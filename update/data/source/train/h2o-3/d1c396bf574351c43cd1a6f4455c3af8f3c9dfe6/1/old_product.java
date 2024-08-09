public CategoricalWrappedVec adaptTo( String[] domain ) {
    return new CategoricalWrappedVec(group().addVec(),_rowLayout,domain,this._key);
  }