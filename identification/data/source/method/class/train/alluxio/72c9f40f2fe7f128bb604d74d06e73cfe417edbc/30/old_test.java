  @Test
  public void getWorkerCount() {
    Response response = mHandler.getWorkerCount();
    try {
      assertNotNull("Response must be not null!", response);
      assertNotNull("Response must have a entry!", response.getEntity());
      assertEquals("Entry must be a Integer!", Integer.class, response.getEntity().getClass());
      Integer entry = (Integer) response.getEntity();
      assertEquals(Integer.valueOf(2), entry);
    } finally {
      response.close();
    }
  }