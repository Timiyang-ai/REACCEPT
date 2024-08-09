void add(final QueryContext ctx) throws QueryException  {
    final int gl = gv.length;
    final Value[] vals = new Value[gl];
    for(int i = 0; i < gl; i++) {
      final Value val = ctx.value(gv[i]);
      if(val.size() > 1) XGRP.thrw(input);
      vals[i] = val;
    }

    final GroupNode gn = new GroupNode(input, vals);
    final int h = gn.hash();
    final IntList ps = hashes.get(h);
    int p = -1;
    if(ps != null) {
      for(int i = 0; i < ps.size(); ++i) {
        final int pp = ps.get(i);
        if(gn.eq(part.get(pp))) {
          p = pp;
          break;
        }
      }
    }
    if(p < 0) {
      p = part.size();
      part.add(gn);

      IntList pos = hashes.get(h);
      if(pos == null) {
        pos = new IntList(1);
        hashes.add(h, pos);
      }
      pos.add(p);
    }

    final int ngl = ngv[0].length;

    // no non-grouping variables exist
    if(ngl == 0) return;

    // adds the current non-grouping variable bindings to the p-th partition.
    if(p == items.size()) items.add(new ValueBuilder[ngl]);
    final ValueBuilder[] sq = items.get(p);

    for(int i = 0; i < ngl; ++i) {
      ValueBuilder vb = sq[i];
      final Value result = ctx.value(ctx.vars.get(ngv[0][i]));
      if(vb == null) {
        vb = new ValueBuilder();
        sq[i] = vb;
      }
      vb.add(result);
    }
  }