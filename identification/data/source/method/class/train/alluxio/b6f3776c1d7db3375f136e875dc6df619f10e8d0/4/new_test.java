  @Test
  public void getUptimeMs() {
    when(mMasterProcess.getUptimeMs()).thenReturn(100L);
    Response response = mHandler.getUptimeMs();
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