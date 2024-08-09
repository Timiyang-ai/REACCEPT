  @Deployment(resources = DMN_DECISION_LITERAL_EXPRESSION)
  @Test
  public void evaluateDecisionByKey() {
    DmnDecisionResult decisionResult = decisionService
        .evaluateDecisionByKey(DECISION_DEFINITION_KEY)
        .variables(createVariables())
        .evaluate();

    assertThatDecisionHasResult(decisionResult, RESULT_OF_FIRST_VERSION);
  }