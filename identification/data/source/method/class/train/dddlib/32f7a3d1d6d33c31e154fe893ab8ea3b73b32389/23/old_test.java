@Test
    public void testSizeNotEq() {
        assertTrue(instance.sizeNotEq("id", 3).getQueryCriterions()
                .contains(new SizeNotEqCriterion("id", 3)));
    }