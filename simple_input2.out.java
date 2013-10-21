Parsing...
simple_input2.java

======= JLite Program =======

class MainC{
  void main(){
    Test t;
    Int x;
    t=new Test();
    x=[t.m1()];
    Return;
  }

} 

class Compute{

  Int square(Int a){
    Return [a,a](*);
  }


  Int add(Int a,Int b){
    Return [a,b](+);
  }


  Int addSquares(Int a,Int b){
    Return [add([square(a)],[square(b)])];
  }

}
class Test{
  Test t;
  Int d;

  Int m1(){
    Return 2;
  }


  Int m19(){
    Return [new Test().m1()];
  }


  Int m12(){
    Int a;
    If([1,3](>))
    {
      a=3;
    }
    else
    {
      a=4;
    }
    Return 4;
  }


  Int m13(){
    Int i;
    i=0;
    While([i,10](<)) {
  If([i,9](==))
  {
    Return 9;
  }
  else
  {
    i=[i,1](+);
  }
}
    Return 11;
  }

}

======= End of JLite Program =======

>>>>>>>>>>>>>>>>>>>>>>>>> + type_check_mthd_decl: [main]
+type_check_var_decl_list
var_decl: [Test t, Int x]
ObjectT
Primitive type

+type_check_stmts
t=new Test();, x=[t.m1()];, Return;
AssignStmt
type_check_expr
ObjectCreate
compare_var_ids
t
t
Test
Test

+type_check_stmts
x=[t.m1()];, Return;
AssignStmt
type_check_expr
MdCall helper: t.m1
FieldAccess : t -> m1
Var
t
compare_var_ids
t
t
Found
t
Class: Test
compare_var_ids
t
x
compare_var_ids
x
x
Int
Int

+type_check_stmts
Return;

+type_check_stmts


>>> +type_check_class_decl (for each fields in class: [Compute], make sure the types are defined & no duplication exists). 
+type_check_var_decl_list
var_decl: []
>>>>>>>>>>>>>>>>>>>>>>>>> + type_check_mthd_decl: [square]
+type_check_var_decl_list
var_decl: [Int a]
Primitive type

+type_check_stmts
Return [a,a](*);
type_check_expr
BinaryExp
Var
a
compare_var_ids
a
a
Found
a
Var
a
compare_var_ids
a
a
Found
a
ReturnStmt

+type_check_stmts

>>>>>>>>>>>>>>>>>>>>>>>>> + type_check_mthd_decl: [add]
+type_check_var_decl_list
var_decl: [Int a, Int b]
Primitive type
Primitive type

+type_check_stmts
Return [a,b](+);
type_check_expr
BinaryExp
Var
a
compare_var_ids
a
a
Found
a
Var
b
compare_var_ids
a
b
compare_var_ids
b
b
Found
b
ReturnStmt

+type_check_stmts

>>>>>>>>>>>>>>>>>>>>>>>>> + type_check_mthd_decl: [addSquares]
+type_check_var_decl_list
var_decl: [Int a, Int b]
Primitive type
Primitive type

+type_check_stmts
Return [add([square(a)],[square(b)])];
type_check_expr
MdCall helper: add
MdCall helper: square
Var
a
compare_var_ids
a
a
Found
a
MdCall helper: square
Var
b
compare_var_ids
a
b
compare_var_ids
b
b
Found
b
ReturnStmt

+type_check_stmts


>>> +type_check_class_decl (for each fields in class: [Test], make sure the types are defined & no duplication exists). 
+type_check_var_decl_list
var_decl: [Test t, Int d]
ObjectT
Primitive type
>>>>>>>>>>>>>>>>>>>>>>>>> + type_check_mthd_decl: [m1]
+type_check_var_decl_list
var_decl: []

+type_check_stmts
Return 2;
type_check_expr
IntLiteral
ReturnStmt

+type_check_stmts

>>>>>>>>>>>>>>>>>>>>>>>>> + type_check_mthd_decl: [m19]
+type_check_var_decl_list
var_decl: []

