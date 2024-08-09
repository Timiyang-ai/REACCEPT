@Override
  public String getAsOfPredicate(String asOfTableAlias, String asOfSysPeriod) {

    // for Postgres we are using the 'timestamp with timezone range' data type
    // as our sys_period column so hence the predicate below
    //noinspection StringBufferReplaceableByString
    StringBuilder sb = new StringBuilder(40);
    sb.append(asOfTableAlias).append(".").append(asOfSysPeriod).append(" @> ?::timestamptz");
    return sb.toString();
  }