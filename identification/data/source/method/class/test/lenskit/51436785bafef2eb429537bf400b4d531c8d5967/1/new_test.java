@Test
    public void testUserRatingVector() {
        Collection<Rating> ratings = Lists.newArrayList(
                Rating.create(5, 7, 3.5),
                Rating.create(5, 3, 1.5),
                Rating.create(5, 8, 2)
        );
        Long2DoubleMap v = Ratings.userRatingVector(ratings);
        assertEquals(3, v.size());
        assertEquals(7, Vectors.sum(v), EPSILON);

        long[] keys = {3, 7, 8};
        double[] values = {1.5, 3.5, 2};
        Long2DoubleSortedArrayMap sv = Long2DoubleSortedArrayMap.wrap(SortedKeyIndex.create(keys), values);
        assertEquals(sv, v);
    }