open Jlite_structs
open Ir3_structs
open Printf

let println line = begin
  printf "%s\n" line;
end

let varcount = ref 0 
let fresh_var () = 
(  
  varcount:=!varcount+1; 
  (string_of_int !varcount))

let new_varname() = "_t" ^ fresh_var()

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
  println "ir3_expr_to_id3";
  if toid3 == true
  then
    let new_id = new_varname() in
    let new_stmt = AssignStmt3 (new_id, exp) in
    let new_var_decl = (ret_type, new_id) in
    let new_exp = Idc3Expr (Var3 new_id) in
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
          | BoolLiteral v -> 
            let newExpr = Idc3Expr (BoolLiteral3 v) in 
            (ir3_expr_to_id3 newExpr BoolT [] [] toid3)
          | IntLiteral v -> 
            (ir3_expr_to_id3 (Idc3Expr (IntLiteral3 v)) IntT [] [] toid3)
          | StringLiteral v -> 
            (ir3_expr_to_id3 (Idc3Expr (StringLiteral3 v)) StringT [] [] toid3)

          | Var v -> (jlite_var_id_to_IR3Expr classid v toidc3)
          | BinaryExp (op,arg1,arg2) -> 
            let (arg1IR3,vars1,stmts1) = (helper arg1 true false) in
            let (arg2IR3,vars2,stmts2) = (helper arg2 true false) in
            let arg1Idc3 = (ir3_expr_get_idc3 arg1IR3) in 
            let arg2Idc3 = (ir3_expr_get_idc3 arg2IR3) in 
            let newExpr = BinaryExp3 (op, arg1Idc3, arg2Idc3) in 
            (ir3_expr_to_id3 newExpr t (List.append vars1 vars2) (List.append stmts1 stmts2) toidc3)

          | UnaryExp _ -> println "UnaryExp"; (ir3_expr_to_id3 (Idc3Expr (BoolLiteral3 true)) BoolT [] [] toid3)
          | BinaryExp _ -> println "BinaryExp"; (ir3_expr_to_id3 (Idc3Expr (BoolLiteral3 true)) BoolT [] [] toid3)
          | FieldAccess _ -> println "FieldAccess"; (ir3_expr_to_id3 (Idc3Expr (BoolLiteral3 true)) BoolT [] [] toid3)
          | ObjectCreate _ -> println "ObjectCreate"; (ir3_expr_to_id3 (Idc3Expr (BoolLiteral3 true)) BoolT [] [] toid3)
          | MdCall _ -> println "MdCall"; (ir3_expr_to_id3 (Idc3Expr (BoolLiteral3 true)) BoolT [] [] toid3)
          | ThisWord _ -> println "ThisWord"; (ir3_expr_to_id3 (Idc3Expr (BoolLiteral3 true)) BoolT [] [] toid3)
          | NullWord _ -> println "NullWord"; (ir3_expr_to_id3 (Idc3Expr (BoolLiteral3 true)) BoolT [] [] toid3)
          (* TODO *)
        end
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
          let (expr3,exprvars,exprstmts) = (jlite_expr_to_IR3Expr classid e true true) in 
          let retIR3 = (ReturnStmt3 (ir3_expr_get_id3 expr3)) in 
          (exprvars, exprstmts @ [retIR3])
        | AssignStmt (id,e) -> 
          let (expr3,exprvars,exprstmts) = (jlite_expr_to_IR3Expr classid e false false) in 
          begin
          let assignIR3 = 
            match id with
              | TypedVarId (id1,t,1) -> 
                AssignFieldStmt3 (FieldAccess3 ("this",id1), expr3)
              | TypedVarId (id1,_,2) | SimpleVarId id1 -> 
                (AssignStmt3 (id1, expr3))
          in (exprvars, exprstmts@[assignIR3])
          end 
      in 
      let (vars,stmts) = (helper s) in
      let (tailvars,tailstmts) = (jlite_stmts_to_IR3_Stmts classid mthd lst) in
      (vars @ tailvars, stmts @ tailstmts) 

(* OK *)
let jlite_md_decl_to_IR3 cname m : md_decl3 = begin
  println "========================================================";
  println (cname ^ " " ^ (string_of_var_id m.ir3id));
  let (new_vars,new_stmts) = (jlite_stmts_to_IR3_Stmts cname m m.stmts)
  in {   
    id3= string_of_var_id m.ir3id;
    rettype3=m.rettype; 
    params3=(ObjectT cname, "this") :: (jlite_var_decl_lst_to_ID3 m.params);
    localvars3= (jlite_var_decl_lst_to_ID3 m.localvars) @ new_vars; 
    ir3stmts=new_stmts;
  }
end

let jlite_program_to_IR3 (p:jlite_program): ir3_program =
  let jlite_class_main_to_IR3 ((cname,cmthd): class_main) : (cdata3 * md_decl3) =
    ((cname, []), (jlite_md_decl_to_IR3 cname cmthd))
  in

  (* OK *)
  let rec jlite_class_decl_to_IR3((cname,cvars,cmthds) : class_decl) : (cdata3 * md_decl3 list) =
    let rec helper mthdlst : (md_decl3 list) =
      match mthdlst with 
      | [] -> []
      | m::tail_rest -> (jlite_md_decl_to_IR3 cname m) :: ( helper tail_rest)
    in 
    ((cname, (jlite_var_decl_lst_to_ID3 cvars)), (helper cmthds))
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