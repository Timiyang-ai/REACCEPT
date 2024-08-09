@Test
    public void testRecommend2() {

        List<ScoredId> recs = recommender.recommend(6, 4);
        assertEquals(3, recs.size());
        assertEquals(6, recs.get(0).getId());
        assertEquals(7, recs.get(1).getId());
        assertEquals(9, recs.get(2).getId());

        recs = recommender.recommend(6, 3);
        assertEquals(3, recs.size());
        assertEquals(6, recs.get(0).getId());
        assertEquals(7, recs.get(1).getId());
        assertEquals(9, recs.get(2).getId());

        recs = recommender.recommend(6, 2);
        assertEquals(2, recs.size());
        assertEquals(6, recs.get(0).getId());
        assertEquals(7, recs.get(1).getId());

        recs = recommender.recommend(6, 1);
        assertEquals(1, recs.size());
        assertTrue(recs.contains(6));

        recs = recommender.recommend(6, 0);
        assertTrue(recs.isEmpty());

        recs = recommender.recommend(6, -1);
        assertEquals(3, recs.size());
        assertEquals(6, recs.get(0).getId());
        assertEquals(7, recs.get(1).getId());
        assertEquals(9, recs.get(2).getId());
    }