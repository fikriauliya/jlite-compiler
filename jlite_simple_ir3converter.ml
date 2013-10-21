open Jlite_structs
open Ir3_structs
open Printf

let println line = begin
  (* printf "%s\n" line; *)
end

let varcount = ref 0 
let labelcount = ref 0
let fresh_var () = 
(  
  varcount:=!varcount+1; 
  (string_of_int !varcount)
)
let fresh_label () = 
(  
  labelcount:=!labelcount+1; 
  !labelcount
)

let new_varname() = "_t" ^ fresh_var()
let new_label() = fresh_label()

(* let method_maps: ((string, string) Hashtbl.t) = (Hashtbl.create 100) *)

(* let id3_of_class_name = string :
let id3_of_jlite_op =  :
let id3_of_jlite_type = :
let id3_of_var_id =  :
let id3_of_jlite_exp = :
let id3_of_var_decl = jlite_type * var_id :
let id3_of_typed_var_id = string * jlite_type * int :
let id3_of_jlite_stmt =  :
let id3_of_md_decl = :
let id3_of_class_decl = class_name * (var_decl list) * (md_decl list) :
let id3_of_class_main = class_name * md_decl : *)

(* 
ir3expr_to_id3 reduces an ir3 expr (exp) which is not necesarily of the form Idc3Expr (Var3 id) 
to an expression of the form Idc3Expr (Var3 id) if the parameter is true. In order to do so it will
a) create a fresh variable name
b) then will create an AssignStmt3 with the new variable as the left hand side and the exp received as parameter as the right hand side
c) will return the Idc3Expr (Var3 newvar) and the new statements and var declaration if any. *)
let ir3_expr_to_id3 (exp: ir3_exp) (ret_type: jlite_type) (var: var_decl3 list) (stmts: ir3_stmt list) 
  (toid3: bool): (ir3_exp * var_decl3 list * ir3_stmt list) = begin
  println "+ir3_expr_to_id3";
  println (string_of_bool toid3);
  if toid3 == true
  then
    let temp_id = new_varname() in
    let new_stmt = AssignStmt3 (temp_id, exp) in
    let new_var_decl = (ret_type, temp_id) in
    let new_exp = Idc3Expr (Var3 temp_id) in
    (new_exp, var @ [new_var_decl], stmts @ [new_stmt])
  else
    (exp, var, stmts)    
end

let ir3_expr_get_idc3 (exp: ir3_exp): idc3 =
  match exp with
    Idc3Expr ret -> ret
    | _ -> failwith ("\nir3_expr_get_idc3: The given parameter is not idc3 expr\n")

let ir3_expr_get_id3 (exp: ir3_exp): id3 =
  match (ir3_expr_get_idc3 exp) with
    Var3 ret -> ret
    | _ -> failwith ("\nir3_expr_get_id3: The given parameter is not idc3 expr Var\n")

let jlite_var_id_to_IR3Expr (classid: class_name) (v:var_id) (toid3:bool):(ir3_exp * var_decl3 list * ir3_stmt list) =
  match v with
    | SimpleVarId id -> 
    begin
      println "SimpleVarId";
      (Idc3Expr (Var3 id),[],[])
    end
    | TypedVarId (id,t,s) ->  
      println "TypedVarId";
      if (s == 1) (* class scope *)
      then begin
        println "Class scope";
        let thisExpr = FieldAccess3 ("this",id) in 
        (ir3_expr_to_id3 thisExpr t [] [] toid3)
      end
      else 
        begin
        println "Local scope";
        let newExpr = Idc3Expr (Var3 id) in
        (newExpr ,[], []) 
      end

let jlite_var_decl_lst_to_ID3 (cvars: var_decl list): var_decl3 list = begin
  println "+jlite_var_decl_lst_to_ID3";
  List.map (fun cvar -> 
    match cvar with
      (t, vid) ->
        match vid with
          SimpleVarId ret -> (t, ret)
          | TypedVarId (ret, _, _) -> (t, ret)
  ) cvars
end

