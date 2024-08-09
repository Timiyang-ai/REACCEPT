public Vec makeCopy(String[] domain){
    Vec v = doCopy();
    v._domain = domain;
    v._type = _type;
    DKV.put(v._key, v);
    return v;
  }