@Test
  public void testKillTopology() throws Exception {
    HttpURLConnection httpURLConnection = Mockito.mock(HttpURLConnection.class);

    // Failed to get connection
    PowerMockito.spy(NetworkUtils.class);
    PowerMockito.doReturn(null).when(NetworkUtils.class, "getHttpConnection", Mockito.anyString());
    Assert.assertFalse(controller.killTopology());
    PowerMockito.verifyStatic();
    NetworkUtils.getHttpConnection(Mockito.anyString());

    // Failed to send request
    PowerMockito.spy(NetworkUtils.class);
    PowerMockito.doReturn(httpURLConnection)
        .when(NetworkUtils.class, "getHttpConnection", Mockito.anyString());
    PowerMockito.doReturn(false)
        .when(NetworkUtils.class, "sendHttpDeleteRequest", Mockito.any(HttpURLConnection.class));
    Assert.assertFalse(controller.killTopology());
    PowerMockito.verifyStatic();
    NetworkUtils.getHttpConnection(Mockito.anyString());
    NetworkUtils.sendHttpDeleteRequest(Mockito.any(HttpURLConnection.class));

    // Failed to get response
    PowerMockito.spy(NetworkUtils.class);
    PowerMockito.doReturn(httpURLConnection)
        .when(NetworkUtils.class, "getHttpConnection", Mockito.anyString());
    PowerMockito.doReturn(true)
        .when(NetworkUtils.class, "sendHttpDeleteRequest", Mockito.any(HttpURLConnection.class));
    PowerMockito.doReturn(false)
        .when(NetworkUtils.class, "checkHttpResponseCode",
            Mockito.any(HttpURLConnection.class), Mockito.anyInt());
    Assert.assertFalse(controller.killTopology());
    PowerMockito.verifyStatic();
    NetworkUtils.getHttpConnection(Mockito.anyString());
    NetworkUtils.sendHttpDeleteRequest(Mockito.any(HttpURLConnection.class));
    NetworkUtils.checkHttpResponseCode(Mockito.any(HttpURLConnection.class), Mockito.anyInt());

    // Success
    PowerMockito.spy(NetworkUtils.class);
    PowerMockito.doReturn(httpURLConnection)
        .when(NetworkUtils.class, "getHttpConnection", Mockito.anyString());
    PowerMockito.doReturn(true)
        .when(NetworkUtils.class, "sendHttpDeleteRequest", Mockito.any(HttpURLConnection.class));
    PowerMockito.doReturn(true)
        .when(NetworkUtils.class, "checkHttpResponseCode",
            Mockito.any(HttpURLConnection.class), Mockito.anyInt());
    Assert.assertTrue(controller.killTopology());
    PowerMockito.verifyStatic();
    NetworkUtils.getHttpConnection(Mockito.anyString());
    NetworkUtils.sendHttpDeleteRequest(Mockito.any(HttpURLConnection.class));
    NetworkUtils.checkHttpResponseCode(Mockito.any(HttpURLConnection.class), Mockito.anyInt());
  }