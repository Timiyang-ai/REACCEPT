@Test(expected = IllegalArgumentException.class)
	public void testGetConversationData() {
		Map<String, Object> params = new HashMap<String,Object>();
		service.getConversationData(params);
	}