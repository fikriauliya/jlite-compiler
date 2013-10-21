Parsing...
p1.jlite

======= JLite Program =======

class P1{
  void main(Int a){
    Int dummy1;
    String dummy2;
    Int x;
    AlwaysDivide ad;
    AlwaysDivideRecursive adr;
    println("Hello world");
    x=0;
    readln(x);
    ad=new AlwaysDivide();
    ad.min=x;
    [ad.printDivide(100)];
    [ad.doNothing()];
    adr=new AlwaysDivideRecursive();
    adr.min=x;
    [adr.printDivideRecursive(100)];
    println("Goodbye cruel world!");
  }

} 

class DivideByX{
  Int x;

  Int divide(Int a){
    Return [a,x](/);
  }

}
class AlwaysDivide{
  Int min;

  void printDivide(Int start){
    Int dummy1;
    String dummy2;
    While([start,min](>=)) {
  println(start);
  start=[start,2](/);
}
  }


  void doNothing(){
    While(true) {
  println("qqq");
}
  }

}
class AlwaysDivideRecursive{
  Int min;

  void printDivideRecursive(Int start){
    If([start,min](>=))
    {
      println(start);
      [printDivideRecursive([start,2](/))];
    }
    else
    {
      Return;
    }
  }

}
class AlwaysDivideIterative{

  Int printDivideIterative(Int start,Int end){
    While([start,end](>)) {
  If([start,end](==))
  {
    Return 9000;
  }
  else
  {
    start=[start,2](/);
  }
}
    Return 42;
  }


  AlwaysDivideIterative get(){
    If(true)
    {
      Return new AlwaysDivideIterative();
    }
    else
    {
      println("aaa");
      If(false)
      {
        println("falsefalse");
        Return null;
      }
      else
      {
        println("falsetrue");
        Return null;
      }
    }
  }

}
class AlwaysDivideRecursiveFixed{

  Int printDivideRecursive(Int start){
    If([start,2](>=))
    {
      println(start);
      Return [this.printDivideRecursive([start,2](/))];
    }
    else
    {
      Return 2;
    }
  }

}

======= End of JLite Program =======


======= JLite Program =======

class P1{
  void main(Int a){
    Int dummy1;
    String dummy2;
    Int x;
    AlwaysDivide ad;
    AlwaysDivideRecursive adr;
    println("Hello world":String);
    (x:Int,2)=0:Int;
    readln((x:Int,2));
    (ad:AlwaysDivide,2)=new AlwaysDivide():AlwaysDivide;
    (ad:AlwaysDivide,2):AlwaysDivide.min:Int=(x:Int,2):Int;
    [(ad:AlwaysDivide,2):AlwaysDivide.printDivide:AlwaysDivide(100:Int)]:void;
    [(ad:AlwaysDivide,2):AlwaysDivide.doNothing:AlwaysDivide()]:void;
    (adr:AlwaysDivideRecursive,2)=new AlwaysDivideRecursive():AlwaysDivideRecursive;
    (adr:AlwaysDivideRecursive,2):AlwaysDivideRecursive.min:Int=(x:Int,2):Int;
    [(adr:AlwaysDivideRecursive,2):AlwaysDivideRecursive.printDivideRecursive:AlwaysDivideRecursive(100:Int)]:void;
    println("Goodbye cruel world!":String);
  }

} 

class DivideByX{
  Int x;

  Int divide(Int a){
    Return [(a:Int,2):Int,(x:Int,1):Int](/):Int;
  }

}
class AlwaysDivide{
  Int min;

  void printDivide(Int start){
    Int dummy1;
    String dummy2;
    While([(start:Int,2):Int,(min:Int,1):Int](>=):Bool) {
  println((start:Int,2):Int);
  (start:Int,2)=[(start:Int,2):Int,2:Int](/):Int;
}
  }


  void doNothing(){
    While(true:Bool) {
  println("qqq":String);
}
  }

}
class AlwaysDivideRecursive{
  Int min;

  void printDivideRecursive(Int start){
    If([(start:Int,2):Int,(min:Int,1):Int](>=):Bool)
    {
      println((start:Int,2):Int);
      [printDivideRecursive([(start:Int,2):Int,2:Int](/):Int)]:void;
    }
    else
    {
      Return;
    }
  }

}
class AlwaysDivideIterative{

  Int printDivideIterative(Int start,Int end){
    While([(start:Int,2):Int,(end:Int,2):Int](>):Bool) {
  If([(start:Int,2):Int,(end:Int,2):Int](==):Bool)
  {
    Return 9000:Int;
  }
  else
  {
    (start:Int,2)=[(start:Int,2):Int,2:Int](/):Int;
  }
}
    Return 42:Int;
  }


  AlwaysDivideIterative get(){
    If(true:Bool)
    {
      Return new AlwaysDivideIterative():AlwaysDivideIterative;
    }
    else
    {
      println("aaa":String);
      If(false:Bool)
      {
        println("falsefalse":String);
        Return null:null;
      }
      else
      {
        println("falsetrue":String);
        Return null:null;
      }
    }
  }

}
class AlwaysDivideRecursiveFixed{

