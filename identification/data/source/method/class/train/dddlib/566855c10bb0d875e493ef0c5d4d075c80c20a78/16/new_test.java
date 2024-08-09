@Test
    public void testSizeLt() {
    	assertEquals(Criteria.sizeLt("id", 3), instance.sizeLt("id", 3).getQueryCriterion());
    }