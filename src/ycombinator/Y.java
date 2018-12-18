package ycombinator;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public final class Y<T, R> implements Function<UnaryOperator<Function<T, R>>, Function<T, R>> {
  @Override
  public Function<T, R> apply(UnaryOperator<Function<T, R>> recursiveFunction) {
    MetaFunc<T, Function<T, R>> F =
        (MetaFunc<T, Function<T, R>> x) -> recursiveFunction.apply((T y) -> x.apply(x).apply(y));

    return F.apply(F);
  }
}
