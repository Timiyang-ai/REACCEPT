public void bind(final String name, final Object val, final String type)
      throws QueryException {

    Object obj = val;
    if(type != null && !type.isEmpty()) {
      if(type.equals(QueryText.JSONSTR)) {
        obj = JsonMapConverter.parse(token(val.toString()), null);
      } else {
        final QNm nm = new QNm(token(type));
        if(nm.ns()) nm.uri(ns.uri(nm.pref(), false, null));
        final Type typ = Types.find(nm, true);
        if(typ != null) obj = typ.e(obj, null);
        else NOTYPE.thrw(null, nm);
      }
    }
    bind(name, obj);
  }