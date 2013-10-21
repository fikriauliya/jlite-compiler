Parsing...
p2.jlite

======= JLite Program =======

class P2{
  void main(){
    Int dummy1;
    Int dummy2;
    Int dummy3;
    String s1;
    String s2;
    C1 c1;
    C2 c2;
    C3 c3;
    dummy1=2;
    dummy2=3;
    dummy3=10;
    [c1.m1()];
    [c1.m2([dummy1,dummy2](+))];
    [c1.m3([dummy1,dummy2](+),dummy3)];
    println(s1);
  }

} 

class C1{

  void m1(){
    println("Hello");
    Return;
  }


  void m2(Int a){
    println("Hello");
  }


  void m3(Int a,Int b){
    println("Hello");
  }

}
class C2{
  String a;

  Int m1(){
    Return [m2(10)];
  }


  Int m2(Int x){
    Return x;
  }


  C3 m3(){
    Return new C3();
  }


  C3 m3(Int a){
    Return [this.m3()];
  }


  C3 m3(Bool a){
    Return null;
  }


  String m4(){
    Return a;
  }

}
class C3{


}

======= End of JLite Program =======


======= JLite Program =======

class P2{
  void main(){
    Int dummy1;
    Int dummy2;
    Int dummy3;
    String s1;
    String s2;
    C1 c1;
    C2 c2;
    C3 c3;
    (dummy1:Int,2)=2:Int;
    (dummy2:Int,2)=3:Int;
    (dummy3:Int,2)=10:Int;
    [(c1:C1,2):C1.m1:C1()]:void;
    [(c1:C1,2):C1.m2:C1([(dummy1:Int,2):Int,(dummy2:Int,2):Int](+):Int)]:void;
    [(c1:C1,2):C1.m3:C1([(dummy1:Int,2):Int,(dummy2:Int,2):Int](+):Int,(dummy3:Int,2):Int)]:void;
    println((s1:String,2):String);
  }

} 

class C1{

  void m1(){
    println("Hello":String);
    Return;
  }


  void m2(Int a){
    println("Hello":String);
  }


  void m3(Int a,Int b){
    println("Hello":String);
  }

}
class C2{
  String a;

  Int m1(){
    Return [m2(10:Int)]:Int;
  }


  Int m2(Int x){
    Return (x:Int,2):Int;
  }


  C3 m3(){
    Return new C3():C3;
  }


  C3 m3(Int a){
    Return [this:C2.m3:C2()]:C3;
  }


  C3 m3(Bool a){
    Return null:null;
  }


  String m4(){
    Return (a:String,1):String;
  }

}
class C3{


}

======= End of JLite Program =======

======= IR3 Program =======

======= CData3 ======= 

class P2{
}

class C1{
}

class C2{
  String a;
}

class C3{
}

=======  CMtd3 ======= 

void P2_main_void(P2 this){
  Int dummy1;
  Int dummy2;
  Int dummy3;
  String s1;
  String s2;
  C1 c1;
  C2 c2;
  C3 c3;
  Int _t1;
  Int _t2;
  dummy1=2;
  dummy2=3;
  dummy3=10;
  [C1_m1_void(c1)];
  _t1=[dummy1,dummy2](+);
  [C1_m2_Int(c1,_t1)];
  _t2=[dummy1,dummy2](+);
  [C1_m3_Int_Int(c1,_t2,dummy3)];
  println(s1);
}

void C1_m1_void(C1 this){
  String _t5;
  _t5="Hello";
  println(_t5);
  Return;
}

void C1_m2_Int(C1 this,Int a){
  String _t4;
  _t4="Hello";
  println(_t4);
}

void C1_m3_Int_Int(C1 this,Int a,Int b){
  String _t3;
  _t3="Hello";
  println(_t3);
}

Int C2_m1_void(C2 this){
  Int _t9;
  Int _t10;
  _t9=10;
  _t10=[C2_m2_Int(this,_t9)];
  Return _t10;
}

Int C2_m2_Int(C2 this,Int x){
  Return x;
}

C3 C2_m3_void(C2 this){
  C3 _t8;
  _t8=new C3();
  Return _t8;
}

C3 C2_m3_Int(C2 this,Int a){
  C3 _t7;
  _t7=[C2_m3_void(this)];
  Return _t7;
}

C3 C2_m3_Bool(C2 this,Bool a){
  Return null;
}

String C2_m4_void(C2 this){
  String _t6;
  _t6=this.a;
  Return _t6;
}

======= End of IR3 Program =======

