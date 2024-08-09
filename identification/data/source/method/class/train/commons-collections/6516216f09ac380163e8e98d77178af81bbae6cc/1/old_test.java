    @Test
    public void containsAll() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        assertTrue("containsAll({1},{1,3}) should return false.", !CollectionUtils.containsAll(one, odds));
        assertTrue("containsAll({1,3},{1}) should return true.", CollectionUtils.containsAll(odds, one));
        assertTrue("containsAll({3},{1,3}) should return false.", !CollectionUtils.containsAll(three, odds));
        assertTrue("containsAll({1,3},{3}) should return true.", CollectionUtils.containsAll(odds, three));
        assertTrue("containsAll({2},{2}) should return true.", CollectionUtils.containsAll(two, two));
        assertTrue("containsAll({1,3},{1,3}) should return true.", CollectionUtils.containsAll(odds, odds));

        assertTrue("containsAll({2},{1,3}) should return false.", !CollectionUtils.containsAll(two, odds));
        assertTrue("containsAll({1,3},{2}) should return false.", !CollectionUtils.containsAll(odds, two));
        assertTrue("containsAll({1},{3}) should return false.", !CollectionUtils.containsAll(one, three));
        assertTrue("containsAll({3},{1}) should return false.", !CollectionUtils.containsAll(three, one));
        assertTrue("containsAll({1,3},{}) should return true.", CollectionUtils.containsAll(odds, empty));
        assertTrue("containsAll({},{1,3}) should return false.", !CollectionUtils.containsAll(empty, odds));
        assertTrue("containsAll({},{}) should return true.", CollectionUtils.containsAll(empty, empty));

        assertTrue("containsAll({1,3},{1,3,1}) should return true.", CollectionUtils.containsAll(odds, multiples));
        assertTrue("containsAll({1,3,1},{1,3,1}) should return true.", CollectionUtils.containsAll(odds, odds));
    }