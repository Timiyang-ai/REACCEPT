public Builder<T> add(Iterable<? extends T> iterable) {
      CollectionUtils.checkImmutable(iterable);
      if (!Iterables.isEmpty(iterable)) {
        iterables.add(iterable);
      }
      return this;
    }