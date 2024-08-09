public Builder<T> add(Iterable<? extends T> iterable) {
      CollectionUtils.checkImmutable(iterable);
      // Avoid unnecessarily expanding a NestedSet.
      boolean isEmpty =
          iterable instanceof NestedSet
              ? ((NestedSet<?>) iterable).isEmpty()
              : Iterables.isEmpty(iterable);
      if (!isEmpty) {
        iterables.add(iterable);
      }
      return this;
    }