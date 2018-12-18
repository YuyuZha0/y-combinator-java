package ycombinator;

import java.util.function.Function;

public final class ClassicY<T, R>
    implements Function<Function<Function<T, R>, Function<T, R>>, Function<T, R>> {

  @Override
  public Function<T, R> apply(Function<Function<T, R>, Function<T, R>> f) {
    return new MetaFunc<T, Function<T, R>>() {
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
    }.apply(
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
        });
  }
}
