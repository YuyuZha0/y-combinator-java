package ycombinator;

import java.util.function.Function;

interface MetaFunc<T, R> extends Function<MetaFunc<T, R>, R> {}
