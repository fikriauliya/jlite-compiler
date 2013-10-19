class MainC {
   void main (){
      return ;
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
    return NULL;
  }
}