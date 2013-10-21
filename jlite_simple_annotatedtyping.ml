
(* ===================================================== *)
(* ============== CS4212 Compiler Design ============== *)
(*     TypeChecking of Jlite programs *)
(*     A Simplified version  *)
(*     Submitting at your own risk  *)
(* ===================================================== *)

open Jlite_structs
open Printf

let println line = begin
	(* printf "%s\n" line; *)
end

let rec compare_params_type (x:var_decl list) (y:var_decl list) =
	if (List.length x) > (List.length y) 
	then 1
	else
		if (List.length x) < (List.length y) 
		then -1
		else
			begin
			match (x, y) with
				| ([], []) -> 0
				| ((xt, xh)::xs, (yt, yh)::ys) -> 
					if xt == yt 
					then begin
						(* println (string_of_jlite_type xt);
						println (string_of_jlite_type yt); *)
						compare_params_type xs ys
					end
					else begin
						(* println (string_of_jlite_type xt);
						println (string_of_jlite_type yt); *)
						compare xt yt
					end
				| _ -> failwith ("\nThis shouldn't be reached\n");
			end

(* From: https://gist.github.com/23Skidoo/1664038 *)
let rec remove_dups lst = 
	match lst with 
	| [] -> []
	| h::t -> h::(remove_dups (List.filter (fun x -> x<>h) t))

(* From: http://ocaml.org/tutorials/99problems.html *)
let group_dups_func comparator lst =
	let sorted_lst = List.sort comparator lst in
  let rec aux current acc = function
    | [] -> []    (* Can only be reached if original list is empty *)
    | [x] -> (x :: current) :: acc
    | a :: (b :: _ as t) ->
       if (comparator a b) == 0 then aux (a :: current) acc t
       else aux [] ((a :: current) :: acc) t  in
  List.rev (aux [] [] sorted_lst);;

let group_dups lst =
	group_dups_func	compare lst

let filter_dups lst =
	let duplicates_group = group_dups lst in
	List.map (fun x -> List.hd x) (List.filter (fun x -> (List.length x) > 1) duplicates_group)

(* Compare two variable ids *) 	
let compare_var_ids v1 v2 = begin
	println "compare_var_ids";
	println (extract_var_name v1);
	println (extract_var_name v2);
	match v1, v2 with
	| SimpleVarId id1, SimpleVarId id2 -> 
		((String.compare id1 id2) == 0)
	| SimpleVarId id1, TypedVarId (id2, t, s) -> 
		((String.compare id1 id2) == 0)	
	| TypedVarId (id1, t, s), SimpleVarId id2 -> 
		((String.compare id1 id2) == 0)		
	| TypedVarId (id1, t1, s1), TypedVarId (id2, t2 , s2) ->
		((String.compare id1 id2) == 0) && (s1 == s2)
end

(* Find the declared type of a variable *) 		
(* TODO: consider scoping *)
let rec find_var_decl_type 
	(vlst: var_decl list) (vid:var_id) : (jlite_type * var_id) =
  match vlst with
    | [] -> (Unknown, SimpleVarId "") 
    | (t, v)::lst -> 
			if (compare_var_ids v vid) 
			then (t, v) 
			else (find_var_decl_type lst vid)

let find_class (p:jlite_program) (classid:class_name) : class_decl =
	match p with
		| (_, classes) ->
			List.find
				(fun x -> 
					match x with
					(cn, _, _) -> 
						(compare cn classid) == 0
				)
				classes

let rec find_method
	(p:jlite_program) (classid:class_name) (method_name:string) (param_types: jlite_type list): md_decl =
	begin
	match p with
		| (_, classes) ->
			let found_class = find_class p classid in
			let found_method = 
				match found_class with
					(_, _, mds) ->
						List.find 
							(fun x -> 
								if (compare (extract_var_name x.jliteid) method_name) == 0
								then
									if (compare_params_type x.params (List.map (fun x -> (x, SimpleVarId "")) param_types)) == 0
									then true
									else false
								else false
							)
							mds
			in
			found_method
	end

let rec find_field
	(p:jlite_program) (classid:class_name) (field_name: string): var_decl =
	begin
	match p with
		| (_, classes) ->
			let found_class = List.find
				(fun x -> 
					match x with
					(cn, _, _) -> 
						(compare cn classid) == 0
				)
			classes in
			let found_field = 
				match found_class with
					(_, fs, _) ->
						List.find 
							(fun x -> 
								match x with
									_, f_id -> 
										println "Comparing";
										println (extract_var_name f_id);
										println field_name;
										(compare (extract_var_name f_id) field_name) == 0
							)
							fs
			in
			found_field
	end

(* Check if a variable id exists *)
let exists_var_id 
	(vlst: var_decl list) (vid: var_id) : bool =
	let is_eq_id ((t, v): var_decl):bool =
		(compare_var_ids v vid) 
	in (List.exists is_eq_id vlst) 	

(* Check if the declaration of a class exists *) 			  
(* OK *)
let exists_class_decl ((cm, clst): jlite_program) (cid:class_name) =
	(List.exists 
		(fun (cname, cvars, cmtd) ->
			(String.compare cname cid) == 0)
	clst)

(* Annotate a list of variable declarations with their scope *)	
let rec create_scoped_var_decls
	(vlst: var_decl list) (scope:int) =
	let helper ((vt, vid):var_decl) =
		match vid with
		| SimpleVarId id -> 
			(vt, TypedVarId (id, vt, scope))
		| TypedVarId (id, t, s) -> 
			(vt, TypedVarId (id, vt, scope))
	in (List.map helper vlst)

let type_check_class_decl_list
	(p: jlite_program) 
	(clist: class_decl list) =

	let check_duplicates clist : string list =
		let extract_class_name (cldec: class_decl) =
			match cldec with
				(cn, vd, md) ->
					cn
		in
		let class_names = List.map extract_class_name clist in
		filter_dups class_names
	in
	match (check_duplicates clist) with
		| [] ->  (true,"")
		| lst -> (false, ("Duplicate class name found: " 
				^ (string_of_list lst (fun x -> x) ",")))

(* Type check a list of variable declarations 
  1) Determine if all object types exist
  2) Find and return duplicate variable names 
*)  
(* OK *)
let rec type_check_var_decl_list
	(p: jlite_program) 
	(vlst: var_decl list) = begin

	println "+type_check_var_decl_list";
	println ("var_decl: [" ^ (string_of_list vlst string_of_var_decl ", " ) ^ "]");

	let rec check_existances (vlst: var_decl list) :jlite_type list =
		match vlst with
		| [] ->
			[]
		| (typ, vid)::tails -> 
			match typ with
			| ObjectT cname -> begin
				println "ObjectT";
	        (* check if the declared class name exists *)
				if (exists_class_decl p cname) 
					then begin
						(check_existances tails)
					end
					(* return the undefined type *)
					else begin
						(typ :: (check_existances tails))
					end
			end
			(* Primitive type *)
			| _ -> begin
				println "Primitive type";
				(check_existances tails) 
			end
	in
	let check_duplicates (vlst: var_decl list) : string list =
		let extract_var_name (vlst: var_decl) =
			match vlst with
				(typ, vid) -> extract_var_name(vid)
		in
		let var_names = List.map extract_var_name vlst in
		filter_dups var_names
	in
		match (check_existances vlst) with
		| [] -> 
			begin
				match (check_duplicates vlst) with
				| [] ->  (true,"")
				| lst -> (false, ("Duplicate variables found: " ^ (string_of_list lst (fun x -> x) ",")))
			end
		| lst -> (false, ("Undefined types: " ^ (string_of_list lst (fun x -> (string_of_jlite_type x)) ",")))
