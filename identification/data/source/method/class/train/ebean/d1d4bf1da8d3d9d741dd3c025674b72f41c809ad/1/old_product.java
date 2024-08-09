public CQueryPlanKey prepare(BeanQueryRequest<?> request) {

    prepareExpressions(request);
    queryPlanKey = createQueryPlanKey();
    return queryPlanKey;
  }