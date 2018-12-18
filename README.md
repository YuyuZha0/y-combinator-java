# y-combinator-java
##### java version of y-combinator

##### In functional-programing, the Y-Combinartor is defined as :

![Definition](https://github.com/YuyuZha0/y-combinator-java/blob/master/definition.png?raw=true)

##### It is also called [Fixed-point combinator](https://en.wikipedia.org/wiki/Fixed-point_combinator):

![Explain](https://github.com/YuyuZha0/y-combinator-java/blob/master/explain.png?raw=true)

##### This is a java version of Y-Combinator.

#### Usage:
    public static void main(String[] args) {
    Function<Function<Integer, BigInteger>, Function<Integer, BigInteger>> fib =
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
    BigInteger n2 = new CachedY<Integer, BigInteger>().apply(fib).apply(n);
    long t3 = System.nanoTime();
    System.out.printf("Y,len:%d,time:%dms%n", n1.toString().length(), (t2 - t1) / 1000_000);
    System.out.printf("CachedY,len:%d,time:%dms%n", n2.toString().length(), (t3 - t2) / 1000_000);
    }
    //Y,len:208988,time:3772ms
    //CachedY,len:208988,time:28ms




