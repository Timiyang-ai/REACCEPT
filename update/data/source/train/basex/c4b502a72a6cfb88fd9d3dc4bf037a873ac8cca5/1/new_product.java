Item sum(final Iter iter, final Item it, final boolean avg) throws QueryException {
    Item rs = it.type.isUntyped() ? Dbl.get(it.dbl(info)) : it;
    final boolean num = rs instanceof ANum, dtd = rs.type == DTD, ymd = rs.type == YMD;
    if(!num && !dtd && !ymd) throw SUM_X_X.get(info, rs.type, rs);

    int c = 1;
    for(Item i; (i = iter.next()) != null;) {
      if(i.type.isNumberOrUntyped()) {
        if(!num) throw SUMDUR_X_X.get(info, i.type, i);
      } else {
        if(num) throw SUMNUM_X_X.get(info, i.type, i);
        if(dtd && i.type != DTD || ymd && i.type != YMD) throw SUMDUR_X_X.get(info, i.type, i);
      }
      rs = Calc.PLUS.ev(info, rs, i);
      ++c;
    }
    return avg ? Calc.DIV.ev(info, rs, Int.get(c)) : rs;
  }