+type_check_stmts
Return [new Test().m1()];
type_check_expr
MdCall helper: new Test().m1
FieldAccess : new Test() -> m1
ObjectCreate
Class: Test
ReturnStmt

+type_check_stmts

>>>>>>>>>>>>>>>>>>>>>>>>> + type_check_mthd_decl: [m12]
+type_check_var_decl_list
var_decl: [Int a]
Primitive type

+type_check_stmts
If([1,3](>))
{
  a=3;
}
else
{
  a=4;
}, Return 4;
type_check_expr
BinaryExp
IntLiteral
IntLiteral

+type_check_stmts
a=3;
AssignStmt
type_check_expr
IntLiteral
compare_var_ids
a
a
Int
Int

+type_check_stmts


+type_check_stmts
a=4;
AssignStmt
type_check_expr
IntLiteral
compare_var_ids
a
a
Int
Int

+type_check_stmts


+type_check_stmts
Return 4;
type_check_expr
IntLiteral
ReturnStmt

+type_check_stmts

>>>>>>>>>>>>>>>>>>>>>>>>> + type_check_mthd_decl: [m13]
+type_check_var_decl_list
var_decl: [Int i]
Primitive type

+type_check_stmts
i=0;, While([i,10](<)) {
  If([i,9](==))
  {
Return 9;
  }
  else
  {
i=[i,1](+);
  }
}, Return 11;
AssignStmt
type_check_expr
IntLiteral
compare_var_ids
i
i
Int
Int

+type_check_stmts
While([i,10](<)) {
  If([i,9](==))
  {
Return 9;
  }
  else
  {
i=[i,1](+);
  }
}, Return 11;
type_check_expr
BinaryExp
Var
i
compare_var_ids
i
i
Found
i
IntLiteral

+type_check_stmts
If([i,9](==))
{
  Return 9;
}
else
{
  i=[i,1](+);
}
type_check_expr
BinaryExp
Var
i
compare_var_ids
i
i
Found
i
IntLiteral

+type_check_stmts
Return 9;
type_check_expr
IntLiteral
ReturnStmt

+type_check_stmts


+type_check_stmts
i=[i,1](+);
AssignStmt
type_check_expr
BinaryExp
Var
i
compare_var_ids
i
i
Found
i
IntLiteral
compare_var_ids
i
i
Int
Int

+type_check_stmts


+type_check_stmts


+type_check_stmts
Return 11;
type_check_expr
IntLiteral
ReturnStmt

+type_check_stmts


======= JLite Program =======

class MainC{
  void main(){
    Test t;
    Int x;
    (t:Test,2)=new Test():Test;
    (x:Int,2)=[(t:Test,2):Test.m1:Test()]:Int;
    Return;
  }

} 

class Compute{

  Int square(Int a){
    Return [(a:Int,2):Int,(a:Int,2):Int](*):Int;
  }


  Int add(Int a,Int b){
    Return [(a:Int,2):Int,(b:Int,2):Int](+):Int;
  }


  Int addSquares(Int a,Int b){
    Return [add([square((a:Int,2):Int)]:Int,[square((b:Int,2):Int)]:Int)]:Int;
  }

}
class Test{
  Test t;
  Int d;

  Int m1(){
    Return 2:Int;
  }


  Int m19(){
    Return [new Test():Test.m1:Test()]:Int;
  }


  Int m12(){
    Int a;
    If([1:Int,3:Int](>):Bool)
    {
      (a:Int,2)=3:Int;
    }
    else
    {
      (a:Int,2)=4:Int;
    }
    Return 4:Int;
  }


  Int m13(){
    Int i;
    (i:Int,2)=0:Int;
    While([(i:Int,2):Int,10:Int](<):Bool) {
  If([(i:Int,2):Int,9:Int](==):Bool)
  {
    Return 9:Int;
  }
  else
  {
    (i:Int,2)=[(i:Int,2):Int,1:Int](+):Int;
  }
}
    Return 11:Int;
  }

}

======= End of JLite Program =======

