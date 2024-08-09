  @Test
  public void getVersion() {
    Response response = mHandler.getVersion();
    try {
      assertNotNull("Response must be not null!", response);
      assertNotNull("Response must have a entry!", response.getEntity());
      assertEquals("Entry must be a String!", String.class, response.getEntity().getClass());
      String entry = (String) response.getEntity();
      assertEquals("\"" + RuntimeConstants.VERSION + "\"", entry);
    } finally {
      response.close();
    }
  }