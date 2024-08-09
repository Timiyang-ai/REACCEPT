@Test
    public void testSizeLt() {
    	assertEquals(new SizeLtCriterion("id", 3), instance.sizeLt("id", 3).getQueryCriterion());
    }