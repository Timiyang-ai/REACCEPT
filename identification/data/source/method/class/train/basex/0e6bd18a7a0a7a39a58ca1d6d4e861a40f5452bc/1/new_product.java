private Object method(final Value[] ar, final QueryContext qc) throws Exception {
    // check if a field with the specified name exists
    try {
      final Field f = clazz.getField(method);
      final boolean st = Modifier.isStatic(f.getModifiers());
      if(ar.length == (st ? 0 : 1)) return f.get(st ? null : instObj(ar[0]));
    } catch(final NoSuchFieldException ex) { /* ignored */ }

    Method meth = null;
    Object inst = null;
    Object[] args = null;
    for(final Method m : clazz.getMethods()) {
      if(!m.getName().equals(method)) continue;
      final boolean st = Modifier.isStatic(m.getModifiers());
      final Class<?>[] pTypes = m.getParameterTypes();
      final Object[] jargs = javaArgs(pTypes, null, ar, st);
      if(jargs != null) {
        if(meth != null) throw JAVAAMBIG_X.get(info, Util.className(clazz) + '.' +
            method + '#' + pTypes.length);
        meth = m;
        args = jargs;

        if(!st) {
          inst = instObj(ar[0]);
          if(inst instanceof QueryModule) {
            final QueryModule mod = (QueryModule) inst;
            mod.staticContext = sc;
            mod.queryContext = qc;
          }
        }
      }
    }
    if(meth != null) return meth.invoke(inst, args);

    throw JAVAMETHOD_X_X.get(info, name(), foundArgs(ar));
  }