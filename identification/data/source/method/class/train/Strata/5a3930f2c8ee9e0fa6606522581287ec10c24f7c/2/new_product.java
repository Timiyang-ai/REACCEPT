public static ScenarioDefinition ofMappings(List<? extends PerturbationMapping<?>> mapping) {
    ArgChecker.notEmpty(mapping, "mappings");

    int numScenarios = countScenarios(mapping, false);

    for (int i = 1; i < mapping.size(); i++) {
      if (mapping.get(i).getScenarioCount() != numScenarios) {
        throw new IllegalArgumentException(
            "All mappings must have the same number of perturbations. First mapping" +
                " has " + numScenarios + " perturbations, mapping " + i + " has " +
                mapping.get(i).getScenarioCount());
      }
    }
    return new ScenarioDefinition(mapping, generateNames(numScenarios));
  }