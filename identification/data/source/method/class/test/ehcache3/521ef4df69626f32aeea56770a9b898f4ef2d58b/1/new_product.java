@Override
  public V putIfAbsent(final K key, final V value) {
    putIfAbsentObserver.begin();
    try {
      statusTransitioner.checkAvailable();
      checkNonNull(key, value);

      boolean[] put = { false };

      try {
        ValueHolder<V> inCache = doPutIfAbsent(key, value, b -> put[0] = b);
        if (put[0]) {
          putIfAbsentObserver.end(PutIfAbsentOutcome.PUT);
          return null;
        } else {
          putIfAbsentObserver.end(PutIfAbsentOutcome.HIT);
          return inCache.get();
        }
      } catch (StoreAccessException e) {
        V newValue = resilienceStrategy.putIfAbsentFailure(key, value, e);
        putIfAbsentObserver.end(PutIfAbsentOutcome.FAILURE);
        return newValue;
      }
    } catch (Throwable e) {
      putIfAbsentObserver.end(PutIfAbsentOutcome.FAILURE);
      throw e;
    }
  }