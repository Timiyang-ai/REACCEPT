private Object method(final Value[] ar, final QueryContext qc) throws Exception {
    // check if a field with the specified name exists
    try {
      final Field f = clazz.getField(method);
      final boolean st = Modifier.isStatic(f.getModifiers());
      if(ar.length == (st ? 0 : 1)) {
        return f.get(st ? null : instObj(ar[0]));
      }
    } catch(final NoSuchFieldException ex) { /* ignored */ }

    for(final Method meth : clazz.getMethods()) {
      if(!meth.getName().equals(method)) continue;
      final boolean st = Modifier.isStatic(meth.getModifiers());
      final Object[] arg = args(meth.getParameterTypes(), null, ar, st);
      if(arg != null) {
        Object inst = null;
        if(!st) {
          inst = instObj(ar[0]);
          if(inst instanceof QueryModule) {
            final QueryModule mod = (QueryModule) inst;
            mod.staticContext = sc;
            mod.queryContext = qc;
          }
        }
        return meth.invoke(inst, arg);
      }
    }
    throw JAVAMETHOD_X_X.get(info, name(), foundArgs(ar));
  }