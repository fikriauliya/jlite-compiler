Parsing...
ex1.txt

======= JLite Program =======

class Main{
  void main(Int i,Int a,Int b,Int d){
    Int t1;
    Int t2;
    Compute help;
    help=new Compute();
    t1=[[help.addSquares(a,b)],[help.square(i)]](+);
    t2=[help.square(d)];
    If([t2,t1](>))
    {
      println("Square of d larger than sum of squares");
    }
    else
    {
      println("Square of d larger than sum of squares");
    }
  }

} 

class Compute{
  Int chachedValue;
  Bool computedSquares;

  Int square(Int a){
    Return [a,a](*);
  }


  Int add(Int a,Int b){
    Return [a,b](+);
  }


  Int addSquares(Int a,Int b){
    If(computedSquares)
    {
      Return chachedValue;
    }
    else
    {
      computedSquares=true;
      Return [add([square(a)],[square(b)])];
    }
  }

}

======= End of JLite Program =======


======= JLite Program =======

class Main{
  void main(Int i,Int a,Int b,Int d){
    Int t1;
    Int t2;
    Compute help;
    help=new Compute():Compute;
    t1=[[help:Compute.Compute_2(a:Int,b:Int)]:Int,[help:Compute.Compute_0(i:Int)]:Int](+):Int;
    t2=[help:Compute.Compute_0(d:Int)]:Int;
    If([t2:Int,t1:Int](>):Bool)
    {
      println("Square of d larger than sum of squares");
    }
    else
    {
      println("Square of d larger than sum of squares");
    }
  }

} 

class Compute{
  Int chachedValue;
  Bool computedSquares;

  Int square(Int a){
    Return [a:Int,a:Int](*):Int;
  }


  Int add(Int a,Int b){
    Return [a:Int,b:Int](+):Int;
  }


  Int addSquares(Int a,Int b){
    If(computedSquares:Bool)
    {
      Return chachedValue:Int;
    }
    else
    {
      computedSquares=true;
      Return [Compute_1([Compute_0(a:Int)]:Int,[Compute_0(b:Int)]:Int)]:Int;
    }
  }

}

======= End of JLite Program =======

======= IR3 Program =======

======= CData3 ======= 

class Main{
}

class Compute{
  Int chachedValue;
  Bool computedSquares;
}

=======  CMtd3 ======= 

void main(Main this,Int i,Int a,Int b,Int d){
  Int t1;
  Int t2;
  Compute help;
  Int _t1;
  Int _t2;
  Bool _t3;
  help=new Compute();
  _t1=[Compute_2(help,a,b)];
  _t2=[Compute_0(help,i)];
  t1=[_t1,_t2](+);
  t2=[Compute_0(help,d)];
  _t3=[t2,t1](>);
  If([_t3,false](==)) goto 1;
  println("Square of d larger than sum of squares");
  goto 2;
 Label 1:
  println("Square of d larger than sum of squares");
 Label 2:
}

Int Compute_0(Compute this,Int a){
  Int _t10;
  _t10=[a,a](*);
  Return _t10;
}

Int Compute_1(Compute this,Int a,Int b){
  Int _t9;
  _t9=[a,b](+);
  Return _t9;
}

Int Compute_2(Compute this,Int a,Int b){
  Bool _t4;
  Int _t5;
  Int _t6;
  Int _t7;
  Int _t8;
  _t4=this.computedSquares;
  If([_t4,false](==)) goto 3;
  _t5=this.chachedValue;
  Return _t5;
  goto 4;
 Label 3:
  this.computedSquares=true;
  _t6=[Compute_0(this,a)];
  _t7=[Compute_0(this,b)];
  _t8=[Compute_1(this,_t6,_t7)];
  Return _t8;
 Label 4:
}

======= End of IR3 Program =======

