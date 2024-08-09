@Test
	public void testClearAndPut() {
		for (int i = 0; i < CAPACITY; ++i) {
			Assert.assertTrue(mailbox.tryPutMail(() -> {}));
		}

		mailbox.clearAndPut(POISON_LETTER);

		Assert.assertTrue(mailbox.hasMail());
		Assert.assertEquals(POISON_LETTER, mailbox.tryTakeMail().get());
		Assert.assertFalse(mailbox.hasMail());
	}