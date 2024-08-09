@SuppressWarnings("deprecation")
    @Test
    public void testUserRatingVector() {
        Collection<Rating> ratings = new ArrayList<Rating>();
        ratings.add(new SimpleRating(5, 7, 3.5));
        ratings.add(new SimpleRating(5, 3, 1.5));
        ratings.add(new SimpleRating(5, 8, 2));
        SparseVector v = Ratings.userRatingVector(ratings);
        assertEquals(3, v.size());
        assertEquals(7, v.sum(), EPSILON);
        assertEquals(simpleVector(), v);
    }