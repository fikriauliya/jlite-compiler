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

}