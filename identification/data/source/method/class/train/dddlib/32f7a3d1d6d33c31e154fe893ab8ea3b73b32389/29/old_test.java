@Test
    public void testSizeGe() {
        assertTrue(instance.sizeGe("id", 3).getQueryCriterions()
                .contains(new SizeGeCriterion("id", 3)));
    }