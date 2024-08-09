@Test
    public void testNotInArray() {
        Object[] criterions = new Object[] {"a", "b"};
    	assertEquals(new NotInCriterion("name", criterions), instance.notIn("name", criterions).getQueryCriterion());
    }