(* If you look at the specifications of the Jlite and IR3 language you will see that 
expressions need to be reduced to different forms. 
Some Jlite exp willl be transformed to an exp3, others to an idc3 such as the operands for a binary expression, 
and others need to be reduced to an id3 such as: (Jlite)return exp -> (IR3)return id3. *)
let rec jlite_expr_to_IR3Expr (classid: class_name) (jexp:jlite_exp) (toidc3:bool) (toid3:bool): (ir3_exp * var_decl3 list * ir3_stmt list) =
  let rec helper (je:jlite_exp) (toidc3:bool) (toid3:bool): (ir3_exp * var_decl3 list * ir3_stmt list)  =
    match je with
      | TypedExp (te,t) -> 
        begin
        println "jlite_expr_to_IR3Expr: TypedExp";
        match te with
          (* OK *)
          | BoolLiteral v -> 
            let newExpr = Idc3Expr (BoolLiteral3 v) in 
            (ir3_expr_to_id3 newExpr BoolT [] [] toid3)
          (* OK *)
          | IntLiteral v -> 
            (ir3_expr_to_id3 (Idc3Expr (IntLiteral3 v)) IntT [] [] toid3)
          (* OK *)
          | StringLiteral v -> 
            (ir3_expr_to_id3 (Idc3Expr (StringLiteral3 v)) StringT [] [] toid3)

          (* OK *)
          | Var v -> (jlite_var_id_to_IR3Expr classid v toid3)
          (* OK *)
          | BinaryExp (op,arg1,arg2) -> 
          
          
          
          
          

            let (arg1IR3,vars1,stmts1) = (helper arg1 true true) in
            let arg1Idc3 = (ir3_expr_get_idc3 arg1IR3) in 
            begin
              match op with
                | (BooleanOp "&&") -> 
                  let label_false_num = (new_label()) in
                  let label_end_num = (new_label()) in

                  let if_stmt = IfStmt3 (BinaryExp3 ((RelationalOp "=="), arg1Idc3, (BoolLiteral3 false)), label_false_num) in
                  let temp_var = new_varname() in

                  let (arg2IR3,vars2,stmts2) = (helper arg2 true true) in
                  (* let arg2Idc3 = (ir3_expr_get_idc3 arg2IR3) in  *)
                  let true_assignment_stmt = AssignStmt3 (temp_var, arg2IR3) in

                  let goto_end = (GoTo3 label_end_num) in
                  let label_false = (Label3 label_false_num) in
                  (* AssignStmt3 of id3 * ir3_exp *)
                  let assign_false_stmt = AssignStmt3 (temp_var, (Idc3Expr (BoolLiteral3 false))) in
                  let label_end = (Label3 label_end_num) in

                  (ir3_expr_to_id3 (Idc3Expr (Var3 temp_var)) BoolT (vars1 @ vars2) 
                    (stmts1 @ [if_stmt] @ stmts2 @ [true_assignment_stmt] @[goto_end] @ [label_false] @ [assign_false_stmt] @ [label_end]) toidc3)
                | (BooleanOp "||") -> 
                  let label_true_num = (new_label()) in
                  let label_end_num = (new_label()) in

                  let if_stmt = IfStmt3 (BinaryExp3 ((RelationalOp "=="), arg1Idc3, (BoolLiteral3 true)), label_true_num) in
                  let temp_var = new_varname() in

                  let (arg2IR3,vars2,stmts2) = (helper arg2 true true) in
                  (* let arg2Idc3 = (ir3_expr_get_idc3 arg2IR3) in  *)
                  let true_assignment_stmt = AssignStmt3 (temp_var, arg2IR3) in

                  let goto_end = (GoTo3 label_end_num) in
                  let label_true = (Label3 label_true_num) in
                  (* AssignStmt3 of id3 * ir3_exp *)
                  let assign_false_stmt = AssignStmt3 (temp_var, (Idc3Expr (BoolLiteral3 true))) in
                  let label_end = (Label3 label_end_num) in

                  (ir3_expr_to_id3 (Idc3Expr (Var3 temp_var)) BoolT (vars1 @ vars2) 
                    (stmts1 @ [if_stmt] @ stmts2 @ [true_assignment_stmt] @[goto_end] @ [label_true] @ [assign_false_stmt] @ [label_end]) toidc3)
                | _ -> 
                  let (arg2IR3,vars2,stmts2) = (helper arg2 true true) in
                  let arg2Idc3 = (ir3_expr_get_idc3 arg2IR3) in 
                  let newExpr = BinaryExp3 (op, arg1Idc3, arg2Idc3) in 
                  (ir3_expr_to_id3 newExpr t (List.append vars1 vars2) (List.append stmts1 stmts2) toidc3)
            end
          (* OK *)
          | UnaryExp (op,arg1) -> 
            let (arg1IR3,vars1,stmts1) = (helper arg1 true true) in
            let arg1Idc3 = (ir3_expr_get_idc3 arg1IR3) in 
            let newExpr = UnaryExp3(op, arg1Idc3) in 
            (ir3_expr_to_id3 newExpr t vars1 stmts1 toidc3)

          (* OK *)
          | ThisWord -> begin
            println "ThisWord";
            (jlite_var_id_to_IR3Expr classid (TypedVarId ("this", (ObjectT classid), 2)) toidc3)
          end
          | FieldAccess (e, vid) -> begin 
            println "FieldAccess";
            let (new_ir3, new_vars, new_stmts) = (helper e true true) in
            let new_id3 = (ir3_expr_get_id3 new_ir3) in
            let new_expr = FieldAccess3(new_id3, string_of_var_id vid) in
            (ir3_expr_to_id3 new_expr t new_vars new_stmts toidc3)
          end
          | MdCall (e1, params) -> 
            println "MdCall";
            println ("e1: " ^ (string_of_jlite_expr e1));
            (* let (e1_new,e1_vars,e1_stmts) = helper e1 true true in *)
            let (e1_new,e1_vars,e1_stmts) = match e1 with
              Var v -> (Idc3Expr (Var3 "this"), [], [])
              | TypedExp (e, _) -> begin
                match e with
                  FieldAccess (e1, v2) -> begin
                    helper e1 true true
                  end
                  | _ -> failwith ("\nThis shouldn't be reached\n");
                end
              | _ -> failwith ("\nThis shouldn't be reached\n");
            in

            let mtd_class_name = match e1 with
              Var v -> classid
              | TypedExp (e, t) -> string_of_jlite_type t
              | _ -> failwith ("\nThis shouldn't be reached\n");
            in

            let mtd_name = match e1 with
              Var v ->  extract_var_name v
              | TypedExp (e, _) -> begin
                match e with
                  FieldAccess (e2, v2) -> extract_var_name v2
                  | _ -> failwith ("\nThis shouldn't be reached\n");
                end
              | _ -> failwith ("\nThis shouldn't be reached\n");
            in

            let method_signature = 
              if (List.length params) == 0
              then
                (mtd_class_name ^ "_" ^ mtd_name ^ "_void")
              else
                List.fold_left 
                (fun (accum_params: string) x -> 
                  match x with
                    TypedExp (e, t) -> accum_params ^ "_" ^ (string_of_jlite_type t)
                    | _ -> failwith ("\nThis shouldn't be reached\n");
                ) (mtd_class_name ^ "_" ^ mtd_name) params
            in

            println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
            println method_signature;

            (* let mtd_name_converted = Hashtbl.find method_maps method_signature in
            println mtd_name_converted; *)
            (* let (new_ir3, new_vars, new_stmts, mtd_name) = match e1 with
              Var v ->  (Idc3Expr (Var3 "this"), [], [], (extract_var_name v))
              | FieldAccess (e2, v2) -> begin
                println "FieldAccess";
                let mtd_name = extract_var_name v2 in
                println ("mtd_name: " ^ mtd_name);
                println ("var_name: " ^ (string_of_jlite_expr e2));
                let (new_ir3, new_vars, new_stmts) = (helper e2 true false) in
                (new_ir3, new_vars, new_stmts, mtd_name)
              end
            in *)
            let new_args = List.map (fun x -> helper x true true) params in
            let (new_idc3s, new_vars, new_stmts) = List.fold_left
              (fun (accum_idc3, accum_vars, accum_stmts) (new_ir3, new_vars, new_stmts) -> 
                (accum_idc3 @ [(ir3_expr_get_idc3 new_ir3)], accum_vars @ new_vars, accum_stmts @ new_stmts)
              ) ([],[],[]) new_args in

            let new_expr = MdCall3 (method_signature, (ir3_expr_get_idc3 e1_new)::new_idc3s) in
            (ir3_expr_to_id3 new_expr t (e1_vars @ new_vars) (e1_stmts @ new_stmts) toidc3)

          | ObjectCreate v -> begin
            let new_expr = ObjectCreate3 v in
            println "ObjectCreate";
            (ir3_expr_to_id3 new_expr t [] [] toidc3)
          end
          | NullWord -> 
            let new_expr = Idc3Expr (Var3 "null") in
            (ir3_expr_to_id3 new_expr t [] [] false)
          | _ -> failwith ("\nThis shouldn't be reached\n");
        end
      | Var v -> (jlite_var_id_to_IR3Expr classid v toid3)
      (* | FieldAccess (e, vid) -> begin 
        let e_type = match e with
          TypedExp (_, t) -> t
        in
        println "FieldAccess";
        let (new_ir3, new_vars, new_stmts) = (helper e true true) in
        let new_id3 = (ir3_expr_get_id3 new_ir3) in
        let new_expr = FieldAccess3(new_id3, string_of_var_id vid) in
        (ir3_expr_to_id3 new_expr e_type new_vars new_stmts toidc3)
      end *)
      | _ -> begin
        (* TODO *)
        println "jlite_expr_to_IR3Expr: Others";
        failwith "\nUnhandled Exception\n";
      end
  in
    helper jexp toidc3 toid3

