  @Test
  public void registerWindowingStrategy() throws IOException {
    WindowingStrategy<?, ?> strategy =
        WindowingStrategy.globalDefault().withMode(AccumulationMode.ACCUMULATING_FIRED_PANES);
    String name = components.registerWindowingStrategy(strategy);
    assertThat(name, not(isEmptyOrNullString()));

    components.toComponents().getWindowingStrategiesOrThrow(name);
  }