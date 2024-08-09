public static ANode stripNS(final ANode node, final byte[] ns, final Context ctx) {
    if(node.type != NodeType.ELM) return node;

    final MemData md = new MemData(ctx.prop);
    final DataBuilder db = new DataBuilder(md);
    db.build(node);

    // flag indicating if namespace should be completely removed
    boolean del = true;
    // loop through all nodes
    for(int pre = 0; pre < md.meta.size; pre++) {
      // only check elements and attributes
      final int kind = md.kind(pre);
      if(kind != Data.ELEM && kind != Data.ATTR) continue;
      // check if namespace is referenced
      final byte[] uri = md.nspaces.uri(md.uri(pre, kind));
      if(uri == null || !eq(uri, ns)) continue;

      final byte[] name = md.name(pre, kind);
      if(prefix(name).length == 0) {
        // no prefix: remove namespace from element
        if(kind == Data.ELEM) {
          md.update(pre, kind, name, EMPTY);
          md.nsFlag(pre, false);
        }
      } else {
        // prefix: retain namespace
        del = false;
      }
    }
    if(del) md.nspaces.delete(ns);
    return new DBNode(md);
  }