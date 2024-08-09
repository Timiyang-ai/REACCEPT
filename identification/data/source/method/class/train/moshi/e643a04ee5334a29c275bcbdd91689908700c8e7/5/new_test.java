  @Test public void newBuilder() throws Exception {
    Moshi moshi = new Moshi.Builder()
        .add(Pizza.class, new PizzaAdapter())
        .build();
    Moshi.Builder newBuilder = moshi.newBuilder();
    for (JsonAdapter.Factory factory : Moshi.BUILT_IN_FACTORIES) {
      assertThat(factory).isNotIn(newBuilder.factories);
    }
  }