end

(* Type check a list of method declarations 
  1) Determine if there is illegal overloading
  2) Find and return overloaded method names	
*)  
let rec type_check_md_overloading 
	(classid: class_name) (mdlst: md_decl list) = begin

	let extract_var_name varid = 
		match varid with
			| SimpleVarId var_name -> var_name
			| TypedVarId (var_name, typ, scope) -> var_name
	in

	let name_duplicated_methods = List.filter (fun x -> (List.length x) > 1)
		(group_dups_func 
			(fun x y -> 
				let x_name = extract_var_name x.jliteid in
				let y_name = extract_var_name y.jliteid in
				compare x_name y_name)
			mdlst) in

	let overloaded_methods = 
		List.fold_left 
			(fun p mds -> 
				begin
				println "";
				println (">> +Evaluating: " ^ (string_of_var_id (List.hd mds).jliteid));
				let params_duplicated_methods = List.filter (fun x -> (List.length x) > 1)
					(group_dups_func
						(fun x y ->
							let x_params = x.params in
							let y_params = y.params in

							compare_params_type x_params y_params)
						mds)
				in
					println (">> -Evaluating: " ^ (string_of_var_id (List.hd mds).jliteid));
					println "";
					if (List.length params_duplicated_methods) > 0 
					then
						(extract_var_name (List.hd mds).jliteid)::p
					else
						p
				end
			) [] name_duplicated_methods in

	if (List.length overloaded_methods) == 0
	then (true, "")
	else (false, "\nIncorrect overloaded method: " ^ string_of_list overloaded_methods (fun x -> x) "," ^ "\n")
