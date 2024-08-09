@Override
  public V putIfAbsent(final K key, final V value) {
    putIfAbsentObserver.begin();
    try {
      statusTransitioner.checkAvailable();
      checkNonNull(key, value);

      AtomicBoolean put = new AtomicBoolean(false);

      try {
        ValueHolder<V> inCache = doPutIfAbsent(key, value, b -> put.set(b));
        if (put.get()) {
          putIfAbsentObserver.end(PutIfAbsentOutcome.PUT);
          return null;
        } else if (inCache == null) {
          putIfAbsentObserver.end(PutIfAbsentOutcome.HIT);
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
    } catch(Exception e) {
      putIfAbsentObserver.end(PutIfAbsentOutcome.FAILURE);
      throw e;
    }
  }