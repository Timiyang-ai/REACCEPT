  @Test
  public void getEffectiveInterceptors_default() {
    builder.intercept(DUMMY_USER_INTERCEPTOR);
    List<ClientInterceptor> effectiveInterceptors = builder.getEffectiveInterceptors();
    assertEquals(3, effectiveInterceptors.size());
    assertThat(effectiveInterceptors.get(0))
        .isInstanceOf(CensusTracingModule.TracingClientInterceptor.class);
    assertThat(effectiveInterceptors.get(1))
        .isInstanceOf(CensusStatsModule.StatsClientInterceptor.class);
    assertThat(effectiveInterceptors.get(2)).isSameInstanceAs(DUMMY_USER_INTERCEPTOR);
  }