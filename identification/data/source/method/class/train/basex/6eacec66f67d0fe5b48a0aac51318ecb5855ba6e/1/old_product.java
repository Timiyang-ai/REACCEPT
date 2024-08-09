private boolean eval(final Item it1, final Item it2, final Collation cl) throws QueryException {
    if(it1 instanceof Array || it2 instanceof Array) {
      for(final Item i1 : it1.atomValue(info)) {
        for(final Item i2 : it2.atomValue(info)) {
          if(op.op.eval(i1, i2, cl, info)) return true;
        }
      }
      return false;
    }

    final Type t1 = it1.type, t2 = it2.type;
    if(!(it1 instanceof FItem || it2 instanceof FItem) &&
        (t1 == t2 || t1.isUntyped() || t2.isUntyped() ||
        it1 instanceof ANum && it2 instanceof ANum ||
        it1 instanceof AStr && it2 instanceof AStr)) return op.op.eval(it1, it2, cl, info);
    throw Err.diffError(info, it1, it2);
  }