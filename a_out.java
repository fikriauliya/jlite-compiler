Parsing...
a.j

======= JLite Program =======

class MainC{
  void main(Int c,Int d){
    Functional fo;
    Functional2 foo;
    Int a;
    println("Hello world");
    fo=null;
    fo=new Functional();
    fo.a=5;
    [fo.g(c)];
    foo.ff=fo;
    a=[foo.ff.f(5)];
    fo=null;
    new Functional().a=10;
    [new Functional().f(5)];
    Return;
  }

} 

class Functional{
  Int a;

  Int f(Int b){
    a=4;
    readln(a);
    [g(b)];
    [this.h()];
    Return 3;
  }


  Int g(Int x){
    If([1,2](==))
    {
      println("hello");
    }
    else
    {
      println("world");
    }
    Return 300;
  }


  Functional hee(Int f){
    Return null;
  }


  Int h(){
    While([1,2](==)) {
  println("QQQ");
  Return 333;
}
    println("RRR");
    this.a=(-)[(-)[(-)[2]]];
    Return this.a;
  }

}
class Functional2{
  Int d;
  Functional2 f2;
  Functional ff;

  Int hahaha(){
    readln(d);
    Return d;
  }


  void test(){
    f2.f2.f2.f2=null;
  }

}

======= End of JLite Program =======


======= JLite Program =======

class MainC{
  void main(Int c,Int d){
    Functional fo;
    Functional2 foo;
    Int a;
    println("Hello world":String);
    (fo:Functional,2)=null:null;
    (fo:Functional,2)=new Functional():Functional;
    (fo:Functional,2):Functional.a:Int=5:Int;
    [(fo:Functional,2):Functional.g:Functional((c:Int,2):Int)]:Int;
    (foo:Functional2,2):Functional2.ff:Functional=(fo:Functional,2):Functional;
    (a:Int,2)=[(foo:Functional2,2):Functional2.ff:Functional.f:Functional(5:Int)]:Int;
    (fo:Functional,2)=null:null;
    new Functional():Functional.a:Int=10:Int;
    [new Functional():Functional.f:Functional(5:Int)]:Int;
    Return;
  }

} 

class Functional{
  Int a;

  Int f(Int b){
    (a:Int,1)=4:Int;
    readln((a:Int,1));
    [g((b:Int,2):Int)]:Int;
    [this:Functional.h:Functional()]:Int;
    Return 3:Int;
  }


  Int g(Int x){
    If([1:Int,2:Int](==):Bool)
    {
      println("hello":String);
    }
    else
    {
      println("world":String);
    }
    Return 300:Int;
  }


  Functional hee(Int f){
    Return null:null;
  }


  Int h(){
    While([1:Int,2:Int](==):Bool) {
  println("QQQ":String);
  Return 333:Int;
}
    println("RRR":String);
    this:Functional.a:Int=(-)[(-)[(-)[2:Int]:Int]:Int]:Int;
    Return this:Functional.a:Int;
  }

}
class Functional2{
  Int d;
  Functional2 f2;
  Functional ff;

  Int hahaha(){
    readln((d:Int,1));
    Return (d:Int,1):Int;
  }


  void test(){
    (f2:Functional2,1):Functional2.f2:Functional2.f2:Functional2.f2:Functional2=null:null;
  }

}

======= End of JLite Program =======

======= IR3 Program =======

======= CData3 ======= 

class MainC{
}

class Functional{
  Int a;
}

class Functional2{
  Int d;
  Functional2 f2;
  Functional ff;
}

=======  CMtd3 ======= 

void MainC_main_Int_Int(MainC this,Int c,Int d){
  Functional fo;
  Functional2 foo;
  Int a;
  String _t1;
  Int _t2;
  Functional _t3;
  Int _t4;
  Functional _t5;
  Int _t6;
  Functional _t7;
  Int _t8;
  _t1="Hello world";
  println(_t1);
  fo=null;
  fo=new Functional();
  _t2=5;
  fo.a=_t2;
  [Functional_g_Int(fo,c)];
  foo.ff=fo;
  _t3=foo.ff;
  _t4=5;
  a=[Functional_f_Int(_t3,_t4)];
  fo=null;
  _t5=new Functional();
  _t6=10;
  _t5.a=_t6;
  _t7=new Functional();
  _t8=5;
  [Functional_f_Int(_t7,_t8)];
  Return;
}

Int Functional_f_Int(Functional this,Int b){
  Int _t25;
  Int _t26;
  this.a=4;
  _t25=this.a;
  readln(_t25);
  [Functional_g_Int(this,b)];
  [Functional_h_void(this)];
  _t26=3;
  Return _t26;
}

Int Functional_g_Int(Functional this,Int x){
  Int _t19;
  Int _t20;
  Bool _t21;
  String _t22;
  String _t23;
  Int _t24;
  _t19=1;
  _t20=2;
  _t21=[_t19,_t20](==);
  If([_t21,false](==)) goto 3;
  _t22="hello";
  println(_t22);
  goto 4;
 Label 3:
  _t23="world";
  println(_t23);
 Label 4:
  _t24=300;
  Return _t24;
}

Functional Functional_hee_Int(Functional this,Int f){
  Return null;
}

Int Functional_h_void(Functional this){
  Int _t9;
  Int _t10;
  Bool _t11;
  String _t12;
  Int _t13;
  String _t14;
  Int _t15;
  Int _t16;
  Int _t17;
  Int _t18;
 Label 1:
  _t9=1;
  _t10=2;
  _t11=[_t9,_t10](==);
  If([_t11,false](==)) goto 2;
  _t12="QQQ";
  println(_t12);
  _t13=333;
  Return _t13;
  goto 1;
 Label 2:
  _t14="RRR";
  println(_t14);
  _t15=2;
  _t16=(-)[_t15];
  _t17=(-)[_t16];
  this.a=(-)[_t17];
  _t18=this.a;
  Return _t18;
}

Int Functional2_hahaha_void(Functional2 this){
  Int _t30;
  Int _t31;
  _t30=this.d;
  readln(_t30);
  _t31=this.d;
  Return _t31;
}

void Functional2_test_void(Functional2 this){
  Functional2 _t27;
  Functional2 _t28;
  Functional2 _t29;
  _t27=this.f2;
  _t28=_t27.f2;
  _t29=_t28.f2;
  _t29.f2=null;
}

======= End of IR3 Program =======

