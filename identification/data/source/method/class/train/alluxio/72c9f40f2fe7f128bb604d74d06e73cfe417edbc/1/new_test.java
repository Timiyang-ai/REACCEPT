  @Test
  public void getFreeBytes() {
    Response response = mHandler.getFreeBytes();
    try {
      assertNotNull("Response must be not null!", response);
      assertNotNull("Response must have a entry!", response.getEntity());
      assertEquals("Entry must be a Long!", Long.class, response.getEntity().getClass());
      Long entry = (Long) response.getEntity();

      long usedSum = 0;
      for (Map.Entry<String, Long> entry1 : WORKER1_USED_BYTES_ON_TIERS.entrySet()) {
        Long totalBytes = entry1.getValue();
        usedSum = usedSum + totalBytes;
      }
      for (Map.Entry<String, Long> entry1 : WORKER2_USED_BYTES_ON_TIERS.entrySet()) {
        Long totalBytes = entry1.getValue();
        usedSum = usedSum + totalBytes;
      }

      long totalSum = 0;
      for (Map.Entry<String, Long> entry1 : WORKER1_TOTAL_BYTES_ON_TIERS.entrySet()) {
        Long totalBytes = entry1.getValue();
        totalSum = totalSum + totalBytes;
      }
      for (Map.Entry<String, Long> entry1 : WORKER2_TOTAL_BYTES_ON_TIERS.entrySet()) {
        Long totalBytes = entry1.getValue();
        totalSum = totalSum + totalBytes;
      }

      assertEquals(totalSum - usedSum, entry.longValue());
    } finally {
      response.close();
    }
  }