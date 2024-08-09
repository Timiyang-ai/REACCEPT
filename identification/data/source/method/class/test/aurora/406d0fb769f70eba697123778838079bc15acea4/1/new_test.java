  private void evaluate(OneWayStatus expectedStatus, Map<Integer, SideEffect> expectedSideEffects) {
    assertEquals(
        new EvaluationResult<>(expectedStatus, expectedSideEffects),
        jobUpdater.evaluate(ImmutableMap.of(), stateProvider));
  }