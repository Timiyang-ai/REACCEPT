	@Test
	public void setRecipients_shouldSetMultipleRecipients() {
		Message testMessage = createTestMessage1();
		
		String recipients = "recipient1@example.com,recipient2@example.com";
		
		testMessage.setRecipients(recipients);
		
		assertEquals(testMessage.getRecipients(), recipients);
	}