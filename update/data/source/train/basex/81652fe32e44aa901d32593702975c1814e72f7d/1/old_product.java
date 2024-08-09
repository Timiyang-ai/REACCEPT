public Map put(final Item key, final Value value, final InputInfo ii) throws QueryException {
    final TrieNode ins = root.put(key.hash(ii), key, value, 0, ii);
    return ins == root ? this :
      new Map(ins, dt + (key instanceof ADate ? ((ADate) key).tzDefined() ? 1 : -1 : 0));
  }