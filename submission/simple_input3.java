class Main {
  void main() {
    return;
  }
}
class Test {
  Int d;
  Test t;
  
  Int m1 () {
    return 2;
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
    Int d;
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

  Bool m19() {
    Bool res;
    Int a;
    Int b;
    Int c;
    Int d;

    Bool a1;
    Bool a2;
    Bool a3;
    // res = 1 > 2;
    res = (a>b) && (b>c) && (c>d);
    println(res);
    res = a1 || a2 && a3;
    return res;
  }

  Int m20() {
    return (new Test()).m1();
  }
}