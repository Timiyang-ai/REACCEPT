@Test
    public void testUserRatingVector() {
        Collection<Rating> ratings = Lists.newArrayList(
                Rating.create(5, 7, 3.5),
                Rating.create(5, 3, 1.5),
                Rating.create(5, 8, 2)
        );
        SparseVector v = Ratings.userRatingVector(ratings);
        assertEquals(3, v.size());
        assertEquals(7, v.sum(), EPSILON);

        long[] keys = {3, 7, 8};
        double[] values = {1.5, 3.5, 2};
        SparseVector sv = MutableSparseVector.wrap(keys, values);
        assertEquals(sv, v);
    }