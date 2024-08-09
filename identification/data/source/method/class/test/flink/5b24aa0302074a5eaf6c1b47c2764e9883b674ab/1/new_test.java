@Test
	public void testClearAndPut() throws Exception {

		Runnable letterInstance = () -> {};

		for (int i = 0; i < CAPACITY; ++i) {
			Assert.assertTrue(mailbox.tryPutMail(letterInstance));
		}

		List<Runnable> droppedLetters = mailbox.clearAndPut(POISON_LETTER);

		Assert.assertTrue(mailbox.hasMail());
		Assert.assertEquals(POISON_LETTER, mailbox.tryTakeMail().get());
		Assert.assertFalse(mailbox.hasMail());
		Assert.assertEquals(CAPACITY, droppedLetters.size());
	}