public Vec makeCopy(){
    final Vec v = new Vec(group().addVec(),_espc.clone());
    new MRTask(){
      @Override public void map(Chunk c){
        Chunk c2 = (Chunk)c.clone();
        c2._mem = c2._mem.clone();
        DKV.put(v.chunkKey(c.cidx()), c2, _fs);
      }
    }.doAll(this);
    DKV.put(v._key,v);
    return v;
  }