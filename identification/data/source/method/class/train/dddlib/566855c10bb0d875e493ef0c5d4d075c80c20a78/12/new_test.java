@Test
    public void testIsEmpty() {
    	assertEquals(Criteria.isEmpty("name"), instance.isEmpty("name").getQueryCriterion());
    }