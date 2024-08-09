public static ScenarioDefinition ofMappings(
      List<? extends PerturbationMapping<?>> mappings,
      List<String> scenarioNames) {

    ArgChecker.notNull(scenarioNames, "scenarioNames");

    int numScenarios = scenarioNames.size();

    for (int i = 0; i < mappings.size(); i++) {
      if (mappings.get(i).getPerturbations().size() != numScenarios) {
        throw new IllegalArgumentException(
            "Each mapping must contain the same number of perturbations as there are scenarios. There are " +
                numScenarios + " scenarios, mapping " + i + " has " + mappings.get(i).getPerturbations().size() +
                " perturbations.");
      }
    }
    return new ScenarioDefinition(createMappings(mappings, false), scenarioNames);
  }