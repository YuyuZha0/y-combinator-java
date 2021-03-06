package ycombinator;

import java.math.BigInteger;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class FibY {

  public static void main(String[] args) {
    UnaryOperator<Function<Integer, BigInteger>> fib =
        (Function<Integer, BigInteger> f) ->
            (Integer n) -> {
              if (n == 0) return BigInteger.ZERO;
              if (n == 1 || n == 2) return BigInteger.ONE;

              Integer m = n >>> 1;
              if ((n & 1) == 0) {
                BigInteger temp = f.apply(m + 1).add(f.apply(m - 1));
                return f.apply(m).multiply(temp);
              } else {
                BigInteger fm = f.apply(m);
                BigInteger fm1 = f.apply(m + 1);
                return fm.multiply(fm).add(fm1.multiply(fm1));
              }
            };

    int n = 1000000;
    long t1 = System.nanoTime();
    BigInteger n1 = new Y<Integer, BigInteger>().apply(fib).apply(n);
    long t2 = System.nanoTime();
    BigInteger n2 = new CachedY<Integer, BigInteger>(1000).apply(fib).apply(n);
    long t3 = System.nanoTime();
    System.out.printf("Y,len:%d,time:%dms%n", n1.toString().length(), (t2 - t1) / 1000_000);
    System.out.printf("CachedY,len:%d,time:%dms%n", n2.toString().length(), (t3 - t2) / 1000_000);
  }
}