========================================================
MainC main
+jlite_var_decl_lst_to_ID3
********************************************************************
MainC_main_void
AssignStmt
jlite_expr_to_IR3Expr: TypedExp
ObjectCreate
+ir3_expr_to_id3
false
AssignStmt
jlite_expr_to_IR3Expr: TypedExp
MdCall
e1: (t:Test,2):Test.m1:Test
jlite_expr_to_IR3Expr: TypedExp
TypedVarId
Local scope
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Test_m1_void
+ir3_expr_to_id3
false
+jlite_var_decl_lst_to_ID3
+jlite_var_decl_lst_to_ID3
========================================================
Compute addSquares
+jlite_var_decl_lst_to_ID3
********************************************************************
Compute_addSquares_Int_Int
ReturnStmt
jlite_expr_to_IR3Expr: TypedExp
MdCall
e1: add
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Compute_add_Int_Int
jlite_expr_to_IR3Expr: TypedExp
MdCall
e1: square
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Compute_square_Int
jlite_expr_to_IR3Expr: TypedExp
TypedVarId
Local scope
+ir3_expr_to_id3
true
1
jlite_expr_to_IR3Expr: TypedExp
MdCall
e1: square
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Compute_square_Int
jlite_expr_to_IR3Expr: TypedExp
TypedVarId
Local scope
+ir3_expr_to_id3
true
1
+ir3_expr_to_id3
true
1
+jlite_var_decl_lst_to_ID3
+jlite_var_decl_lst_to_ID3
========================================================
Compute add
+jlite_var_decl_lst_to_ID3
********************************************************************
Compute_add_Int_Int
ReturnStmt
jlite_expr_to_IR3Expr: TypedExp
BinaryExp
jlite_expr_to_IR3Expr: TypedExp
TypedVarId
Local scope
jlite_expr_to_IR3Expr: TypedExp
TypedVarId
Local scope
+ir3_expr_to_id3
true
1
+jlite_var_decl_lst_to_ID3
+jlite_var_decl_lst_to_ID3
========================================================
Compute square
+jlite_var_decl_lst_to_ID3
********************************************************************
Compute_square_Int
ReturnStmt
jlite_expr_to_IR3Expr: TypedExp
BinaryExp
jlite_expr_to_IR3Expr: TypedExp
TypedVarId
Local scope
jlite_expr_to_IR3Expr: TypedExp
TypedVarId
Local scope
+ir3_expr_to_id3
true
1
+jlite_var_decl_lst_to_ID3
+jlite_var_decl_lst_to_ID3
+jlite_var_decl_lst_to_ID3
========================================================
Test m13
+jlite_var_decl_lst_to_ID3
********************************************************************
Test_m13_void
AssignStmt
jlite_expr_to_IR3Expr: TypedExp
+ir3_expr_to_id3
false
jlite_expr_to_IR3Expr: TypedExp
BinaryExp
jlite_expr_to_IR3Expr: TypedExp
TypedVarId
Local scope
jlite_expr_to_IR3Expr: TypedExp
+ir3_expr_to_id3
true
1
+ir3_expr_to_id3
true
1
jlite_expr_to_IR3Expr: TypedExp
BinaryExp
jlite_expr_to_IR3Expr: TypedExp
TypedVarId
Local scope
jlite_expr_to_IR3Expr: TypedExp
+ir3_expr_to_id3
true
1
+ir3_expr_to_id3
true
1
ReturnStmt
jlite_expr_to_IR3Expr: TypedExp
+ir3_expr_to_id3
true
1
AssignStmt
jlite_expr_to_IR3Expr: TypedExp
BinaryExp
jlite_expr_to_IR3Expr: TypedExp
TypedVarId
Local scope
jlite_expr_to_IR3Expr: TypedExp
+ir3_expr_to_id3
true
1
+ir3_expr_to_id3
false
ReturnStmt
jlite_expr_to_IR3Expr: TypedExp
+ir3_expr_to_id3
true
1
+jlite_var_decl_lst_to_ID3
+jlite_var_decl_lst_to_ID3
========================================================
Test m12
+jlite_var_decl_lst_to_ID3
********************************************************************
Test_m12_void
jlite_expr_to_IR3Expr: TypedExp
BinaryExp
jlite_expr_to_IR3Expr: TypedExp
+ir3_expr_to_id3
true
1
jlite_expr_to_IR3Expr: TypedExp
+ir3_expr_to_id3
true
1
+ir3_expr_to_id3
true
1
AssignStmt
jlite_expr_to_IR3Expr: TypedExp
+ir3_expr_to_id3
false
AssignStmt
jlite_expr_to_IR3Expr: TypedExp
+ir3_expr_to_id3
false
ReturnStmt
jlite_expr_to_IR3Expr: TypedExp
+ir3_expr_to_id3
true
1
+jlite_var_decl_lst_to_ID3
+jlite_var_decl_lst_to_ID3
========================================================
Test m19
+jlite_var_decl_lst_to_ID3
********************************************************************
Test_m19_void
ReturnStmt
jlite_expr_to_IR3Expr: TypedExp
MdCall
e1: new Test():Test.m1:Test
jlite_expr_to_IR3Expr: TypedExp
ObjectCreate
+ir3_expr_to_id3
true
1
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Test_m1_void
+ir3_expr_to_id3
true
1
+jlite_var_decl_lst_to_ID3
+jlite_var_decl_lst_to_ID3
========================================================
Test m1
+jlite_var_decl_lst_to_ID3
********************************************************************
Test_m1_void
ReturnStmt
jlite_expr_to_IR3Expr: TypedExp
+ir3_expr_to_id3
true
1
+jlite_var_decl_lst_to_ID3
+jlite_var_decl_lst_to_ID3
+jlite_var_decl_lst_to_ID3
======= IR3 Program =======