end
				
(* Type check an expression *)
(* Return the type of the Expression 
    and a new TypedExpes	ion *)  
let rec type_check_expr 
	(p: jlite_program)(env: var_decl list) 
	(classid: class_name) (exp:jlite_exp) : (jlite_type * jlite_exp) = begin
		
	println "type_check_expr";
	let rec helper e : (jlite_type * jlite_exp) =
		match e with
		| TypedExp _ -> 
			println "Unknown";
  		(Unknown, e) 
		| BoolLiteral v -> 
			println "BoolLiteral";
			(BoolT, (TypedExp (e, BoolT)))
		| IntLiteral v -> 
			println "IntLiteral";
			(IntT, (TypedExp (e, IntT)))
		| StringLiteral v -> 
			println "StringLiteral";
			(StringT, (TypedExp (e, StringT)))
		| ThisWord -> 
			println "ThisWord";
			((ObjectT classid), TypedExp (e, (ObjectT classid)))
		| NullWord -> 
			println "NullWord";
			((ObjectT "null") , TypedExp (e, (ObjectT "null")))
		| Var v -> 
			println "Var";
			println (extract_var_name v);
			let (vtyp, vid) =(find_var_decl_type env v) in
			println "Found";
			println (extract_var_name vid);
			(vtyp, TypedExp (Var vid, vtyp)) 
		| ObjectCreate c -> 
			println "ObjectCreate";
			if (exists_class_decl p c) 
			then ((ObjectT c), TypedExp(e, (ObjectT c)))
			else (Unknown, e)
		| BinaryExp (op, e1, e2) ->
			println "BinaryExp";
			let (e1_t, e1_new) = (helper e1) in
			let (e2_t, e2_new) = (helper e2) in
			if (compare e1_t e2_t) == 0
			then
				match op with
					RelationalOp _ -> (BoolT, TypedExp(BinaryExp (op, e1_new, e2_new), BoolT))
					| _ -> (e1_t, TypedExp(BinaryExp (op, e1_new, e2_new), e1_t))
			else
				failwith 
					("\nType-check error in BinaryExp: " ^ (string_of_jlite_expr e) ^ "\n") 
		| UnaryExp (op ,e1) ->
			println "UnaryExp";
			let (e1_t, e1_new) = helper e1 in
			(e1_t, TypedExp(UnaryExp (op, e1_new), e1_t))
		| MdCall (e1, params) -> begin
			println ("MdCall helper: " ^ (string_of_jlite_expr e1));
			(* let (e1_t, e1_new) = helper e1 in *)
			let (params_t, params_e) = (List.fold_left 
				(fun (accum_t, accum_e) (new_raw_e) -> 
					let (new_t, new_e) = helper new_raw_e in
					(accum_t @ [new_t], accum_e @ [new_e]))
				([],[])  params) in
			let (typed_e1, found_method) = match e1 with
				| Var name -> 
					let method_name = extract_var_name name in
					let found_method = find_method p classid method_name params_t in
					(Var name, found_method) 
				(* | UnaryExp _ -> println "UnaryExp";  SimpleVarId "" *)
			  (* | BinaryExp _ -> println "BinaryExp"; SimpleVarId "" *)
			  | FieldAccess (e2, v2) -> 
			  	println ("FieldAccess : " ^ (string_of_jlite_expr e2) ^ " -> " ^ (string_of_var_id v2)); 
			  	let (t3, e3) = helper e2 in
			  	begin
			  	match t3 with
			  		ObjectT classid -> 
			  			println ("Class: " ^ classid);
			  			let found_method = find_method p classid (extract_var_name v2) params_t in
			  			(TypedExp (FieldAccess (e3, v2), t3), found_method)
			  		| Unknown | VoidT | StringT | BoolT | IntT -> failwith ("\nThis shouldn't be reached\n");
			  	end
		  	| ObjectCreate _
				| MdCall _
				| BoolLiteral _
				| IntLiteral _
				| StringLiteral _
				| ThisWord _
				| NullWord _
				| TypedExp _ 
				| BinaryExp (_, _, _)
				| UnaryExp (_, _) -> failwith ("\nThis shouldn't be reached\n");
			  (* | ObjectCreate class_name -> println "ObjectCreate"; find_method p "Hello1" "w" [IntT]
			  | MdCall (md, mp) -> println "MdCall"; find_method p "Hello1" "w" [IntT]
			  (* | BoolLiteral _ -> println "BoolLiteral"; Unknown *)
			  (* | IntLiteral _ -> println "IntLiteral"; Unknown *)
			  (* | StringLiteral _ -> println "StringLiteral"; Unknown *)
			  | ThisWord -> println "ThisWord"; find_method p "Hello1" "w" [IntT]
			  (* | NullWord -> println "NullWord"; Unknown *)
			  (* TODO: check this *)
			  | TypedExp _ -> println "TypedExp"; find_method p "Hello1" "w" [IntT] *)
			in
			(* println (string_of_jlite_expr e1); *)
			(* println (extract_var_name found_method.jliteid); *)
			(found_method.rettype, TypedExp(MdCall(typed_e1, params_e), found_method.rettype))
		end
		| FieldAccess (e1, v1) ->
			println "FieldAccess";
			let (otype, oexp) = helper e1 in
			(* let otype = match e1 with
				| Var var -> 
					let (otype, oid) = (find_var_decl_type env var) in
					otype
				(* | UnaryExp _ -> println "UnaryExp";  SimpleVarId "" *)
			  (* | BinaryExp _ -> println "BinaryExp"; SimpleVarId "" *)
			  | FieldAccess (e2, v2) -> 
			  	let (t3, e3) = helper (FieldAccess (e2, v2)) in
			  	t3
			  | ObjectCreate class_name -> 
			  	let (t3, e3) = helper (ObjectCreate class_name) in
			  	t3
			  | MdCall (md, mp) -> 
			  	let (t3, e3) = helper (MdCall (md, mp)) in
			  	t3
			  (* | BoolLiteral _ -> println "BoolLiteral"; Unknown *)
			  (* | IntLiteral _ -> println "IntLiteral"; Unknown *)
			  (* | StringLiteral _ -> println "StringLiteral"; Unknown *)
			  | ThisWord -> 
			  	let (t3, e3) = helper (ThisWord) in
			  	t3
			  (* | NullWord -> println "NullWord"; Unknown *)
			  (* TODO: check this *)
			  | TypedExp _ -> println "TypedExp";  Unknown
			in *)
			let field_name =  (extract_var_name v1) in
			println field_name;
			match otype with
				ObjectT class_name ->
					println class_name;
					let (field_type, field_var_id) = find_field p class_name field_name in
					(field_type, (TypedExp ((FieldAccess (oexp, v1)), field_type)))
				| _ -> 
					println "Unknown type";
					(Unknown, e1)

	  in  helper exp
