@Test
	public void testNext() throws ServiceException, InterruptedException {
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

		PactInteger record;
		int cnt = 0;
		while (cnt < NUM_TESTRECORDS) {
			record = iterator.next();
			Assert.assertTrue("Record was not read from iterator", record != null);
			cnt++;
		}

		record = iterator.next();
		Assert.assertTrue("Too many records were read from iterator", record == null);
		
		iterator.close();
	}