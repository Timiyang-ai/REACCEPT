public final void replace(final int rpre, final Data data) {
    meta.update();
    meta.docindex = false;

    // check if attribute size of parent must be updated
    final int dsize = data.meta.size;
    buffer(dsize);

    final int rkind = kind(rpre);
    final int rsize = size(rpre, rkind);
    final int rpar = parent(rpre, rkind);
    for(int dpre = 0; dpre < dsize; dpre++) {
      final int dkind = data.kind(dpre);
      final int dpar = data.parent(dpre, dkind);
      final int pre = rpre + dpre;
      final int dis = dpar >= 0 ? dpre - dpar : pre - parent(rpre, rkind);

      switch(dkind) {
        case DOC:
          // add document
          doc(pre, data.size(dpre, dkind), data.text(dpre, true));
          meta.ndocs++;
          break;
        case ELEM:
          // add element
          byte[] nm = data.name(dpre, dkind);
          elem(dis, tagindex.index(nm, null, false), data.attSize(dpre, dkind),
              data.size(dpre, dkind), ns.uri(nm, true), false);
          break;
        case TEXT:
        case COMM:
        case PI:
          // add text
          text(pre, dis, data.text(dpre, true), dkind);
          break;
        case ATTR:
          // add attribute
          nm = data.name(dpre, dkind);
          attr(pre, dis, atnindex.index(nm, null, false),
              data.text(dpre, false), ns.uri(nm, false), false);
          break;
      }
    }
    table.replace(rpre, buffer(), rsize);
    buffer(1);

    // no distance/size update if the two subtrees are of equal size
    final int diff = dsize - rsize;
    if(diff == 0) return;

    // increase/decrease size of ancestors, adjust distances of siblings
    int p = rpar;
    while(p >= 0) {
      final int k = kind(p);
      size(p, k, size(p, k) + diff);
      p = parent(p, k);
    }
    updateDist(rpre + dsize, diff);

    // adjust attribute size of parent if attributes inserted. attribute size
    // of parent cannot be reduced via a replace expression.
    if(data.kind(0) == ATTR) {
      int d = 0, i = 0;
      while(i < dsize && data.kind(i++) == ATTR) d++;
      if(d > 1) attSize(rpar, kind(rpar), d + 1);
    }
  }