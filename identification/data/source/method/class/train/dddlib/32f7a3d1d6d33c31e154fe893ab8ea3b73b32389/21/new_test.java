@Test
    public void testSizeLe() {
    	assertEquals(new SizeLeCriterion("id", 3), instance.sizeLe("id", 3).getQueryCriterion());
    }