@Test
    public void testIsBlank() {
        QueryCriterion criterion1 = Criteria.isNull("name");
        QueryCriterion criterion2 = Criteria.eq("name", "");
        QueryCriterion criterion3 = Criteria.or(criterion1, criterion2);
        
        assertEquals(criterion3, instance.isBlank("name").getQueryCriterion());
    }