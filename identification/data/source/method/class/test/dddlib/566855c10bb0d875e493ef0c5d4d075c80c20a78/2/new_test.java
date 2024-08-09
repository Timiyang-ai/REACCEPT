@Test
    public void testIsNull() {
    	assertEquals(Criteria.isNull("name"), instance.isNull("name").getQueryCriterion());
    }