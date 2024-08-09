@Test(expected = IllegalArgumentException.class)
  public void testGetConversationData() {
    final Map<String, Object> params = new HashMap<String, Object>();
    service.getConversationData(params);
  }