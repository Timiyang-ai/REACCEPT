@Test
	public void testHasNext() throws ServiceException, InterruptedException  {
		
		// create the reader
		reader = new CollectionReader<PactInteger>(objects);
		// create the resettable Iterator
		SpillingResettableIterator<PactInteger> iterator = new SpillingResettableIterator<PactInteger>(memman, ioman,
			reader, 1000, deserializer);
		// open the iterator
		try {
			iterator.open();
		} catch (IOException e) {
			Assert.fail("Could not open resettable iterator:" + e.getMessage());
		}
		
		int cnt = 0;
		while(iterator.hasNext()) {
			iterator.hasNext();
			iterator.next();
			cnt++;
		}
		
		Assert.assertTrue(cnt+" elements read from iterator, but "+NUMTESTRECORDS+" expected",cnt == NUMTESTRECORDS);
		
	}