@Override
  public CQueryPlanKey prepare(BeanQueryRequest<?> request) {

    prepareExpressions(request);
    prepareForPaging((OrmQueryRequest) request);
    queryPlanKey = createQueryPlanKey();
    return queryPlanKey;
  }