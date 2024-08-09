private NodeIter index(final byte[] term) {
    final Data data = ictx.data;
    final boolean mem = data instanceof MemData;
    final boolean text = ind == IndexType.TEXT;
    final byte kind = text ? Data.TEXT : Data.ATTR;

    return term.length <= MAXLEN ? new NodeIter() {
      final IndexIterator ii = data.ids(new ValuesToken(ind, term));

      @Override
      public Nod next() {
        while(ii.more()) {
          final int p = ii.next();
          // main memory instance: check if text is no comment, etc.
          if(!mem || data.kind(p) == kind) return new DBNode(data, p, kind);
        }
        return null;
      }
    } : new NodeIter() {
      // fallback solution: parse complete data if string is too long
      int pre = -1;

      @Override
      public Nod next() {
        while(++pre != data.meta.size) {
          if(data.kind(pre) == kind && eq(data.text(pre, text), term))
            return new DBNode(data, pre, kind);
        }
        return null;
      }
    };
  }