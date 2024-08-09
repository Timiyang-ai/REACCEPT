@Test
    public void testIsTrue() {
        assertTrue(instance.isTrue("name").getQueryCriterions()
                .contains(new EqCriterion("name", true)));
    }