@Test
  public void testSubmitTopology() throws Exception {
    HttpURLConnection httpURLConnection = Mockito.mock(HttpURLConnection.class);
    final String[] appConf = {"{pod1: conf}", "{pod2: conf}"};

    // Failed to get connection
    PowerMockito.spy(NetworkUtils.class);
    PowerMockito.doReturn(null).when(NetworkUtils.class, "getHttpConnection", Mockito.anyString());
    Assert.assertFalse(controller.submitTopology(appConf));
    PowerMockito.verifyStatic();
    NetworkUtils.getHttpConnection(Mockito.anyString());

    // Failed to send request
    PowerMockito.spy(NetworkUtils.class);
    PowerMockito.doReturn(httpURLConnection)
        .when(NetworkUtils.class, "getHttpConnection", Mockito.anyString());
    PowerMockito.doReturn(false)
        .when(NetworkUtils.class, "sendHttpPostRequest",
            Mockito.any(HttpURLConnection.class),
            Mockito.anyString(),
            Mockito.any(byte[].class));
    Assert.assertFalse(controller.submitTopology(appConf));
    PowerMockito.verifyStatic();
    NetworkUtils.getHttpConnection(Mockito.anyString());
    NetworkUtils.sendHttpPostRequest(
        Mockito.any(HttpURLConnection.class),
        Mockito.anyString(),
        Mockito.any(byte[].class));

    // Failed to get response
    PowerMockito.spy(NetworkUtils.class);
    PowerMockito.doReturn(httpURLConnection)
        .when(NetworkUtils.class, "getHttpConnection", Mockito.anyString());
    PowerMockito.doReturn(true)
        .when(NetworkUtils.class, "sendHttpPostRequest",
            Mockito.any(HttpURLConnection.class),
            Mockito.anyString(),
            Mockito.any(byte[].class));
    PowerMockito.doReturn(false)
        .when(NetworkUtils.class, "checkHttpResponseCode",
            Mockito.any(HttpURLConnection.class), Mockito.anyInt());
    Assert.assertFalse(controller.submitTopology(appConf));
    PowerMockito.verifyStatic();
    NetworkUtils.getHttpConnection(Mockito.anyString());
    NetworkUtils.sendHttpPostRequest(
        Mockito.any(HttpURLConnection.class),
        Mockito.anyString(),
        Mockito.any(byte[].class));
    NetworkUtils.checkHttpResponseCode(Mockito.any(HttpURLConnection.class), Mockito.anyInt());

    // Success
    PowerMockito.spy(NetworkUtils.class);
    PowerMockito.doReturn(httpURLConnection)
        .when(NetworkUtils.class, "getHttpConnection", Mockito.anyString());
    PowerMockito.doReturn(true)
        .when(NetworkUtils.class, "sendHttpPostRequest",
            Mockito.any(HttpURLConnection.class),
            Mockito.anyString(),
            Mockito.any(byte[].class));
    PowerMockito.doReturn(true)
        .when(NetworkUtils.class, "checkHttpResponseCode",
            Mockito.any(HttpURLConnection.class), Mockito.anyInt());

    // Sample app conf with 2 pods -- verify it ran twice
    PowerMockito.spy(NetworkUtils.class);
    PowerMockito.doReturn(httpURLConnection)
        .when(NetworkUtils.class, "getHttpConnection", Mockito.anyString());
    PowerMockito.doReturn(true)
        .when(NetworkUtils.class, "sendHttpPostRequest",
            Mockito.any(HttpURLConnection.class),
            Mockito.anyString(),
            Mockito.any(byte[].class));
    PowerMockito.doReturn(true)
        .when(NetworkUtils.class, "checkHttpResponseCode",
            Mockito.any(HttpURLConnection.class), Mockito.anyInt());

    Assert.assertTrue(controller.submitTopology(appConf));

    // Verify 2 times
    PowerMockito.verifyStatic(times(2));
    NetworkUtils.getHttpConnection(Mockito.anyString());
    NetworkUtils.sendHttpPostRequest(
        Mockito.any(HttpURLConnection.class),
        Mockito.anyString(),
        Mockito.any(byte[].class));
    NetworkUtils.checkHttpResponseCode(Mockito.any(HttpURLConnection.class), Mockito.anyInt());
  }