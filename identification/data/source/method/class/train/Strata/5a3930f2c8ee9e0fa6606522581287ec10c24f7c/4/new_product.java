public static ScenarioDefinition ofMappings(
      List<? extends PerturbationMapping<?>> mappings,
      List<String> scenarioNames) {

    ArgChecker.notNull(scenarioNames, "scenarioNames");

    int numScenarios = scenarioNames.size();

    for (int i = 0; i < mappings.size(); i++) {
      if (mappings.get(i).getScenarioCount() != numScenarios) {
        throw new IllegalArgumentException(
            "Each mapping must contain the same number of scenarios as the definition. There are " +
                numScenarios + " scenarios in the definition, mapping " + i + " has " +
                mappings.get(i).getScenarioCount() + " scenarios.");
      }
    }
    return new ScenarioDefinition(mappings, scenarioNames);
  }