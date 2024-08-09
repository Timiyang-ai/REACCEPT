  @Test
  public void recommendLayout() {
    Map<String, Map<String, Set<String>>> newServices = ImmutableMap.of(
      "KAFKA",
      ImmutableMap.of("KAFKA_BROKER", emptySet()));

    AddServiceInfo info = addServiceInfoBuilder
      .setStack(stack)
      .setConfig(Configuration.newEmpty())
      .setNewServices(newServices)
      .build();
    AddServiceInfo infoWithRecommendations = adapter.recommendLayout(info);

    Map<String, Map<String, Set<String>>> expectedNewLayout = ImmutableMap.of(
      "KAFKA",
      ImmutableMap.of("KAFKA_BROKER", ImmutableSet.of("c7402"))
    );

    assertEquals(expectedNewLayout, infoWithRecommendations.newServices());
  }