void add(final QueryContext ctx) throws QueryException  {
    final Item[] its = new Item[gv.length];
    for(int i = 0; i < gv.length; i++) {
      its[i] = ctx.vars.get(gv[i]).item;
    }
    
    boolean found = false;
    int p = 0;
    final GroupNode cand = new GroupNode(its, gv.length);
    final Integer chash = cand.hashCode();
    
    if(hashes.containsKey(chash)) {
      final IntList ps = hashes.get(cand.hash);
      for(final int pp : ps.toArray()) {
        if(cand.eq(partitions.get(pp))) {
          found = true;
          p = pp;
          break;
        }
      }    
    }
     if(!found) {
      p = partitions.size();
      partitions.add(cand);
      final IntList pos = hashes.get(chash) != null ?
          hashes.get(chash)
          : new IntList(2);
      pos.add(p);
      hashes.put(chash, pos);
    }
    addNonGrpIts(ctx, p);
  }