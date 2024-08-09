@Test
  public void testInvalidateTx() throws Exception {
    TransactionSystemClient txClient = AppFabricTestsSuite.getTxClient();

    Transaction tx1 = txClient.startShort();
    HttpResponse response = AppFabricTestsSuite.doPost("/v2/transactions/" + tx1.getWritePointer() + "/invalidate");
    Assert.assertEquals(200, response.getStatusLine().getStatusCode());

    Transaction tx2 = txClient.startShort();
    txClient.commit(tx2);
    response = AppFabricTestsSuite.doPost("/v2/transactions/" + tx2.getWritePointer() + "/invalidate");
    Assert.assertEquals(409, response.getStatusLine().getStatusCode());

    Assert.assertEquals(400, AppFabricTestsSuite.doPost("/v2/transactions/foobar/invalidate")
                               .getStatusLine().getStatusCode());
  }