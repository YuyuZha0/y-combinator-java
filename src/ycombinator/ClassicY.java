package ycombinator;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public final class ClassicY<T, R>
    implements Function<UnaryOperator<Function<T, R>>, Function<T, R>> {

  @Override
  public Function<T, R> apply(UnaryOperator<Function<T, R>> f) {
    MetaFunc<T, Function<T, R>> F =
        new MetaFunc<T, Function<T, R>>() {
          @Override
          public Function<T, R> apply(MetaFunc<T, Function<T, R>> x) {
            return f.apply(
                new Function<T, R>() {
                  @Override
                  public R apply(T y) {
                    return x.apply(x).apply(y);
                  }
                });
          }
        };
    return F.apply(F);
  }
}