let rec jlite_stmts_to_IR3_Stmts (classid: class_name) (mthd: md_decl) (stmtlst:jlite_stmt list): 
  (var_decl3 list * ir3_stmt list) =
  match stmtlst with
    | [] -> ([], [])    
    | s::lst ->
      (* Convert ONE statement *)
      (* Return variables and new IR3 statements *)
      let rec helper s: (var_decl3 list * ir3_stmt list) =
        match s with
        | ReturnVoidStmt -> ([], [ReturnVoidStmt3])
        | ReturnStmt e -> 
          println "ReturnStmt";
          let (expr3,exprvars,exprstmts) = (jlite_expr_to_IR3Expr classid e true true) in 
          let retIR3 = (ReturnStmt3 (ir3_expr_get_id3 expr3)) in 
          (exprvars, exprstmts @ [retIR3])
        | AssignStmt (id,e) -> begin
          println "AssignStmt";
          let (expr3,exprvars,exprstmts) = (jlite_expr_to_IR3Expr classid e false false) in 
          let assignIR3 = 
            match id with
              | TypedVarId (id1,t,1) -> 
                AssignFieldStmt3 (FieldAccess3 ("this",id1), expr3)
              | TypedVarId (id1,_,2) | SimpleVarId id1 -> 
                (AssignStmt3 (id1, expr3))
              | _ -> failwith ("\nThis shouldn't be reached\n");
          in (exprvars, exprstmts@[assignIR3])
        end
        | AssignFieldStmt (e1, e2) -> begin
          let (expr_1,exprvars_1,exprstmts_1) = (jlite_expr_to_IR3Expr classid e1 false true) in 
          let (expr_2,exprvars_2,exprstmts_2) = (jlite_expr_to_IR3Expr classid e2 false true) in 
          let assignIR3 = (AssignFieldStmt3 (expr_1, expr_2)) in
            (exprvars_1 @ exprvars_2, exprstmts_1 @ exprstmts_2 @ [assignIR3])
          (* ([], [ReturnVoidStmt3]) *)
        end
        | IfStmt (e, stmt_true, stmt_false) -> begin
          let (expr3, exprvars, exprstmts) = (jlite_expr_to_IR3Expr classid e true true) in

          let label_false_num = (new_label()) in
          let label_end_num = (new_label()) in

          let (vars_1, ir3_stmt1) = jlite_stmts_to_IR3_Stmts classid mthd stmt_true in
          let goto_end = (GoTo3 label_end_num) in
          let label_false = (Label3 label_false_num) in
          let (vars_2, ir3_stmt2) = jlite_stmts_to_IR3_Stmts classid mthd stmt_false in
          let label_end = (Label3 label_end_num) in

          (exprvars @ vars_1 @ vars_2, exprstmts @ 
            [IfStmt3 (BinaryExp3 ((RelationalOp "=="), (ir3_expr_get_idc3 expr3), (BoolLiteral3 false)), label_false_num)] 
            @ ir3_stmt1 @ [goto_end] @ [label_false] @ ir3_stmt2 @ [label_end])
        end
        | WhileStmt (e, stmt_true) -> begin
          let label_begin_num = (new_label()) in
          let label_end_num = (new_label()) in

          let label_begin = (Label3 label_begin_num) in
          let (expr3, exprvars, exprstmts) = (jlite_expr_to_IR3Expr classid e true true) in
          
          let (vars_1, ir3_stmt1) = jlite_stmts_to_IR3_Stmts classid mthd stmt_true in
          let goto_begin = (GoTo3 label_begin_num) in
          let label_end = (Label3 label_end_num) in

          (exprvars @ vars_1, [label_begin] @ exprstmts @ 
            [IfStmt3 (BinaryExp3 ((RelationalOp "=="), (ir3_expr_get_idc3 expr3), (BoolLiteral3 false)), label_end_num)] 
          @ ir3_stmt1 @ [goto_begin] @ [label_end])
        end
        | ReadStmt vid -> begin
          let (expr3, exprvars, exprstmts) = (jlite_var_id_to_IR3Expr classid vid true) in
          let read_ir3 = (ReadStmt3 (ir3_expr_get_id3 expr3)) in 
          (exprvars, exprstmts @ [read_ir3])
        end
        | PrintStmt (e) -> begin
          let (expr3,exprvars,exprstmts) = (jlite_expr_to_IR3Expr classid e true true) in 
          let print_ir3 = (PrintStmt3 (ir3_expr_get_idc3 expr3)) in 
          (exprvars, exprstmts @ [print_ir3])
        end
        | MdCallStmt (e) -> begin
          let (expr3,exprvars,exprstmts) = (jlite_expr_to_IR3Expr classid e false true) in 
          let md_call_ir3 = (MdCallStmt3 (expr3)) in 
          (exprvars, exprstmts @ [md_call_ir3])
        end
      in 
      let (vars,stmts) = (helper s) in
      let (tailvars,tailstmts) = (jlite_stmts_to_IR3_Stmts classid mthd lst) in
      (vars @ tailvars, stmts @ tailstmts) 

