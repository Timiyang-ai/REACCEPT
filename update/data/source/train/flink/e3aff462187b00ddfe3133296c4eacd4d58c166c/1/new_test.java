@Test
	public void testNext()
	{
		try {
			// create the resettable Iterator
			SpillingResettableIterator<IntValue> iterator = new SpillingResettableIterator<IntValue>(
					this.reader, this.serializer, this.memman, this.ioman, 2 * 32 * 1024, this.memOwner);
			// open the iterator
			try {
				iterator.open();
			} catch (IOException e) {
				Assert.fail("Could not open resettable iterator:" + e.getMessage());
			}
	
			IntValue record;
			int cnt = 0;
			while (cnt < NUM_TESTRECORDS) {
				record = iterator.next();
				Assert.assertTrue("Record was not read from iterator", record != null);
				cnt++;
			}
	
			try {
				record = iterator.next();
				Assert.fail("Too many records were read from iterator.");
			} catch (NoSuchElementException nseex) {
				// expected
			}
			
			iterator.close();
		} catch (Exception ex)  {
			ex.printStackTrace();
			Assert.fail("Test encountered an exception.");
		}
	}