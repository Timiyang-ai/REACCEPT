@Test
  public void testRunInBackground() throws Exception {

    // mock the RunnableAction and how it's created by the resource
    final ActionResource.CallableAction runnableAction = Mockito.spy( ActionResource.CallableAction.class );
    Mockito.doReturn( runnableAction ).when( resourceMock ).createCallable( actionId, actionClassName, actionUser,
      actionParams );

    // call the runInBackground methos
    resourceMock.invokeAction( ActionUtil.INVOKER_DEFAULT_ASYNC_EXEC_VALUE, actionId, actionClassName, actionUser, actionParams );

    // verify that the createRunnable method was called with the expected parameters
    Mockito.verify( resourceMock, Mockito.times( 1 ) ).createCallable( actionId, actionClassName, actionUser,
      actionParams );

    // verity that the executor submit method was called to execute the expected RunnableAction
    Mockito.verify( resourceMock.executorService, Mockito.times( 1 ) ).submit( runnableAction );
  }