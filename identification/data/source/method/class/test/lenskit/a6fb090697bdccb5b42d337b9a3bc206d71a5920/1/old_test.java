@Test
	public void testRecommend2() {
		
		LongList recs = extractIds(recommender.recommend(6, 4));
		assertEquals(3, recs.size());
		assertEquals(6, recs.getLong(0));
		assertEquals(7, recs.getLong(1));
		assertEquals(9, recs.getLong(2));
		
		recs = extractIds(recommender.recommend(6, 3));
		assertEquals(3, recs.size());
		assertEquals(6, recs.getLong(0));
		assertEquals(7, recs.getLong(1));
		assertEquals(9, recs.getLong(2));
		
		recs = extractIds(recommender.recommend(6, 2));
		assertEquals(2, recs.size());
		assertEquals(6, recs.getLong(0));
		assertEquals(7, recs.getLong(1));
		
		recs = extractIds(recommender.recommend(6, 1));
		assertEquals(1, recs.size());
		assertTrue(recs.contains(6));
		
		recs = extractIds(recommender.recommend(6, 0));
		assertTrue(recs.isEmpty());
		
		recs = extractIds(recommender.recommend(6, -1));
		assertEquals(3, recs.size());
		assertEquals(6, recs.getLong(0));
		assertEquals(7, recs.getLong(1));
		assertEquals(9, recs.getLong(2));
	}