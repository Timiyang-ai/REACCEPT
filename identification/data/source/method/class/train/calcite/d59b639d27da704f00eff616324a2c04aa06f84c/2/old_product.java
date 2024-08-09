public static <TSource, TKey, TElement, TResult> Enumerable<TResult> groupBy(
      Enumerable<TSource> enumerable, Function1<TSource, TKey> keySelector,
      Function1<TSource, TElement> elementSelector,
      final Function2<TKey, Enumerable<TElement>, TResult> resultSelector) {
    return enumerable.toLookup(keySelector, elementSelector)
        .select(new Function1<Grouping<TKey, TElement>, TResult>() {
          public TResult apply(Grouping<TKey, TElement> group) {
            return resultSelector.apply(group.getKey(), group);
          }
        });
  }