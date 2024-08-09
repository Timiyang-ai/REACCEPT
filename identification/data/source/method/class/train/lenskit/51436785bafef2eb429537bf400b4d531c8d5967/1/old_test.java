@Test
    public void testItemRatingVector() {
        Collection<Rating> ratings = new ArrayList<Rating>();
        ratings.add(Rating.create(7, 5, 3.5));
        RatingBuilder rb = new RatingBuilder();
        ratings.add(Rating.create(3, 5, 1.5));
        ratings.add(Rating.create(8, 5, 2));
        SparseVector v = Ratings.itemRatingVector(ratings);
        assertEquals(3, v.size());
        assertEquals(7, v.sum(), EPSILON);

        long[] keys = {3, 7, 8};
        double[] values = {1.5, 3.5, 2};
        SparseVector sv = MutableSparseVector.wrap(keys, values);
        assertEquals(sv, v);
    }