end

(* Type check a list of statements and determine the return type.
   Exceptions are thrown when a statement does not type check 
   and when dead code is found
*)  
let rec type_check_stmts 
	(p: jlite_program)(env: var_decl list) 
	(classid: class_name) 
	(mthd: md_decl) 
	(stmtlst:jlite_stmt list)
	(rettype: jlite_type option) 
	: (jlite_type option *(jlite_stmt list))  = begin

	println "";
	println "+type_check_stmts";
	println (string_of_list stmtlst string_of_jlite_stmt ", ");

	match stmtlst with
	| [] -> (rettype,[])
	| s::tail_lst -> 
		let rec helper s : (jlite_type option * jlite_stmt) =
			match s with
			| ReturnStmt e ->  
				let (expr_type, exprnew) = (type_check_expr p env classid e) in
				begin
				println "ReturnStmt";
				match expr_type with
				| Unknown -> 
					failwith 
					("\nType-check error in " 
					^ classid ^ "." ^ string_of_var_id mthd.jliteid 
					^ ". Return expression fails:\n" 
					^ string_of_jlite_stmt s ^ "\n")
				| _ ->  (Some expr_type, ReturnStmt exprnew)
				end
			| ReturnVoidStmt ->  
				(Some VoidT, ReturnVoidStmt)
			| ReadStmt id -> 
				let (idtype, scopedid) = (find_var_decl_type env id) in
				begin
				match idtype with
				| ObjectT _ | Unknown  -> 
					failwith 
					("\nType-check error in " 
					^ classid ^ "." ^ string_of_var_id mthd.jliteid 
					^ ". Read statement fails:\n" 
					^ string_of_jlite_stmt s ^ "\n")
				| _ ->  (None, ReadStmt scopedid)
				end
			| PrintStmt e -> 
				let (expr_type, exprnew) = 
				 (type_check_expr p env classid e) in
				begin
				match expr_type with
				| Unknown | ObjectT _ -> 
					failwith 
					("\nType-check error in " 
					^ classid ^ "." ^ string_of_var_id mthd.jliteid 
					^ ". Statement fails:\n" 
					^ string_of_jlite_stmt s ^ "\n")
				| _ ->  (None, PrintStmt exprnew)
				end
			| AssignStmt (vi, e) ->
				println "AssignStmt";
				let (expr_type, expr_new) = (type_check_expr p env classid e) in
				let (vi_type, vi_new) = (find_var_decl_type env vi) in
				println (string_of_jlite_type expr_type);
				println (string_of_jlite_type vi_type);

				if (compare expr_type vi_type) == 0
					then (None, AssignStmt (vi_new, expr_new))
					else
						begin
							match expr_type with
								(ObjectT "null") -> (None, AssignStmt (vi_new, expr_new))
								| _ -> failwith 
									("\nType-check error in " 
									^ classid ^ "." ^ string_of_var_id mthd.jliteid 
									^ ". AssignStmt fails, type mismatch:\n" 
									^ string_of_jlite_stmt s ^ "\n")
						end
			| MdCallStmt e ->
				let (expr_type, expr_new) = (type_check_expr p env classid e) in
				(None, MdCallStmt expr_new)
			| AssignFieldStmt (e1, e2) ->
				let (expr1_type, expr1_new) = (type_check_expr p env classid e1) in
				let (expr2_type, expr2_new) = (type_check_expr p env classid e2) in
				if (compare expr1_type expr2_type) == 0
					then (None, AssignFieldStmt (expr1_new, expr2_new))
					else
						begin
							match expr2_type with
								(ObjectT "null") -> (None, AssignFieldStmt (expr1_new, expr2_new))
								| _ -> failwith 
									("\nType-check error in " 
									^ classid ^ "." ^ string_of_var_id mthd.jliteid 
									^ ". AssignFieldStmt fails, type mismatch:\n" 
									^ string_of_jlite_stmt s ^ "\n")
						end
			| IfStmt (e, s1, s2) ->
				let (expr_type, expr_new) = (type_check_expr p env classid e) in
				begin
				match expr_type with
					BoolT ->
						let (newrettype1, newstmt1) = type_check_stmts p env classid mthd s1 None in
						let (newrettype2, newstmt2) = type_check_stmts p env classid mthd s2 None in
						begin
						match (newrettype1, newrettype2) with
							(Some t1, Some t2) -> 
								if (compare t1 t2) == 0
									then (newrettype1, IfStmt (expr_new, newstmt1, newstmt2))
									else
									begin
										match (t1, t2) with
											(ObjectT "null", _) -> (newrettype2, IfStmt (expr_new, newstmt1, newstmt2))	
											| (_, ObjectT "null") -> (newrettype1, IfStmt (expr_new, newstmt1, newstmt2))
											| _ -> 
												failwith 
													("\nType-check error in " 
													^ classid ^ "." ^ string_of_var_id mthd.jliteid 
													^ ". IfStmt inner statements fails, expression type mismatch:\n" 
													^ string_of_jlite_stmt s ^ "\n")
									end
							| (None _, None _) -> (None, IfStmt (expr_new, newstmt1, newstmt2))
							| _ -> (None, IfStmt (expr_new, newstmt1, newstmt2))
						end
					| _ ->
						failwith 
							("\nType-check error in " 
							^ classid ^ "." ^ string_of_var_id mthd.jliteid 
							^ ". IfStmt checking fails, expression type mismatch:\n" 
							^ string_of_jlite_stmt s ^ "\n")
				end
				| WhileStmt (e, s1) ->
					let (expr_type, expr_new) = (type_check_expr p env classid e) in
					match expr_type with
						BoolT ->
							let (newrettype1, newstmt1) = type_check_stmts p env classid mthd s1 None in
							(None, WhileStmt (expr_new, newstmt1))
						| _ ->
							failwith 
								("\nType-check error in " 
								^ classid ^ "." ^ string_of_var_id mthd.jliteid 
								^ ". WhileStmt checking fails, expression type mismatch:\n" 
								^ string_of_jlite_stmt s ^ "\n")


	  in let (newrettype, newstmt) = ( helper s) in
	  match newrettype, tail_lst with
		| Some t, head::tail -> 
			failwith 
			("\nType-check error in " ^ classid ^ "." 
			 ^ string_of_var_id mthd.jliteid 
			 ^ ". Dead Code:\n" 
			 ^ (string_of_list tail_lst string_of_jlite_stmt "\n" ^ "\n")) 
		| _,_ ->  
			let (rettype, stmts) = (type_check_stmts p env classid mthd tail_lst newrettype) in
				(rettype, (newstmt::stmts))
 end