(* OK *)
let jlite_md_decl_to_IR3 cname m counter : md_decl3 = begin
  println "========================================================";
  println (cname ^ " " ^ (string_of_var_id m.ir3id));

  let params_id3 = (jlite_var_decl_lst_to_ID3 m.params) in
  let method_signature = 
    if List.length params_id3 == 0 
    then
      (cname ^ "_" ^ (string_of_var_id m.ir3id) ^ "_void")
    else
      List.fold_left 
      (fun (accum_params: string) ((new_param_t, new_param_id3): var_decl3) ->
        accum_params ^ "_" ^ (string_of_jlite_type new_param_t)
      ) (cname ^ "_" ^ (string_of_var_id m.ir3id)) params_id3 in
  (* let new_method_name = (cname ^ "_" ^ (string_of_var_id m.ir3id) ^ "_" ^ (string_of_int counter)) in *)

  println "********************************************************************";
  println method_signature;
  (* println new_method_name; *)
  (* Hashtbl.add method_maps method_signature new_method_name; *)

  let (new_vars,new_stmts) = (jlite_stmts_to_IR3_Stmts cname m m.stmts)
  in {   
    id3= method_signature;
    rettype3=m.rettype; 
    params3=(ObjectT cname, "this") :: (jlite_var_decl_lst_to_ID3 m.params);
    localvars3= (jlite_var_decl_lst_to_ID3 m.localvars) @ new_vars; 
    ir3stmts=new_stmts;
  }
