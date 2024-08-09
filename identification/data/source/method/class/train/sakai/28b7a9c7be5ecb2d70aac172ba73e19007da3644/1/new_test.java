	@Test
	public void setOtherRecipients() {
		String rcpts = "nobody@example.com;someone@example.com,whatever@example.com";

		entry.setOtherRecipients(rcpts);
		assertFalse(entry.getOtherRecipients().isEmpty());
		assertEquals(3, entry.getOtherRecipients().size());
		assertEquals("nobody@example.com", entry.getOtherRecipients().get(0));
		assertEquals("someone@example.com", entry.getOtherRecipients().get(1));
		assertEquals("whatever@example.com", entry.getOtherRecipients().get(2));

		entry.setOtherRecipients("");

	}