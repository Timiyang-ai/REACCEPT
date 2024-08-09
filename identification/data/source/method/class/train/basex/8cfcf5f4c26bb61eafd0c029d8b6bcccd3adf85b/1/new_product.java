public static ANode stripNS(final ANode node, final byte[] ns, final Context ctx) {
    if(node.type != NodeType.ELM && node.type != NodeType.DOC) return node;

    final MemData data = new MemData(ctx.options);
    final DataBuilder db = new DataBuilder(data);
    db.build(node);

    // flag indicating if namespace should be completely removed
    boolean del = true;
    // loop through all nodes
    for(int pre = 0; pre < data.meta.size; pre++) {
      // only check elements and attributes
      final int kind = data.kind(pre);
      if(kind != Data.ELEM && kind != Data.ATTR) continue;
      // check if namespace is referenced
      final byte[] uri = data.nspaces.uri(data.uriId(pre, kind));
      if(uri == null || !eq(uri, ns)) continue;

      final byte[] nm = data.name(pre, kind);
      if(prefix(nm).length == 0) {
        // no prefix: remove namespace from element
        if(kind == Data.ELEM) {
          data.update(pre, Data.ELEM, nm, EMPTY);
          data.nsFlag(pre, false);
        }
      } else {
        // prefix: retain namespace
        del = false;
      }
    }
    if(del) data.nspaces.delete(ns);
    return new DBNode(data);
  }