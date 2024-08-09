@Test
	public void testRepeatLast() throws ServiceException, InterruptedException {
		final AbstractInvokable memOwner = new DummyInvokable();

		// create the reader
		reader = new CollectionIterator<PactInteger>(objects);
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

		PactInteger record1;
		PactInteger record2;
		PactInteger compare;
		int cnt = 0;
		while (iterator.hasNext()) {

			record1 = iterator.next();
			record2 = iterator.repeatLast();
			compare = objects.get(cnt);

			Assert.assertTrue("Record read with next() does not equal expected value", record1.equals(compare));
			Assert.assertTrue("Record read with next() does not equal record read with lastReturned()",
				record1.equals(record2));
			Assert.assertTrue("Records read with next() and lastReturned have same reference", record1 != record2);

			cnt++;
		}

		Assert.assertTrue(cnt + " elements read from iterator, but " + NUM_TESTRECORDS + " expected",
			cnt == NUM_TESTRECORDS);
		
		iterator.close();
	}