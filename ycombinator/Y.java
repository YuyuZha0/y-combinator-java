package ycombinator;

import java.util.function.Function;

public final class Y<T, R>
    implements Function<Function<Function<T, R>, Function<T, R>>, Function<T, R>> {
  @Override
  public Function<T, R> apply(Function<Function<T, R>, Function<T, R>> recursiveFunction) {
    MetaFunc<T, Function<T, R>> F =
        (MetaFunc<T, Function<T, R>> x) -> recursiveFunction.apply((T y) -> x.apply(x).apply(y));

    return F.apply(F);
  }
}
