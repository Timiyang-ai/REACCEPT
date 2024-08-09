@Test
  public void testClearRecursively() {
    FinishedTriggersProperties.verifyClearRecursively(
        FinishedTriggersSet.fromSet(new HashSet<ExecutableTrigger<?>>()));
  }