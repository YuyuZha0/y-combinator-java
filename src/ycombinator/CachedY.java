package ycombinator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public final class CachedY<T, R>
    implements Function<UnaryOperator<Function<T, R>>, Function<T, R>> {

  private final Map<? super T, R> cache;

  public CachedY() {
    this.cache = new HashMap<>();
  }

  public CachedY(int capacity) {
    this.cache = new HashMap<>(capacity);
  }

  public CachedY(Map<? super T, R> cache) {
    Objects.requireNonNull(cache);
    this.cache = cache;
  }

  @Override
  public Function<T, R> apply(UnaryOperator<Function<T, R>> recursiveFunction) {
    MetaFunc<T, Function<T, R>> F =
        (MetaFunc<T, Function<T, R>> x) ->
            recursiveFunction.apply(
                (T y) -> {
                  R r = cache.get(y);
                  if (r != null) return r;
                  r = x.apply(x).apply(y);
                  cache.put(y, r);
                  return r;
                });
    return F.apply(F);
  }
}
