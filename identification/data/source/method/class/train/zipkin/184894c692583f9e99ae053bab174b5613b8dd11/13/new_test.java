  @Test public void getTraces_fansOutAgainstServices() {
    Call<List<List<Span>>> call = spanStore.getTraces(queryBuilder.build());

    assertThat(call.toString()).contains(FlatMapServiceNamesToInput.class.getSimpleName());
  }