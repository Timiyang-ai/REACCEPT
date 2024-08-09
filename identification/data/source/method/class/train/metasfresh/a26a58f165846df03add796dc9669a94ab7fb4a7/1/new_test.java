	@Test
	public void test_invalid()
	{
		final EMailSentStatus status = EMailSentStatus.invalid("invalidError1");
		Assert.assertEquals(false, status.isSentOK());
		Assert.assertEquals(false, status.isSentConnectionError());
		Assert.assertEquals("invalidError1", status.getSentMsg());
		Assert.assertEquals(null, status.getMessageId());
	}