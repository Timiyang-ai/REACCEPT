  @Test
  public void getUfsCapacityBytes() {
    Response response = mHandler.getUfsCapacityBytes();
    try {
      assertNotNull("Response must be not null!", response);
      assertNotNull("Response must have a entry!", response.getEntity());
      assertEquals("Entry must be a Long!", Long.class, response.getEntity().getClass());
      Long entry = (Long) response.getEntity();
      assertEquals(UFS_SPACE_TOTAL, entry.longValue());
    } finally {
      response.close();
    }
  }