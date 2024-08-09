@Override
  public String getAsOfPredicate(String asOfTableAlias, String asOfSysPeriod) {

    // for Postgres we are using the 'timestamp with timezone range' data type
    // as our sys_period column so hence the predicate below
    return asOfTableAlias + "." + asOfSysPeriod + " @> ?::timestamptz";
  }