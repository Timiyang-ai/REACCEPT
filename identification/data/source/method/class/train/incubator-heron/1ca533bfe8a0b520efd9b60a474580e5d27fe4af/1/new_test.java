@Test
  public void testKillTopology() throws Exception {

    HttpJsonClient httpJsonClient = PowerMockito.spy(new HttpJsonClient(""));
    PowerMockito.whenNew(HttpJsonClient.class).withAnyArguments().thenReturn(httpJsonClient);

    // Test a bad DELETE
    PowerMockito.doThrow(new IOException()).when(httpJsonClient,  "delete", Mockito.anyInt());
    Assert.assertFalse(controller.killTopology());

    // Test a good path
    PowerMockito.doNothing().when(httpJsonClient).delete(Mockito.anyInt());
    Assert.assertTrue(controller.killTopology());
  }