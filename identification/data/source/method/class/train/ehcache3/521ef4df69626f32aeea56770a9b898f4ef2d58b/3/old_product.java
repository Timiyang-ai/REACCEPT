@Override
  public V putIfAbsent(final K key, final V value) {
    putIfAbsentObserver.begin();
    statusTransitioner.checkAvailable();
    checkNonNull(key, value);

    AtomicBoolean put = new AtomicBoolean(false);

    try {
      ValueHolder<V> inCache = doPutIfAbsent(key, value, b -> put.set(b));
      if(put.get()) {
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
      try {
        return resilienceStrategy.putIfAbsentFailure(key, value, e); // FIXME: We can't know if it's absent or not
      } finally {
        putIfAbsentObserver.end(PutIfAbsentOutcome.FAILURE);
      }
    }
  }