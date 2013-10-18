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

let rec jlite_stmts_to_IR3_Stmts (classid: class_name) (mthd: md_decl) (stmtlst:jlite_stmt list): 
  (var_decl3 list * ir3_stmt list) =
  match stmtlst with
    | [] -> ([], [])    
    | s::lst ->
      let rec helper s: (var_decl3 list * ir3_stmt list) =
        match s with
        | ReturnVoidStmt -> ([], [ReturnVoidStmt3])
        | ReturnStmt e -> 
          let (expr3,exprvars,exprstmts) = (jlite_expr_to_IR3Expr classid e true true) in 
          let retIR3 = (ReturnStmt3 (iR3Expr_get_id3 expr3)) in 
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

let ir3_expr_to_id3 (exp: ir3_exp) (t: jlite_type) (var1: var_decl3 list) (var1: var_decl3 list) 
  (toid3: bool): (ir3_exp * var_decl3 list * ir3_stmt list) =

let ir3_expr_get_idc3 (exp: ir3_exp): idc3 =

let jlite_var_id_to_IR3Expr (classid: class_name) (v:var_id) (toid3:bool):(ir3_exp * var_decl3 list * ir3_stmt list) =
  match v with
    | SimpleVarId id -> (Idc3Expr (Var3 id),[],[])
    | TypedVarId (id,t,s) -> 
      if (s == 1) (* class scope *)
      then let thisExpr = 
        FieldAccess3 ("this",id) in 
        (ir3_expr_to_id3 thisExpr t [] [] toid3)
      else let newExpr = Idc3Expr (Var3 id) in
        (newExpr ,[], []) 

let rec jlite_expr_to_IR3Expr (classid: class_name) (jexp:jlite_exp) (toidc3:bool) (toid3:bool): (ir3_exp * var_decl3 list * ir3_stmt list) =
  let rec helper (je:jlite_exp) (toidc3:bool) (toid3:bool): (ir3_exp * var_decl3 list * ir3_stmt list)  =
    match je with
      | BoolLiteral v -> 
        let newExpr = Idc3Expr (BoolLiteral3 v) in 
        (ir3_expr_to_id3 newExpr BoolT [] [] toid3)
      | TypedExp (te,t) -> 
        begin 
        match te with
          | Var v -> (jlite_var_id_to_IR3Expr classid v toidc3)
          | BinaryExp (op,arg1,arg2) -> 
            let (arg1IR3,vars1,stmts1) = (helper arg1 true false) in
            let (arg2IR3,vars2,stmts2) = (helper arg2 true false) in
            let arg1Idc3 = (ir3_expr_get_idc3 arg1IR3) in 
            let arg2Idc3 = (ir3_expr_get_idc3 arg2IR3) in 
            let newExpr = BinaryExp3 (op, arg1Idc3, arg2Idc3) in 
            (ir3_expr_to_id3 newExpr t (List.append vars1 vars2) (List.append stmts1 stmts2) toidc3)
  in
    helper jexp toidc3 toid3

(* OK *)
let jlite_md_decl_to_IR3 cname m : md_decl3 = 
  let (new_vars,new_stmts) = (jlite_stmts_to_IR3_Stmts cname m m.stmts)
  in {   
    id3= string_of_var_id m.ir3id;
    rettype3=m.rettype; 
    params3=(ObjectT cname, "this") :: (jlite_var_decl_lst_to_ID3 m.params);
    localvars3= (jlite_var_decl_lst_to_ID3 m.localvars) @ new_vars; 
    ir3stmts=new_stmts;
  }
} 

let dummy_ir3_type = 
  Unknown

let dummy_var_decl3 = 
  (dummy_ir3_type, "id3")

let dummy_cdata3 = 
  ("cname3", [dummy_var_decl3])

let dummy_ir3_stmt =
  Label3 "label3"

let dummy_md_decl3 = 
  { 
    id3= "id3"; 
    rettype3= dummy_ir3_type;
    params3= [dummy_var_decl3];
    localvars3= [dummy_var_decl3];
    ir3stmts= [dummy_ir3_stmt];
   }

let jlite_var_decl_lst_to_ID3 (cvars: var_decl list): var_decl3 list =
  [ dummy_var_decl3 ]

let jlite_program_to_IR3 (p:jlite_program): ir3_program =
  (* TODO *)
  let jlite_class_main_to_IR3 (cmain: class_main) : (cdata3 * md_decl3 list) =
    (dummy_cdata3, [dummy_md_decl3])

  (* OK *)
  let rec jlite_class_decl_to_IR3((cname,cvars,cmthds) : class_decl) : (cdata3 * md_decl3 list) =
    let rec helper mthdlst : (md_decl3 list) =
      match mthdlst with 
      | [] -> []
      | m::tail_rest -> ( cname m) :: ( helper tail_rest)
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