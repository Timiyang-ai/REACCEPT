private boolean instanceOf(final FuncType ft, final boolean coerce) {
    if(ft instanceof MapType) return false;
    if(type.instanceOf(ft)) return true;

    final SeqType[] at = ft.argTypes;
    if(at != null && (at.length != 1 || !at[0].instanceOf(SeqType.ITR))) return false;

    final SeqType ret = ft.declType;
    if(ft instanceof ArrayType) {
      // no argument and return type: no check required
      if(ret.eq(SeqType.ITEM_ZM)) return true;
      // check types of members
      for(final Value val : members()) {
        if(!ret.instance(val)) return false;
      }
      return true;
    }
    // allow coercion
    return coerce || ret.eq(SeqType.ITEM_ZM);
  }