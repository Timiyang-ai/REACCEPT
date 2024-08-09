  @Test
  public void getUfsFreeBytes() {
    Response response = mHandler.getUfsFreeBytes();
    try {
      assertNotNull("Response must be not null!", response);
      assertNotNull("Response must have a entry!", response.getEntity());
      assertEquals("Entry must be a Long!", Long.class, response.getEntity().getClass());
      Long entry = (Long) response.getEntity();
      assertEquals(UFS_SPACE_FREE, entry.longValue());
    } finally {
      response.close();
    }
  }