class MainC {

   void main (){
      Functional fo ;

      return ;
   }
}

class Functional {
Int a;
Hello b;
Int c;

Int f (Int b){
        readln(b) ;
        println(b) ; 
	return 3;
}

Int g (Int x){ 
        return x;
}
Int g (Int x, Int y){ 
        return x;
}
}

class Hello {
  Int c;
  Hello d;
  Hello e;
  Int h (Int c) {
    //return 5;
    return d.e.d.d.e.c;
  }

  Hello s() {
    return (new Hello()).d;
  }

  Hello s2() {
    return s().d;
  }

  Hello s3() {
    return this.d.e.d;
  }

  Hello s4() {
   return this.d.e.d.s3(); 
  }

  Hello s5() {
   return (new Hello()).s3();
  }

  Hello s6() {
   return s5().s4();
  }

  Hello s7() {
   return this.s4();
  }
}

class Hello1 {
  Int c;
  Hello d;

  Int h (Int c) {
    Hello d;
    Hello s;
    Int f;
    
    readln(f);
    println(f + 1 + 3);
    println(-2);
    // return w(); => error
    return w(4);
  }

  Hello w() {
    Hello h;
    return h;
  }
  Int w(Int a) {
    return 0;
  }
  // Int h (Int c, String s) {
  //   return 5;
  // }
  // Int h (Int c, Int s) {
  //   return 5;
  // }
  // Int h (Int c, Hello s) {
  //   return 5;
  // }
  // Hello1 h (Int c, Hello1 s) {
  //   return s;
  // }
}

class Hello2 {
  Int m2() {
    Int ret;
    String ret2;
    String ret3;

    ret = 2;    
    ret2 = "Hello";
    ret3 = ret2;
    return ret;
  }

  void m3() {
    m2();
    return;
  }

  void m4() {
    Hello w;
    w.c = 2;
    return;
  }

  Int m5() {
    Int a;
    Int b;

    a = 2;
    b = 3;

    if (a == b) {
      return 3;
    }
    else {
      return 4;
    }
  }
}