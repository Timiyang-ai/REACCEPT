@Test
    public void testIsBlank() {
        QueryCriterion criterion1 = new IsNullCriterion("name");
        QueryCriterion criterion2 = new EqCriterion("name", "");
        QueryCriterion criterion3 = new OrCriterion(criterion1, criterion2);
        
        assertEquals(criterion3, instance.isBlank("name").getQueryCriterion());
    }