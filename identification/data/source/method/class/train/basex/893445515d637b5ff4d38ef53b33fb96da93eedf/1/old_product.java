public Map put(final Item key, final Value value, final InputInfo ii) throws QueryException {
    final TrieNode ins = root.put(key.hash(ii), key, value, 0, ii);
    // update date counter
    int t = dt;
    if(key instanceof ADate) {
      final boolean tz = ((ADate) key).zon() != Short.MAX_VALUE;
      if(tz ? t < 0 : t > 0) throw MAPTZ.get(ii);
      t += tz ? 1 : -1;
    }
    return ins == root ? this : new Map(ins, t);
  }