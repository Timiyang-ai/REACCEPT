private Iter eval(final QueryContext ctx) throws QueryException {
    // creates an initial item cache
    Iter res = ctx.value.iter();
    // loop through all expressions
    final int el = step.length;
    for(int ex = 0; ex < el; ex++) {
      final Expr e = step[ex];
      final boolean last = ex + 1 == el;
      final ItemCache ii = new ItemCache();
      ctx.size = res.size();
      ctx.pos = 1;
      // this flag indicates if the resulting items contain nodes
      boolean nodes = false;
      // loop through all input items
      for(Item it; (it = res.next()) != null;) {
        if(!it.node()) NODESPATH.thrw(input, this, it.type);
        ctx.value = it;
        // loop through all resulting items
        final Iter ir = ctx.iter(e);
        for(Item i; (i = ir.next()) != null;) {
          // set node flag
          if(ii.size() == 0) nodes = i.node();
          // check if both nodes and atomic values occur in last result
          else if(last && nodes != i.node()) EVALNODESVALS.thrw(input);
          ii.add(i);
        }
        ctx.pos++;
      }
      if(nodes) {
        // remove potential duplicates from node sets
        final NodeCache nc = new NodeCache().random();
        for(Item it; (it = ii.next()) != null;) nc.add((ANode) it);
        res = nc.finish().cache();
      } else {
        res = ii;
      }
    }
    return res;
  }