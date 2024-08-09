@Override
  public CQueryPlanKey prepare(SpiOrmQueryRequest<T> request) {

    prepareExpressions(request);
    prepareForPaging(request);
    queryPlanKey = createQueryPlanKey();
    return queryPlanKey;
  }