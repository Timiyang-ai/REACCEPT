  @Deployment(resources = DMN_DECISION_LITERAL_EXPRESSION)
  @Test
  public void evaluateDecisionById() {
    DecisionDefinition decisionDefinition = repositoryService.createDecisionDefinitionQuery().singleResult();

    DmnDecisionResult decisionResult = decisionService
        .evaluateDecisionById(decisionDefinition.getId())
        .variables(createVariables())
        .evaluate();

    assertThatDecisionHasResult(decisionResult, RESULT_OF_FIRST_VERSION);
  }