private boolean add(final BasicUpdate u, final boolean merged) {
    if(u == null) return false;

    if(!merged) {
      if(recent instanceof StructuralUpdate)
        struct.add((StructuralUpdate) recent);
      else val.add(recent);
    }
    recent = u;
    return true;
  }