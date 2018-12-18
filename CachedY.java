package ycombinator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class CachedY<T, R>
  implements Function<Function<Function<T, R>, Function<T, R>>, Function<T, R>> {

private final Map<T, R> cache = new HashMap<>();

@Override
public Function<T, R> apply(Function<Function<T, R>, Function<T, R>> recursiveFunction) {
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