  Int printDivideRecursive(Int start){
    If([(start:Int,2):Int,2:Int](>=):Bool)
    {
      println((start:Int,2):Int);
      Return [this:AlwaysDivideRecursiveFixed.printDivideRecursive:AlwaysDivideRecursiveFixed([(start:Int,2):Int,2:Int](/):Int)]:Int;
    }
    else
    {
      Return 2:Int;
    }
  }

}

======= End of JLite Program =======

======= IR3 Program =======

======= CData3 ======= 

class P1{
}

class DivideByX{
  Int x;
}

class AlwaysDivide{
  Int min;
}

class AlwaysDivideRecursive{
  Int min;
}

class AlwaysDivideIterative{
}

class AlwaysDivideRecursiveFixed{
}

=======  CMtd3 ======= 

void P1_main_Int(P1 this,Int a){
  Int dummy1;
  String dummy2;
  Int x;
  AlwaysDivide ad;
  AlwaysDivideRecursive adr;
  String _t1;
  Int _t2;
  Int _t3;
  String _t4;
  _t1="Hello world";
  println(_t1);
  x=0;
  readln(x);
  ad=new AlwaysDivide();
  ad.min=x;
  _t2=100;
  [AlwaysDivide_printDivide_Int(ad,_t2)];
  [AlwaysDivide_doNothing_void(ad)];
  adr=new AlwaysDivideRecursive();
  adr.min=x;
  _t3=100;
  [AlwaysDivideRecursive_printDivideRecursive_Int(adr,_t3)];
  _t4="Goodbye cruel world!";
  println(_t4);
}

Int DivideByX_divide_Int(DivideByX this,Int a){
  Int _t5;
  Int _t6;
  _t5=this.x;
  _t6=[a,_t5](/);
  Return _t6;
}

void AlwaysDivide_printDivide_Int(AlwaysDivide this,Int start){
  Int dummy1;
  String dummy2;
  Int _t9;
  Bool _t10;
  Int _t11;
 Label 3:
  _t9=this.min;
  _t10=[start,_t9](>=);
  If([_t10,false](==)) goto 4;
  println(start);
  _t11=2;
  start=[start,_t11](/);
  goto 3;
 Label 4:
}

void AlwaysDivide_doNothing_void(AlwaysDivide this){
  Bool _t7;
  String _t8;
 Label 1:
  _t7=true;
  If([_t7,false](==)) goto 2;
  _t8="qqq";
  println(_t8);
  goto 1;
 Label 2:
}

void AlwaysDivideRecursive_printDivideRecursive_Int(AlwaysDivideRecursive this,Int start){
  Int _t12;
  Bool _t13;
  Int _t14;
  Int _t15;
  _t12=this.min;
  _t13=[start,_t12](>=);
  If([_t13,false](==)) goto 5;
  println(start);
  _t14=2;
  _t15=[start,_t14](/);
  [AlwaysDivideRecursive_printDivideRecursive_Int(this,_t15)];
  goto 6;
 Label 5:
  Return;
 Label 6:
}

Int AlwaysDivideIterative_printDivideIterative_Int_Int(AlwaysDivideIterative this,Int start,Int end){
  Bool _t22;
  Bool _t23;
  Int _t24;
  Int _t25;
  Int _t26;
 Label 11:
  _t22=[start,end](>);
  If([_t22,false](==)) goto 12;
  _t23=[start,end](==);
  If([_t23,false](==)) goto 13;
  _t24=9000;
  Return _t24;
  goto 14;
 Label 13:
  _t25=2;
  start=[start,_t25](/);
 Label 14:
  goto 11;
 Label 12:
  _t26=42;
  Return _t26;
}

AlwaysDivideIterative AlwaysDivideIterative_get_void(AlwaysDivideIterative this){
  Bool _t16;
  AlwaysDivideIterative _t17;
  String _t18;
  Bool _t19;
  String _t20;
  String _t21;
  _t16=true;
  If([_t16,false](==)) goto 7;
  _t17=new AlwaysDivideIterative();
  Return _t17;
  goto 8;
 Label 7:
  _t18="aaa";
  println(_t18);
  _t19=false;
  If([_t19,false](==)) goto 9;
  _t20="falsefalse";
  println(_t20);
  Return null;
  goto 10;
 Label 9:
  _t21="falsetrue";
  println(_t21);
  Return null;
 Label 10:
 Label 8:
}

Int AlwaysDivideRecursiveFixed_printDivideRecursive_Int(AlwaysDivideRecursiveFixed this,Int start){
  Int _t27;
  Bool _t28;
  Int _t29;
  Int _t30;
  Int _t31;
  Int _t32;
  _t27=2;
  _t28=[start,_t27](>=);
  If([_t28,false](==)) goto 15;
  println(start);
  _t29=2;
  _t30=[start,_t29](/);
  _t31=[AlwaysDivideRecursiveFixed_printDivideRecursive_Int(this,_t30)];
  Return _t31;
  goto 16;
 Label 15:
  _t32=2;
  Return _t32;
 Label 16:
}

======= End of IR3 Program =======

