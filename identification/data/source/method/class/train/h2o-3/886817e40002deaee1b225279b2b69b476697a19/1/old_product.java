public TransfVec toEnum() {
    if( isEnum() ) return adaptTo(domain()); // Use existing domain directly
    if( !isInt() ) throw new IllegalArgumentException("Enum conversion only works on integer columns");
    long[] domain= new CollectDomain().doAll(this).domain();
    if( domain.length > Enum.MAX_ENUM_SIZE )
      throw new IllegalArgumentException("Column domain is too large to be represented as an enum: " + domain.length + " > " + Enum.MAX_ENUM_SIZE);
    return adaptTo(ArrayUtils.toString(domain));
  }