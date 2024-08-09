@Test
	public void testKeysOnly() throws Exception
	{
		TestObjectify ofy = this.fact.begin();
		Query<Trivial> q = ofy.load().type(Trivial.class);
		
		int count = 0;
		for (Key<Trivial> k: q.keys())
		{
			assert keys.contains(k);
			count++;
		}
		
		assert count == keys.size();
		
		// Just for the hell of it, test the other methods
		assert q.count() == keys.size();
		
		q = q.limit(2);
		for (Key<Trivial> k: q.keys())
			assert keys.contains(k);
		
		Key<Trivial> first = q.keys().first().key();
		assert first.equals(this.keys.get(0));
		
		q = q.offset(1);
		Key<Trivial> second = q.keys().first().key();
		assert second.equals(this.keys.get(1));
	}