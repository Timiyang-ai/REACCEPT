private NodeIter index(final byte[] term) {
    final Data data = ictx.data;
    return term.length <= MAXLEN &&
      (ind == IndexType.TEXT ? data.meta.textindex : data.meta.attrindex) ?
      index(data, term) : scan(data, term);
  }