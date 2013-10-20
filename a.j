class MainC {

   void main (Int c, Int d){
      Functional fo;
      Functional2 foo;
      
      Int a;
      
      println("Hello world");
      fo = NULL;
      fo = new Functional();
      fo.a = 5;
      fo.g(c);
      
      foo.ff = fo;
      a = foo.ff.f(5);

      fo = NULL;

      (new Functional()).a = 10;
      (new Functional()).f(5);
      
      return ;
   }
}

class Functional {
    Int a;

    Int f (Int b){
        a = 4;
        readln(a) ;
        g(b) ; 
        this.h();
        return 3;
    }

    Int g (Int x){
        if (1 == 2) {
            println("hello");
        }
        else {
            println("world");
        }
        return 300;
    }
    
    Functional hee(Int f) {
    return NULL;
    }
    
    Int h () {
        while (1 == 2) {
            println("QQQ");
            return 333;
        }
        println ("RRR");
        this.a = ---2;
        return this.a;
    }
}

class Functional2 {
    Functional ff; 
    Functional2 f2;
    Int d;
    Int hahaha() {
        readln(d);
        return d;
    }
    
    void test() {
//        f2 = new Functional2();
        
  //      f2.f2 = new Functional2();
        f2.f2.f2.f2 = NULL;
    }
}
