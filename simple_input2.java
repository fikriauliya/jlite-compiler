class MainC {
   void main (){
      Test t;
      Int x;
      t = new Test();
      x = t.m1();
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

  Int m13() {
    Int i;
    i = 0;
    while (i < 10) {
      if (i == 9) {
        return 9;
      } 
      else {
        i = i + 1;
      }
    }
    return 11;
  }

  Int m14() {
    Int a;
    Int b;

    while (a < b) {
      a = 1;
      if (a > 0) {
        return a;
      } else {
        a = 2;
      }
    }
    return a;
  }

  Int m15() {
    // Int d;
    readln(d);
    return d;
  }

  void m16() {
    println (1+2);
    return;
  }

  void m17() {
    m16();
  }

  Test m18() {
    return m11().t;
  }
}