end

let jlite_program_to_IR3 (p:jlite_program): ir3_program =
  let jlite_class_main_to_IR3 ((cname,cmthd): class_main) : (cdata3 * md_decl3) =
    ((cname, []), (jlite_md_decl_to_IR3 cname cmthd 0))
  in

  (* OK *)
  let rec jlite_class_decl_to_IR3((cname,cvars,cmthds) : class_decl) : (cdata3 * md_decl3 list) =
    let rec helper mthdlst counter : (md_decl3 list) =
      match mthdlst with 
      | [] -> []
      | m::tail_rest -> (jlite_md_decl_to_IR3 cname m counter) :: ( helper tail_rest (counter + 1))
    in 
    ((cname, (jlite_var_decl_lst_to_ID3 cvars)), (helper cmthds 0))
  in begin
    let (main_class, classes) = p in 
    (* OK *)
    let (new_main_ir3, new_main_md_ir3) = (jlite_class_main_to_IR3 main_class) in
    (* OK *)
    let new_ir3_classes_Lst = (List.map jlite_class_decl_to_IR3 classes) in
    (* OK *)
    let (new_classes,new_methods) = (List.split new_ir3_classes_Lst) in 
    (* OK *)
    (new_main_ir3::new_classes, new_main_md_ir3, (List.flatten new_methods))
  end 