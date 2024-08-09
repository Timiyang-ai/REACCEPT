@Test
	public void testHasNext() throws ServiceException, InterruptedException {
		final AbstractInvokable memOwner = new DummyInvokable();

		// create the reader
		reader = new CollectionReader<PactInteger>(objects);
		// create the resettable Iterator
		SpillingResettableIterator<PactInteger> iterator = new SpillingResettableIterator<PactInteger>(memman, ioman,
			reader, SpillingResettableIterator.MIN_BUFFER_SIZE * SpillingResettableIterator.MINIMUM_NUMBER_OF_BUFFERS,
			deserializer, memOwner);
		// open the iterator
		try {
			iterator.open();
		} catch (IOException e) {
			Assert.fail("Could not open resettable iterator:" + e.getMessage());
		}

		int cnt = 0;
		while (iterator.hasNext()) {
			iterator.hasNext();
			iterator.next();
			cnt++;
		}

		Assert.assertTrue(cnt + " elements read from iterator, but " + NUM_TESTRECORDS + " expected",
			cnt == NUM_TESTRECORDS);
		
		iterator.close();
	}