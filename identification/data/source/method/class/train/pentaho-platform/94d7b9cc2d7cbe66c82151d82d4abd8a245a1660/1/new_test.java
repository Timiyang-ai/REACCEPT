  @Test
  public void invokeActionTest() throws Exception {
    Map<String, Serializable> testMap = new HashMap<>();
    testMap.put( ActionUtil.QUARTZ_ACTIONCLASS, "one" );
    testMap.put( ActionUtil.QUARTZ_ACTIONUSER, "two" );
    IAction iaction = ActionUtil.createActionBean( ActionSequenceAction.class.getName(), null );
    ActionInvokeStatus actionInvokeStatus =
      (ActionInvokeStatus) defaultActionInvoker.invokeAction( iaction, "aUser", testMap );
    Assert.assertFalse( actionInvokeStatus.requiresUpdate() );
  }