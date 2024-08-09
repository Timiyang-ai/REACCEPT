public TransfVec toEnum() {
    if( isEnum() ) return adaptTo(domain()); // Use existing domain directly
    if( !isInt() ) throw new IllegalArgumentException("Enum conversion only works on integer columns");
    // Right now, limited to small dense integers.
    if( min() < 0 || max() > 1000000 ) 
      throw new IllegalArgumentException("Enum conversion only works on small integers, but min="+min()+" and max = "+max());
    long[] domain= new CollectDomain().doAll(this).domain();
    if( domain.length > Enum.MAX_ENUM_SIZE )
      throw new IllegalArgumentException("Column domain is too large to be represented as an enum: " + domain.length + " > " + Enum.MAX_ENUM_SIZE);
    return adaptTo(ArrayUtils.toString(domain));
  }