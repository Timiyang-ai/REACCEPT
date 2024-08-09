public Builder<T> add(Iterable<? extends T> iterable) {
      CollectionUtils.checkImmutable(iterable);
      // Avoid unnecessarily expanding a NestedSet.
      boolean isEmpty = CollectionUtils.isEmpty(iterable);
      if (!isEmpty) {
        iterables.add(iterable);
      }
      return this;
    }