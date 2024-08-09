public final int id(final Item key, final InputInfo ii) throws QueryException {
    final int h = key.hash(ii);
    final int p = h & bucket.length - 1;
    for(int id = bucket[p]; id != 0; id = next[id]) {
      if(keys[id].equiv(key, null, ii)) return -id;
    }
    return 0;
  }