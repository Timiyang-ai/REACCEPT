@Test
    public void testNotBlank() {
        QueryCriterion criterion1 = Criteria.notNull("name");
        QueryCriterion criterion2 = Criteria.notEq("name", "");
        QueryCriterion criterion3 = Criteria.and(criterion1, criterion2);
        
        assertEquals(criterion3, instance.notBlank("name").getQueryCriterion());
    }