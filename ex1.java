class Main {

void main(Int i,Int a, Int b,Int d){
 Int t1;
 Int t2;
 Compute help;
 help = new Compute();
 t1 = help.addSquares(a,b) + help.square(i);
 t2 = help.square(d);
 if(t2>t1){
    println("Square of d larger than sum of squares");
 }
 else{
      println("Square of d larger than sum of squares");
 }
}

}

class Compute {

   Bool computedSquares;
   Int chachedValue;

   Int square(Int a){
     return a*a;
   }
   
   Int add(Int a, Int b){
    return a+b;
   }
   
   Int addSquares(Int a, Int b){
    if(computedSquares){
      return chachedValue;
    }
    else{
      computedSquares = true;
      return add(square(a),square(b));
    }
   }
}