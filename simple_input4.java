class Main {
  void main() {
    return;
  }
}

class Test {
  Int a;
  Int b;
  Int c;

  Int if_test() {
    Int a;
    Int b;
    if (a > b) {
      return a;
    } else {
      a = b;
    }
    return a;
  }

  Int while_test() {
    Int a;
    Int b;
    while (a > b) {
      if (a == 100) {
        return a;
      } else {
        a = a + 2;
      }
    }
    return a;
  }

  Int method_naming_test(Int a, String b, Bool c) {
    return 0;
  }

  Bool short_circuit_test() {
    return ((a > b) && (b > c));
  }

  Bool short_circuit_test2() {
    return ((a > b) || (b > c));
  }
}