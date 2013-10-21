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


  Int m2(){
    Int c;
    Return c;
  }


  Int m3(){
    Return d;
  }


  Int m4(){
    Int a;
    Int b;
    Int c;
    a=2;
    b=3;
    c=[a,b](+);
    Return c;
  }


  String m5(){
    Return "Hello";
  }


  Bool m6(){
    Bool ret;
    ret=false;
    Return ret;
  }


  Bool m7(){
    Bool ret;
    ret=(!)[false];
    Return ret;
  }


  Test m8(Int a){
    Return this;
  }


  Int m9(Int a,Int b,Int c){
    this.d=2;
    Return [this.d,2](+);
  }


  Int m10(){
    Return [[m8(2)].t.d,[m9([4,2](+),2,5)]](+);
  }


  Test m11(){
    Test ret;
    ret=new Test();
    ret=null;
    ret.t=null;
    ret.t.t=null;
    ret.t.d=2;
    ret.t.t.t.t.t.t.d=10;
    [[m11()].m11()];
    Return ret;
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

}

======= End of JLite Program =======


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


  Int m2(){
    Int c;
    Return (c:Int,2):Int;
  }


  Int m3(){
    Return (d:Int,1):Int;
  }


  Int m4(){
    Int a;
    Int b;
    Int c;
    (a:Int,2)=2:Int;
    (b:Int,2)=3:Int;
    (c:Int,2)=[(a:Int,2):Int,(b:Int,2):Int](+):Int;
    Return (c:Int,2):Int;
  }


  String m5(){
    Return "Hello":String;
  }


  Bool m6(){
    Bool ret;
    (ret:Bool,2)=false:Bool;
    Return (ret:Bool,2):Bool;
  }


  Bool m7(){
    Bool ret;
    (ret:Bool,2)=(!)[false:Bool]:Bool;
    Return (ret:Bool,2):Bool;
  }


  Test m8(Int a){
    Return this:Test;
  }


  Int m9(Int a,Int b,Int c){
    this:Test.d:Int=2:Int;
    Return [this:Test.d:Int,2:Int](+):Int;
  }


  Int m10(){
    Return [[m8(2:Int)]:Test.t:Test.d:Int,[m9([4:Int,2:Int](+):Int,2:Int,5:Int)]:Int](+):Int;
  }


  Test m11(){
    Test ret;
    (ret:Test,2)=new Test():Test;
    (ret:Test,2)=null:null;
    (ret:Test,2):Test.t:Test=null:null;
    (ret:Test,2):Test.t:Test.t:Test=null:null;
    (ret:Test,2):Test.t:Test.d:Int=2:Int;
    (ret:Test,2):Test.t:Test.t:Test.t:Test.t:Test.t:Test.t:Test.d:Int=10:Int;
    [[m11()]:Test.m11:Test()]:Test;
    Return (ret:Test,2):Test;
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

}

======= End of JLite Program =======

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
  Int _t39;
  _t39=2;
  Return _t39;
}

Int Test_m2_void(Test this){
  Int c;
  Return c;
}

Int Test_m3_void(Test this){
  Int _t38;
  _t38=this.d;
  Return _t38;
}

Int Test_m4_void(Test this){
  Int a;
  Int b;
  Int c;
  a=2;
  b=3;
  c=[a,b](+);
  Return c;
}

String Test_m5_void(Test this){
  String _t37;
  _t37="Hello";
  Return _t37;
}

Bool Test_m6_void(Test this){
  Bool ret;
  ret=false;
  Return ret;
}

Bool Test_m7_void(Test this){
  Bool ret;
  Bool _t36;
  _t36=false;
  ret=(!)[_t36];
  Return ret;
}

Test Test_m8_Int(Test this,Int a){
  Return this;
}

Int Test_m9_Int_Int_Int(Test this,Int a,Int b,Int c){
  Int _t32;
  Int _t33;
  Int _t34;
  Int _t35;
  _t32=2;
  this.d=_t32;
  _t33=this.d;
  _t34=2;
  _t35=[_t33,_t34](+);
  Return _t35;
}

Int Test_m10_void(Test this){
  Int _t21;
  Test _t22;
  Test _t23;
  Int _t24;
  Int _t25;
  Int _t26;
  Int _t27;
  Int _t28;
  Int _t29;
  Int _t30;
  Int _t31;
  _t21=2;
  _t22=[Test_m8_Int(this,_t21)];
  _t23=_t22.t;
  _t24=_t23.d;
  _t25=4;
  _t26=2;
  _t27=[_t25,_t26](+);
  _t28=2;
  _t29=5;
  _t30=[Test_m9_Int_Int_Int(this,_t27,_t28,_t29)];
  _t31=[_t24,_t30](+);
  Return _t31;
}

Test Test_m11_void(Test this){
  Test ret;
  Test _t10;
  Test _t11;
  Int _t12;
  Test _t13;
  Test _t14;
  Test _t15;
  Test _t16;
  Test _t17;
  Test _t18;
  Int _t19;
  Test _t20;
  ret=new Test();
  ret=null;
  ret.t=null;
  _t10=ret.t;
  _t10.t=null;
  _t11=ret.t;
  _t12=2;
  _t11.d=_t12;
  _t13=ret.t;
  _t14=_t13.t;
  _t15=_t14.t;
  _t16=_t15.t;
  _t17=_t16.t;
  _t18=_t17.t;
  _t19=10;
  _t18.d=_t19;
  _t20=[Test_m11_void(this)];
  [Test_m11_void(_t20)];
  Return ret;
}

Int Test_m12_void(Test this){
  Int a;
  Int _t6;
  Int _t7;
  Bool _t8;
  Int _t9;
  _t6=1;
  _t7=3;
  _t8=[_t6,_t7](>);
  If([_t8,false](==)) goto 1;
  a=3;
  goto 2;
 Label 1:
  a=4;
 Label 2:
  _t9=4;
  Return _t9;
}

======= End of IR3 Program =======

