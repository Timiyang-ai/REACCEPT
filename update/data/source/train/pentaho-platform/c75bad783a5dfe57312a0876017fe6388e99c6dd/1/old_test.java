@Test
  public void testRunInBackground() throws Exception {

    // mock the RunnableAction and how it's created by the resource
    final ActionResource.RunnableAction runnableAction = Mockito.spy( ActionResource.RunnableAction.class );
    Mockito.doReturn( runnableAction ).when( resourceMock ).createRunnable( actionId, actionClassName, actionUser,
      actionParams );

    // call the runInBackground methos
    resourceMock.runInBackground( actionId, actionClassName, actionUser, actionParams );

    // verify that the createRunnable method was called with the expected parameters
    Mockito.verify( resourceMock, Mockito.times( 1 ) ).createRunnable( actionId, actionClassName, actionUser,
      actionParams );

    // verity that the executor submit method was called to execute the expected RunnableAction
    Mockito.verify( resourceMock.executorService, Mockito.times( 1 ) ).submit( runnableAction );
  }