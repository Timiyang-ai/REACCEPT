@Test
    public void testInArray() {
        Object[] criterions = new Object[] {"a", "b"};
    	assertEquals(new InCriterion("name", criterions), instance.in("name", criterions).getQueryCriterion());
    }