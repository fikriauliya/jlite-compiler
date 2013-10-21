Parsing...
simple_input.java

======= JLite Program =======

class MainC{
  void main(){
    Functional fo;
    Return;
  }

} 

class Functional{
  Int c;
  Hello b;
  Int a;

  Int f(Int b){
    readln(b);
    println(b);
    Return 3;
  }


  Int g(Int x){
    Return x;
  }


  Int g(Int x,Int y){
    Return x;
  }

}
class Hello{
  Hello e;
  Hello d;
  Int c;

  Int h(Int c){
    Return d.e.d.d.e.c;
  }


  Hello s(){
    Return new Hello().d;
  }


  Hello s2(){
    Return [s()].d;
  }


  Hello s3(){
    Return this.d.e.d;
  }


  Hello s4(){
    Return [this.d.e.d.s3()];
  }


  Hello s5(){
    Return [new Hello().s3()];
  }


  Hello s6(){
    Return [[s5()].s4()];
  }


  Hello s7(){
    Return [this.s4()];
  }

}
class Hello1{
  Hello d;
  Int c;

  Int h(Int c){
    Hello d;
    Hello s;
    Int f;
    readln(f);
    println([[f,1](+),3](+));
    println((-)[2]);
    Return [w(4)];
  }


  Hello w(){
    Hello h;
    Return h;
  }


  Int w(Int a){
    Return 0;
  }


  Int h(Int c,String s){
    Return 5;
  }


  Int h(Int c,Int s){
    Return 5;
  }


  Int h(Int c,Hello s){
    Return 5;
  }


  Hello1 h(Int c,Hello1 s){
    Return s;
  }

}
class Hello2{

  Int m2(){
    Int ret;
    String ret2;
    String ret3;
    ret=2;
    ret2="Hello";
    ret3=ret2;
    Return ret;
  }


  void m3(){
    [m2()];
    Return;
  }


  void m4(){
    Hello w;
    w.c=2;
    Return;
  }


  Int m5(){
    Int a;
    Int b;
    a=2;
    b=3;
    If([a,b](==))
    {
      a=3;
    }
    else
    {
      b=2;
    }
    If([a,b](>=))
    {
      Return [a,3](+);
    }
    else
    {
      Return 2;
    }
  }


  Int m6(){
    Int d;
    While([1,1](==)) {
  If([2,2](==))
  {
    Return 3;
  }
  else
  {
    d=3;
  }
  Return 3;
}
    Return 4;
  }

}
class ScopeTest{
  Int a;

  String m1(String a){
    Return a;
  }


  Int m2(Int a){
    String b;
    Return [a,a](+);
  }

}

======= End of JLite Program =======


======= JLite Program =======

class MainC{
  void main(){
    Functional fo;
    Return;
  }

} 

class Functional{
  Int c;
  Hello b;
  Int a;

  Int f(Int b){
    readln((b:Int,2));
    println((b:Int,2):Int);
    Return 3:Int;
  }


  Int g(Int x){
    Return (x:Int,2):Int;
  }


  Int g(Int x,Int y){
    Return (x:Int,2):Int;
  }

}
class Hello{
  Hello e;
  Hello d;
  Int c;

  Int h(Int c){
    Return (d:Hello,1):Hello.e:Hello.d:Hello.d:Hello.e:Hello.c:Int;
  }


  Hello s(){
    Return new Hello():Hello.d:Hello;
  }


  Hello s2(){
    Return [s()]:Hello.d:Hello;
  }


  Hello s3(){
    Return this:Hello.d:Hello.e:Hello.d:Hello;
  }


  Hello s4(){
    Return [this:Hello.d:Hello.e:Hello.d:Hello.s3:Hello()]:Hello;
  }


  Hello s5(){
    Return [new Hello():Hello.s3:Hello()]:Hello;
  }


  Hello s6(){
    Return [[s5()]:Hello.s4:Hello()]:Hello;
  }


  Hello s7(){
    Return [this:Hello.s4:Hello()]:Hello;
  }

}
class Hello1{
  Hello d;
  Int c;

