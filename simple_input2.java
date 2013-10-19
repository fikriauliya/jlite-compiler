class MainC {
   void main (){
      return ;
   }
}

class Test {
  Int d;

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

  Test m8() {
    return this;
  }
}