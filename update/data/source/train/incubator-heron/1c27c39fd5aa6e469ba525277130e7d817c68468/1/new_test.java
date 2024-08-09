@Test
  public void testRequestSchedulerService() throws Exception {
    HttpServiceSchedulerClient client =
        Mockito.spy(new HttpServiceSchedulerClient(config, runtime, SCHEDULER_HTTP_ENDPOINT));

    // Failed to create new http connection
    PowerMockito.spy(NetworkUtils.class);
    PowerMockito.doReturn(null).
        when(NetworkUtils.class, "getHttpConnection", Mockito.any(String.class));
    Mockito.doReturn(COMMAND_ENDPOINT).
        when(client).getCommandEndpoint(Mockito.any(String.class), Mockito.any(Command.class));
    Assert.assertFalse(
        client.requestSchedulerService(
            Mockito.any(Command.class), Mockito.any(byte[].class)));

    HttpURLConnection connection = Mockito.mock(HttpURLConnection.class);
    PowerMockito.doReturn(connection).
        when(NetworkUtils.class, "getHttpConnection", Mockito.any(String.class));

    // Failed to send http post request
    PowerMockito.doReturn(false).
        when(NetworkUtils.class, "sendHttpPostRequest",
            Mockito.eq(connection), Mockito.any(String.class), Mockito.any(byte[].class));
    Assert.assertFalse(
        client.requestSchedulerService(
            Mockito.any(Command.class), Mockito.any(byte[].class)));
    Mockito.verify(connection).disconnect();

    // Received non-ok response
    PowerMockito.doReturn(true).
        when(NetworkUtils.class, "sendHttpPostRequest",
            Mockito.eq(connection), Mockito.any(String.class), Mockito.any(byte[].class));
    Scheduler.SchedulerResponse notOKResponse = SchedulerUtils.constructSchedulerResponse(false);
    PowerMockito.doReturn(notOKResponse.toByteArray()).
        when(NetworkUtils.class, "readHttpResponse", Mockito.eq(connection));
    Assert.assertFalse(
        client.requestSchedulerService(
            Mockito.any(Command.class), Mockito.any(byte[].class)));
    Mockito.verify(connection, Mockito.times(2)).disconnect();

    // Received ok response -- success case
    Scheduler.SchedulerResponse oKResponse = SchedulerUtils.constructSchedulerResponse(true);
    PowerMockito.doReturn(oKResponse.toByteArray()).
        when(NetworkUtils.class, "readHttpResponse", Mockito.eq(connection));
    Assert.assertTrue(
        client.requestSchedulerService(
            Mockito.any(Command.class), Mockito.any(byte[].class)));
    Mockito.verify(connection, Mockito.times(3)).disconnect();
  }