  Int h(Int c){
    Hello d;
    Hello s;
    Int f;
    readln((f:Int,2));
    println([[(f:Int,2):Int,1:Int](+):Int,3:Int](+):Int);
    println((-)[2:Int]:Int);
    Return [w(4:Int)]:Int;
  }


  Hello w(){
    Hello h;
    Return (h:Hello,2):Hello;
  }


  Int w(Int a){
    Return 0:Int;
  }


  Int h(Int c,String s){
    Return 5:Int;
  }


  Int h(Int c,Int s){
    Return 5:Int;
  }


  Int h(Int c,Hello s){
    Return 5:Int;
  }


  Hello1 h(Int c,Hello1 s){
    Return (s:Hello1,2):Hello1;
  }

}
class Hello2{

  Int m2(){
    Int ret;
    String ret2;
    String ret3;
    (ret:Int,2)=2:Int;
    (ret2:String,2)="Hello":String;
    (ret3:String,2)=(ret2:String,2):String;
    Return (ret:Int,2):Int;
  }


  void m3(){
    [m2()]:Int;
    Return;
  }


  void m4(){
    Hello w;
    (w:Hello,2):Hello.c:Int=2:Int;
    Return;
  }


  Int m5(){
    Int a;
    Int b;
    (a:Int,2)=2:Int;
    (b:Int,2)=3:Int;
    If([(a:Int,2):Int,(b:Int,2):Int](==):Bool)
    {
      (a:Int,2)=3:Int;
    }
    else
    {
      (b:Int,2)=2:Int;
    }
    If([(a:Int,2):Int,(b:Int,2):Int](>=):Bool)
    {
      Return [(a:Int,2):Int,3:Int](+):Int;
    }
    else
    {
      Return 2:Int;
    }
  }


  Int m6(){
    Int d;
    While([1:Int,1:Int](==):Bool) {
  If([2:Int,2:Int](==):Bool)
  {
    Return 3:Int;
  }
  else
  {
    (d:Int,2)=3:Int;
  }
  Return 3:Int;
}
    Return 4:Int;
  }

}
class ScopeTest{
  Int a;

  String m1(String a){
    Return (a:String,2):String;
  }


  Int m2(Int a){
    String b;
    Return [(a:Int,2):Int,(a:Int,2):Int](+):Int;
  }

}

======= End of JLite Program =======

======= IR3 Program =======

======= CData3 ======= 

class MainC{
}

class Functional{
  Int c;
  Hello b;
  Int a;
}

class Hello{
  Hello e;
  Hello d;
  Int c;
}

class Hello1{
  Hello d;
  Int c;
}

class Hello2{
}

class ScopeTest{
  Int a;
}

=======  CMtd3 ======= 

void MainC_main_void(MainC this){
  Functional fo;
  Return;
}

Int Functional_f_Int(Functional this,Int b){
  Int _t1;
  readln(b);
  println(b);
  _t1=3;
  Return _t1;
}

Int Functional_g_Int(Functional this,Int x){
  Return x;
}

Int Functional_g_Int_Int(Functional this,Int x,Int y){
  Return x;
}

Int Hello_h_Int(Hello this,Int c){
  Hello _t18;
  Hello _t19;
  Hello _t20;
  Hello _t21;
  Hello _t22;
  Int _t23;
  _t18=this.d;
  _t19=_t18.e;
  _t20=_t19.d;
  _t21=_t20.d;
  _t22=_t21.e;
  _t23=_t22.c;
  Return _t23;
}

Hello Hello_s_void(Hello this){
  Hello _t16;
  Hello _t17;
  _t16=new Hello();
  _t17=_t16.d;
  Return _t17;
}

Hello Hello_s2_void(Hello this){
  Hello _t14;
  Hello _t15;
  _t14=[Hello_s_void(this)];
  _t15=_t14.d;
  Return _t15;
}

Hello Hello_s3_void(Hello this){
  Hello _t11;
  Hello _t12;
  Hello _t13;
  _t11=this.d;
  _t12=_t11.e;
  _t13=_t12.d;
  Return _t13;
}

Hello Hello_s4_void(Hello this){
  Hello _t7;
  Hello _t8;
  Hello _t9;
  Hello _t10;
  _t7=this.d;
  _t8=_t7.e;
  _t9=_t8.d;
  _t10=[Hello_s3_void(_t9)];
  Return _t10;
}

