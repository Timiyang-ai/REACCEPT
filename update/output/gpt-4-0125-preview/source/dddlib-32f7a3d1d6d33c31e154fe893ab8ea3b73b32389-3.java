@Test
    public void testNotNull() {
        assertTrue(instance.notNull("name").getQueryCriterions()
                .contains(Criteria.notNull("name")));
    }