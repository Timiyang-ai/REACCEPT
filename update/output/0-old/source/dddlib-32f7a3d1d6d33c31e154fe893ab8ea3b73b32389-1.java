@Test
    public void testSizeGt() {
        assertTrue(instance.sizeGt("id", 3).getQueryCriterions()
                .contains(new SizeGtCriterion("id", 3)));
    }