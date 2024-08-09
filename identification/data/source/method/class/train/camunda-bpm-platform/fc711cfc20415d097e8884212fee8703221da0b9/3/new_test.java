  @Deployment(resources = DMN_DECISION_LITERAL_EXPRESSION)
  @Test
  public void evaluateDecisionByKeyAndVersion() {
    testRule.deploy(DMN_DECISION_LITERAL_EXPRESSION_V2);

    DmnDecisionResult decisionResult = decisionService
        .evaluateDecisionByKey(DECISION_DEFINITION_KEY)
        .version(1)
        .variables(createVariables())
        .evaluate();

    assertThatDecisionHasResult(decisionResult, RESULT_OF_FIRST_VERSION);
  }