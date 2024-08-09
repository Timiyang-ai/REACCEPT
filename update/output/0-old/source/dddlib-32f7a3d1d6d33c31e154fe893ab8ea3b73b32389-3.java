@Test
    public void testNotNull() {
        assertTrue(instance.notNull("name").getQueryCriterions()
                .contains(new NotNullCriterion("name")));
    }