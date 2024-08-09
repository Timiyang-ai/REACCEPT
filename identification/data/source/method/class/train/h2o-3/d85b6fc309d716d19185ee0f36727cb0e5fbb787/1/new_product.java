public final float set(int idx, float f) {
    setWrite();
    if( _chk2.set_impl(idx,f) ) return f;
    (_chk2 = inflate()).set_impl(idx,f);
    return f;
  }