(* TypeCheck a JLite Method Declaration *)
let type_check_mthd_decl p env cname m : md_decl = begin
	println (">>>>>>>>>>>>>>>>>>>>>>>>> + type_check_mthd_decl: [" ^ string_of_var_id(m.jliteid) ^ "]") ;

	let mthdenv = 
		List.append m.params m.localvars in 
	(* OK *)
	let (retval, errmsg) = 
		(type_check_var_decl_list p mthdenv)
	in if (retval == false) 
		then failwith 
		 ("\nType-check error in " ^ cname ^ "." 
		  ^ string_of_var_id m.jliteid 
		  ^ " parameter or local variables declarations.\n"
		  ^ errmsg ^ "\n")
		else
		let scopedEnv = List.append 
				(create_scoped_var_decls mthdenv 2) env in 
		(* TypeCheck the body of the method *)
			let (rettyp, newstmts) = 
				(type_check_stmts p scopedEnv cname m m.stmts None) in
		(* TypeCheck the return type of the method *)
			let _ = match rettyp, m.rettype with
			| None, VoidT -> true
			| Some VoidT, VoidT -> true
			| None, t -> 
				failwith 
				("\nType-check error in " ^ cname ^ "." 
				^ string_of_var_id m.jliteid 
				^ ". This method must return a result of type "
				^ string_of_jlite_type m.rettype ^ ". \n")
			| Some (ObjectT t1), (ObjectT t2) -> 
				if ((String.compare t1 t2) != 0) 
				then 
					if (compare t1 "null") == 0 then
						true
					else 
						failwith 
							("\nType-check error in " ^ cname ^ "." 
							^ string_of_var_id m.jliteid 
							^ ". Type mismatch. Return type of method " 
							^ "is different from declared type "
							^ string_of_jlite_type m.rettype ^ t1 ^ ". \n")
				else true
			| Some t1, t2 -> 
				if (t1!= t2) 
				then failwith 
					("\nType-check error in " ^ cname ^ "." 
					^ string_of_var_id m.jliteid 
					^ ". Type mismatch. Return type of method "
					^ "is different from declared type "
					^ string_of_jlite_type m.rettype 
					^ string_of_jlite_type t1 ^ ". \n")
				else true
			in 
			{ 
				m with stmts = newstmts;
			}
