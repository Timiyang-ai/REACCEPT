  private <T> void prepare(DefaultOrmQuery<T> q1, DefaultOrmQuery<T> q2) {

    OrmQueryRequest<T> r1 = createQueryRequest(SpiQuery.Type.LIST, q1, null);
    q1.prepare(r1);

    OrmQueryRequest<T> r2 = createQueryRequest(SpiQuery.Type.LIST, q2, null);
    q2.prepare(r2);
  }