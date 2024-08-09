@Test
  public void testSubmitTopology() throws Exception {

    HttpJsonClient httpJsonClient = PowerMockito.spy(new HttpJsonClient(""));
    PowerMockito.whenNew(HttpJsonClient.class).withAnyArguments().thenReturn(httpJsonClient);

    // Test a bad POST
    PowerMockito.doThrow(new IOException()).when(httpJsonClient).post(Mockito.anyString(),
        Mockito.anyInt());
    Assert.assertFalse(controller.submitTopology(DEPLOY_CONFS));

    // Test a good path
    PowerMockito.doNothing().when(httpJsonClient).post(Mockito.anyString(), Mockito.anyInt());
    Assert.assertTrue(controller.submitTopology(DEPLOY_CONFS));

  }