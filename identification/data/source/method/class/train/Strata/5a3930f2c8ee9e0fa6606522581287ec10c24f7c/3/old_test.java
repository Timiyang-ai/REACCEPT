  @Test
  public void ofMappings() {
    List<PerturbationMapping<Object>> mappings = ImmutableList.of(MAPPING_A, MAPPING_B, MAPPING_C);
    ScenarioDefinition scenarioDefinition = ScenarioDefinition.ofMappings(mappings);
    List<String> scenarioNames = ImmutableList.of("Scenario 1", "Scenario 2");
    assertThat(scenarioDefinition.getMappings()).isEqualTo(mappings);
    assertThat(scenarioDefinition.getScenarioNames()).isEqualTo(scenarioNames);
  }