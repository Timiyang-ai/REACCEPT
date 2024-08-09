@Override
  public CQueryPlanKey prepare(SpiOrmQueryRequest<T> request) {

    prepareExpressions(request);
    prepareForPaging();
    queryPlanKey = createQueryPlanKey();
    return queryPlanKey;
  }