======= CData3 ======= 

class MainC{
}

class Compute{
}

class Test{
  Test t;
  Int d;
}

=======  CMtd3 ======= 

void MainC_main_void(MainC this){
  Test t;
  Int x;
  t=new Test();
  x=[Test_m1_void(t)];
  Return;
}

Int Compute_square_Int(Compute this,Int a){
  Int _t5;
  _t5=[a,a](*);
  Return _t5;
}

Int Compute_add_Int_Int(Compute this,Int a,Int b){
  Int _t4;
  _t4=[a,b](+);
  Return _t4;
}

Int Compute_addSquares_Int_Int(Compute this,Int a,Int b){
  Int _t1;
  Int _t2;
  Int _t3;
  _t1=[Compute_square_Int(this,a)];
  _t2=[Compute_square_Int(this,b)];
  _t3=[Compute_add_Int_Int(this,_t1,_t2)];
  Return _t3;
}

Int Test_m1_void(Test this){
  Int _t19;
  _t19=2;
  Return _t19;
}

Int Test_m19_void(Test this){
  Test _t17;
  Int _t18;
  _t17=new Test();
  _t18=[Test_m1_void(_t17)];
  Return _t18;
}

Int Test_m12_void(Test this){
  Int a;
  Int _t13;
  Int _t14;
  Bool _t15;
  Int _t16;
  _t13=1;
  _t14=3;
  _t15=[_t13,_t14](>);
  If([_t15,false](==)) goto 5;
  a=3;
  goto 6;
 Label 5:
  a=4;
 Label 6:
  _t16=4;
  Return _t16;
}

Int Test_m13_void(Test this){
  Int i;
  Int _t6;
  Bool _t7;
  Int _t8;
  Bool _t9;
  Int _t10;
  Int _t11;
  Int _t12;
  i=0;
 Label 1:
  _t6=10;
  _t7=[i,_t6](<);
  If([_t7,false](==)) goto 2;
  _t8=9;
  _t9=[i,_t8](==);
  If([_t9,false](==)) goto 3;
  _t10=9;
  Return _t10;
  goto 4;
 Label 3:
  _t11=1;
  i=[i,_t11](+);
 Label 4:
  goto 1;
 Label 2:
  _t12=11;
  Return _t12;
}

======= End of IR3 Program =======

