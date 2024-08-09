public final int id(final Item key, final InputInfo ii) throws QueryException {
    final int h = key.hash(ii);
    final int p = h & buckets.length - 1;
    for(int id = buckets[p]; id != 0; id = next[id]) {
      if(keys[id].equiv(key, null, ii)) return -id;
    }
    return 0;
  }