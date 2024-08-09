@Test
    public void testSizeLe() {
    	assertEquals(Criteria.sizeLe("id", 3), instance.sizeLe("id", 3).getQueryCriterion());
    }