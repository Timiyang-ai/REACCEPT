@Test
  public void testSubmitTopologySuccess() throws Exception {

    HttpJsonClient httpJsonClient = PowerMockito.spy(new HttpJsonClient(""));
    PowerMockito.whenNew(HttpJsonClient.class).withAnyArguments().thenReturn(httpJsonClient);

    // Test a good path
    PowerMockito.doNothing().when(httpJsonClient).post(Mockito.anyString(), Mockito.anyInt());
    Assert.assertTrue(controller.submitTopology(DEPLOY_CONFS));
  }