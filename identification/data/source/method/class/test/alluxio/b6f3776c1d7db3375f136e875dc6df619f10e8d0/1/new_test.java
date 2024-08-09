  @Test
  public void getStartTimeMs() {
    when(mMasterProcess.getStartTimeMs()).thenReturn(100L);
    Response response = mHandler.getStartTimeMs();
    try {
      assertNotNull("Response must be not null!", response);
      assertNotNull("Response must have a entry!", response.getEntity());
      assertEquals("Entry must be a Long!", Long.class, response.getEntity().getClass());
      Long entry = (Long) response.getEntity();
      assertEquals(100L, entry.longValue());
    } finally {
      response.close();
    }
  }