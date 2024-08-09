public void bind(final String n, final Object o, final String t)
      throws QueryException {

    Object obj = o;
    if(t != null && !t.isEmpty()) {
      if(t.equals(QueryText.JSONSTR)) {
        obj = JsonMapConverter.parse(token(o.toString()), null);
      } else {
        final QNm type = new QNm(token(t));
        if(type.ns()) type.uri(ns.uri(type.pref(), false, null));
        final Type typ = Types.find(type, true);
        if(typ != null) obj = typ.e(obj, null);
        else NOTYPE.thrw(null, type);
      }
    }
    bind(n, obj);
  }