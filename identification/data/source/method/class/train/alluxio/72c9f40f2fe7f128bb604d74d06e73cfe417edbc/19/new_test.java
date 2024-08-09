  @Test
  public void getUsedBytes() {
    Response response = mHandler.getUsedBytes();
    try {
      assertNotNull("Response must be not null!", response);
      assertNotNull("Response must have a entry!", response.getEntity());
      assertEquals("Entry must be a Long!", Long.class, response.getEntity().getClass());
      Long entry = (Long) response.getEntity();
      long sum = 0;
      for (Map.Entry<String, Long> entry1 : WORKER1_USED_BYTES_ON_TIERS.entrySet()) {
        Long totalBytes = entry1.getValue();
        sum = sum + totalBytes;
      }
      for (Map.Entry<String, Long> entry1 : WORKER2_USED_BYTES_ON_TIERS.entrySet()) {
        Long totalBytes = entry1.getValue();
        sum = sum + totalBytes;
      }
      assertEquals(sum, entry.longValue());
    } finally {
      response.close();
    }
  }