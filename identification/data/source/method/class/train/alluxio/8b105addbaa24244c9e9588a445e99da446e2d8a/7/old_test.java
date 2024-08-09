  @Test
  public void getConfiguration() {
    Response response = mHandler.getConfiguration();
    try {
      assertNotNull("Response must be not null!", response);
      assertNotNull("Response must have a entry!", response.getEntity());
      assertTrue("Entry must be a SortedMap!", (response.getEntity() instanceof SortedMap));
      SortedMap<String, String> entry = (SortedMap<String, String>) response.getEntity();
      assertFalse("Properties Map must be not empty!", (entry.isEmpty()));
    } finally {
      response.close();
    }
  }