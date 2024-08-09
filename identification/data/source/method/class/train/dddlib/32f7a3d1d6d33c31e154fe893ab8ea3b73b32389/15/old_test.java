@Test
    public void testIsFalse() {
        assertTrue(instance.isFalse("name").getQueryCriterions()
                .contains(new EqCriterion("name", false)));
    }