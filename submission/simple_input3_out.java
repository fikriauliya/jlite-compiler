Parsing...
simple_input3.java

======= JLite Program =======

class Main{
  void main(){
    Return;
  }

} 

class Test{
  Test t;
  Int d;

  Int m1(){
    Return 2;
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


  Int m14(){
    Int a;
    Int b;
    While([a,b](<)) {
  a=1;
  If([a,0](>))
  {
    Return a;
  }
  else
  {
    a=2;
  }
}
    Return a;
  }


  Int m15(){
    Int d;
    readln(d);
    Return d;
  }


  void m16(){
    println([1,2](+));
    Return;
  }


  void m17(){
    [m16()];
  }


  Test m18(){
    Return [m11()].t;
  }


  Bool m19(){
    Bool res;
    Int a;
    Int b;
    Int c;
    Int d;
    Bool a1;
    Bool a2;
    Bool a3;
    res=[[[a,b](>),[b,c](>)](&&),[c,d](>)](&&);
    println(res);
    res=[a1,[a2,a3](&&)](||);
    Return res;
  }


  Int m20(){
    Return [new Test().m1()];
  }

}

======= End of JLite Program =======


======= JLite Program =======

class Main{
  void main(){
    Return;
  }

} 

class Test{
  Test t;
  Int d;

  Int m1(){
    Return 2:Int;
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


  Int m14(){
    Int a;
    Int b;
    While([(a:Int,2):Int,(b:Int,2):Int](<):Bool) {
  (a:Int,2)=1:Int;
  If([(a:Int,2):Int,0:Int](>):Bool)
  {
    Return (a:Int,2):Int;
  }
  else
  {
    (a:Int,2)=2:Int;
  }
}
    Return (a:Int,2):Int;
  }


  Int m15(){
    Int d;
    readln((d:Int,2));
    Return (d:Int,2):Int;
  }


  void m16(){
    println([1:Int,2:Int](+):Int);
    Return;
  }


  void m17(){
    [m16()]:void;
  }


  Test m18(){
    Return [m11()]:Test.t:Test;
  }


  Bool m19(){
    Bool res;
    Int a;
    Int b;
    Int c;
    Int d;
    Bool a1;
    Bool a2;
    Bool a3;
    (res:Bool,2)=[[[(a:Int,2):Int,(b:Int,2):Int](>):Bool,[(b:Int,2):Int,(c:Int,2):Int](>):Bool](&&):Bool,[(c:Int,2):Int,(d:Int,2):Int](>):Bool](&&):Bool;
    println((res:Bool,2):Bool);
    (res:Bool,2)=[(a1:Bool,2):Bool,[(a2:Bool,2):Bool,(a3:Bool,2):Bool](&&):Bool](||):Bool;
    Return (res:Bool,2):Bool;
  }


  Int m20(){
    Return [new Test():Test.m1:Test()]:Int;
  }

}

======= End of JLite Program =======

======= IR3 Program =======

======= CData3 ======= 

class Main{
}

class Test{
  Test t;
  Int d;
}

=======  CMtd3 ======= 

void Main_main_void(Main this){
  Return;
}

Int Test_m1_void(Test this){
  Int _t38;
  _t38=2;
  Return _t38;
}

Test Test_m11_void(Test this){
  Test ret;
  Test _t27;
  Test _t28;
  Int _t29;
  Test _t30;
  Test _t31;
  Test _t32;
  Test _t33;
  Test _t34;
  Test _t35;
  Int _t36;
  Test _t37;
  ret=new Test();
  ret=null;
  ret.t=null;
  _t27=ret.t;
  _t27.t=null;
  _t28=ret.t;
  _t29=2;
  _t28.d=_t29;
  _t30=ret.t;
  _t31=_t30.t;
  _t32=_t31.t;
  _t33=_t32.t;
  _t34=_t33.t;
  _t35=_t34.t;
  _t36=10;
  _t35.d=_t36;
  _t37=[Test_m11_void(this)];
  [Test_m11_void(_t37)];
  Return ret;
}

Int Test_m13_void(Test this){
  Int i;
  Int _t20;
  Bool _t21;
  Int _t22;
  Bool _t23;
  Int _t24;
  Int _t25;
  Int _t26;
  i=0;
 Label 13:
  _t20=10;
  _t21=[i,_t20](<);
  If([_t21,false](==)) goto 14;
  _t22=9;
  _t23=[i,_t22](==);
  If([_t23,false](==)) goto 15;
  _t24=9;
  Return _t24;
  goto 16;
 Label 15:
  _t25=1;
  i=[i,_t25](+);
 Label 16:
  goto 13;
 Label 14:
  _t26=11;
  Return _t26;
}

Int Test_m14_void(Test this){
  Int a;
  Int b;
  Bool _t17;
  Int _t18;
  Bool _t19;
 Label 9:
  _t17=[a,b](<);
  If([_t17,false](==)) goto 10;
  a=1;
  _t18=0;
  _t19=[a,_t18](>);
  If([_t19,false](==)) goto 11;
  Return a;
  goto 12;
 Label 11:
  a=2;
 Label 12:
  goto 9;
 Label 10:
  Return a;
}

Int Test_m15_void(Test this){
  Int d;
  readln(d);
  Return d;
}

void Test_m16_void(Test this){
  Int _t14;
  Int _t15;
  Int _t16;
  _t14=1;
  _t15=2;
  _t16=[_t14,_t15](+);
  println(_t16);
  Return;
}

void Test_m17_void(Test this){
  [Test_m16_void(this)];
}

Test Test_m18_void(Test this){
  Test _t12;
  Test _t13;
  _t12=[Test_m11_void(this)];
  _t13=_t12.t;
  Return _t13;
}

Bool Test_m19_void(Test this){
  Bool res;
  Int a;
  Int b;
  Int c;
  Int d;
  Bool a1;
  Bool a2;
  Bool a3;
  Bool _t3;
  Bool _t5;
  Bool _t6;
  Bool _t8;
  Bool _t11;
  _t3=[a,b](>);
  If([_t3,false](==)) goto 1;
  _t5=[b,c](>);
  _t4=_t5;
  goto 2;
 Label 1:
  _t4=false;
 Label 2:
  _t6=_t4;
  If([_t6,false](==)) goto 3;
  _t8=[c,d](>);
  _t7=_t8;
  goto 4;
 Label 3:
  _t7=false;
 Label 4:
  res=_t7;
  println(res);
  If([a1,true](==)) goto 5;
  If([a2,false](==)) goto 7;
  _t10=a3;
  goto 8;
 Label 7:
  _t10=false;
 Label 8:
  _t11=_t10;
  _t9=_t11;
  goto 6;
 Label 5:
  _t9=true;
 Label 6:
  res=_t9;
  Return res;
}

Int Test_m20_void(Test this){
  Test _t1;
  Int _t2;
  _t1=new Test();
  _t2=[Test_m1_void(_t1)];
  Return _t2;
}

======= End of IR3 Program =======