Hello Hello_s5_void(Hello this){
  Hello _t5;
  Hello _t6;
  _t5=new Hello();
  _t6=[Hello_s3_void(_t5)];
  Return _t6;
}

Hello Hello_s6_void(Hello this){
  Hello _t3;
  Hello _t4;
  _t3=[Hello_s5_void(this)];
  _t4=[Hello_s4_void(_t3)];
  Return _t4;
}

Hello Hello_s7_void(Hello this){
  Hello _t2;
  _t2=[Hello_s4_void(this)];
  Return _t2;
}

Int Hello1_h_Int(Hello1 this,Int c){
  Hello d;
  Hello s;
  Int f;
  Int _t28;
  Int _t29;
  Int _t30;
  Int _t31;
  Int _t32;
  Int _t33;
  Int _t34;
  Int _t35;
  readln(f);
  _t28=1;
  _t29=[f,_t28](+);
  _t30=3;
  _t31=[_t29,_t30](+);
  println(_t31);
  _t32=2;
  _t33=(-)[_t32];
  println(_t33);
  _t34=4;
  _t35=[Hello1_w_Int(this,_t34)];
  Return _t35;
}

Hello Hello1_w_void(Hello1 this){
  Hello h;
  Return h;
}

Int Hello1_w_Int(Hello1 this,Int a){
  Int _t27;
  _t27=0;
  Return _t27;
}

Int Hello1_h_Int_String(Hello1 this,Int c,String s){
  Int _t26;
  _t26=5;
  Return _t26;
}

Int Hello1_h_Int_Int(Hello1 this,Int c,Int s){
  Int _t25;
  _t25=5;
  Return _t25;
}

Int Hello1_h_Int_Hello(Hello1 this,Int c,Hello s){
  Int _t24;
  _t24=5;
  Return _t24;
}

Hello1 Hello1_h_Int_Hello1(Hello1 this,Int c,Hello1 s){
  Return s;
}

Int Hello2_m2_void(Hello2 this){
  Int ret;
  String ret2;
  String ret3;
  ret=2;
  ret2="Hello";
  ret3=ret2;
  Return ret;
}

void Hello2_m3_void(Hello2 this){
  [Hello2_m2_void(this)];
  Return;
}

void Hello2_m4_void(Hello2 this){
  Hello w;
  Int _t50;
  _t50=2;
  w.c=_t50;
  Return;
}

Int Hello2_m5_void(Hello2 this){
  Int a;
  Int b;
  Bool _t45;
  Bool _t46;
  Int _t47;
  Int _t48;
  Int _t49;
  a=2;
  b=3;
  _t45=[a,b](==);
  If([_t45,false](==)) goto 5;
  a=3;
  goto 6;
 Label 5:
  b=2;
 Label 6:
  _t46=[a,b](>=);
  If([_t46,false](==)) goto 7;
  _t47=3;
  _t48=[a,_t47](+);
  Return _t48;
  goto 8;
 Label 7:
  _t49=2;
  Return _t49;
 Label 8:
}

Int Hello2_m6_void(Hello2 this){
  Int d;
  Int _t36;
  Int _t37;
  Bool _t38;
  Int _t39;
  Int _t40;
  Bool _t41;
  Int _t42;
  Int _t43;
  Int _t44;
 Label 1:
  _t36=1;
  _t37=1;
  _t38=[_t36,_t37](==);
  If([_t38,false](==)) goto 2;
  _t39=2;
  _t40=2;
  _t41=[_t39,_t40](==);
  If([_t41,false](==)) goto 3;
  _t42=3;
  Return _t42;
  goto 4;
 Label 3:
  d=3;
 Label 4:
  _t43=3;
  Return _t43;
  goto 1;
 Label 2:
  _t44=4;
  Return _t44;
}

String ScopeTest_m1_String(ScopeTest this,String a){
  Return a;
}

Int ScopeTest_m2_Int(ScopeTest this,Int a){
  String b;
  Int _t51;
  _t51=[a,a](+);
  Return _t51;
}

======= End of IR3 Program =======

