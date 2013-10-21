class MainC {
   void main (){
      Test t;
      Int x;
      t = new Test();
      x = t.m1();
      return ;
   }
}


class Compute {
  
   Int square(Int a){
     return a*a;
   }
   
   Int add(Int a, Int b){
    return a+b;
   }
   
   Int addSquares(Int a, Int b){
    return add(square(a),square(b));
   }
}

class Test {
  Int d;
  Test t;

  Int m1 () {
    return 2;
  }

  Int m2 () {
    Int c;
    return c;
  }

  Int m3 () {
    return d;
  }

  Int m4 () {
    Int a;
    Int b;
    Int c;

    a = 2;
    b = 3;
    c = a + b;

    return c; 
  }

  String m5() {
    return "Hello";
  }

  Bool m6() {
    Bool ret;
    ret = false;
    return ret;
  }

  Bool m7() {
    Bool ret;
    ret = !false;
    return ret;
  }

  Test m8(Int a) {
    return this;
  }

  Int m9(Int a, Int b, Int c) {
    this.d = 2;
    return this.d + 2;
  }

  Int m10() {
    return m8(2).t.d + m9(4+2,2,5);
  }

  Test m11() {
    Test ret;
    ret = new Test();
    ret = NULL;
    ret.t = NULL;
    ret.t.t = NULL;
    ret.t.d = 2;
    ret.t.t.t.t.t.t.d = 10;
    m11().m11();
    return ret;
  }

  Int m12() {
    Int a ;

    // a = 2;
    if (1 > 3) {
      a = 3;
    } else {
      a = 4;
    }
    return 4;
  }

}