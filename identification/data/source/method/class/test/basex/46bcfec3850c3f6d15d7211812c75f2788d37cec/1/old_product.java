public final void delete(final int pre) {
    meta.update(true);

    // size of the subtree to delete
    int k = kind(pre);
    int s = size(pre, k);
    ns.delete(pre, s);

    // reduce size of ancestors
    int par = pre;
    // check if we are an attribute (different size counters)
    if(k == ATTR) {
      par = parent(par, ATTR);
      attSize(par, ELEM, attSize(par, ELEM) - 1);
      size(par, ELEM, size(par, ELEM) - 1);
      k = kind(par);
    }

    // reduce size of ancestors
    while(par > 0 && k != DOC) {
      par = parent(par, k);
      k = kind(par);
      size(par, k, size(par, k) - s);
    }

    // preserve empty root node
    int p = pre;
    final boolean empty = p == 0 && s == meta.size;
    if(empty) {
      ++p;
      --s;
    } else {
      if(kind(p) == DOC) --meta.ndocs;
    }

    // delete node from table structure and reduce document size
    table.delete(pre, s);
    updateDist(p, -s);

    // NSNodes have to be checked for pre value shifts after delete
    ns.update(pre, s, false, null);

    // restore empty document node
    if(empty) {
      doc(0, 1, EMPTY);
      table.set(0, buffer());
    }
  }