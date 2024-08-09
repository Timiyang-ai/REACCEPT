@Test
    public void testNotBlank() {
        QueryCriterion criterion1 = new NotNullCriterion("name");
        QueryCriterion criterion2 = new NotEqCriterion("name", "");
        QueryCriterion criterion3 = new AndCriterion(criterion1, criterion2);
        
        assertTrue(instance.notBlank("name").getQueryCriterions()
                .contains(criterion3));
    }