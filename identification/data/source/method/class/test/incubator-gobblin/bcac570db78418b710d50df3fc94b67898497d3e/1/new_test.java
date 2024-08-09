@Test
  public void testGetPropertyNameForBranchWithWorkUnitState() {
    WorkUnitState workUnitState = new WorkUnitState();
    workUnitState.setProp(PROPERTY_FOO, PATH_FOO);

    // Test that if the fork id key is not specified that the original property is preserved
    Assert.assertEquals(ForkOperatorUtils.getPropertyNameForBranch(workUnitState, PROPERTY_FOO), PROPERTY_FOO);

    // Test that if the fork id key is set to -1 that the original property is preserved
    workUnitState.setProp(ConfigurationKeys.FORK_BRANCH_ID_KEY, -1);
    Assert.assertEquals(ForkOperatorUtils.getPropertyNameForBranch(workUnitState, PROPERTY_FOO), PROPERTY_FOO);

    // Test that if the fork id key is set to 0 that the new property is properly created
    workUnitState.setProp(ConfigurationKeys.FORK_BRANCH_ID_KEY, 0);
    Assert.assertEquals(ForkOperatorUtils.getPropertyNameForBranch(workUnitState, PROPERTY_FOO), PROPERTY_FOO
        + ".0");
  }