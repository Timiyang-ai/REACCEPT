public void fill(byte[] v, int byteoff, int nbits, int bitoff) {
    if( nbits   < 0 ) throw new NegativeArraySizeException("nbits < 0: " + nbits  );
    if( byteoff < 0 ) throw new IndexOutOfBoundsException("byteoff < 0: "+ byteoff);
    if( bitoff  < 0 ) throw new IndexOutOfBoundsException("bitoff < 0: " + bitoff );
    assert(v.length >= bytes(nbits));
    assert v==null || byteoff+bytes(nbits) <= v.length;
    _val = v;
    _nbits = nbits;
    _bitoff = bitoff;
    _byteoff = byteoff;
  }