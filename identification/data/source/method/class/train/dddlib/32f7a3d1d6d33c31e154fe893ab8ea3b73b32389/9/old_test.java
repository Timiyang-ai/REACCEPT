@Test
    public void testSizeEq() {
        assertTrue(instance.sizeEq("id", 3).getQueryCriterions()
                .contains(new SizeEqCriterion("id", 3)));
    }