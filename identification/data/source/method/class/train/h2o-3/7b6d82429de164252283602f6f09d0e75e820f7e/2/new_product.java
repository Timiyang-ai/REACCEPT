public Vec makeCopy(String[] domain) {
    Vec v = doCopy();
    v.setDomain(0,domain);
    DKV.put(v);
    return v;
  }