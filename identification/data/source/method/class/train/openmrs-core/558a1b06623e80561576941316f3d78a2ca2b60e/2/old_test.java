	@Test
	public void putAll_shouldFilterOutNonMatchingLocaleMessagesFromBatchAdd() {
		Map<String, PresentationMessage> messageMap = new HashMap<>();
		messageMap.put(EXPECTED_MESSAGE_KEY, MESSAGE_EN);
		messageMap.put("wrong_locale", MESSAGE_DE);
		
		presentationMessages.putAll(messageMap);
		
		assertEquals(1, presentationMessages.size());
		assertTrue(presentationMessages.containsKey(EXPECTED_MESSAGE_KEY));
	}