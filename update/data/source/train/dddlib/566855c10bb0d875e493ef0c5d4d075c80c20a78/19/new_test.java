@Test
    public void testBetween() {
    	assertEquals(Criteria.between("name", "a", "b"), instance.between("name", "a", "b").getQueryCriterion());
    }