end

(* TypeCheck a JLite Program. 
   Return a new JLite Program 
   where expressions are annotated with types
*)
let type_check_jlite_program (p:jlite_program) : jlite_program =
	(* functions *)

	let type_check_class_main  ((cname, mmthd):class_main ) = 
		(cname, (type_check_mthd_decl p [] cname mmthd )) in

	let rec type_check_class_decl ((cname, cvars, cmthds):class_decl) = 
	begin
		println "";
		println (">>> +type_check_class_decl (for each fields in class: [" ^ cname ^ "], make sure the types are defined & no duplication exists). ");

		(* TypeCheck field declarations *)
		(* OK *)
		let (retval, errmsg) = (type_check_var_decl_list p cvars) in
		if (retval == false) then 
			failwith 
			("\nType-check error in " ^ cname 
			^ " field declarations: " ^ errmsg ^ "\n")

		(* TypeCheck methods overloading *)
		(* OK *)
		else let (retval, errmsg) = (type_check_md_overloading cname cmthds) in
			if (retval == false) then 
				failwith 
				("\nType-check error in " ^ cname 
				^ " method declarations: " ^ errmsg ^ "\n")
			(* TypeCheck method declarations *)
			(* OK *)
			else let env = (create_scoped_var_decls cvars 1) 
			in (cname, cvars, 
				List.map 
				(type_check_mthd_decl p env cname) cmthds
				)
	end
	in 

	begin
		let (mainclass, classes) = p in 

		(* TypeCheck class declarations *)
		let (retval, errmsg) = (type_check_class_decl_list p classes) in
		let _ = 
			if (retval == false) then
					failwith ("\n" ^ errmsg ^ "\n")
			else true 
		in
		let newmain = (type_check_class_main mainclass) in
		let newclasses = (List.map type_check_class_decl classes) in
		(newmain, newclasses)
	end

(* let type_check_jlite_program (p:jlite_program) : jlite_program =
begin
	let ms = find_method p "Hello1" "w" [IntT] in
	println (string_of_list ms string_of_md_decl ", ");
	p
end *)
