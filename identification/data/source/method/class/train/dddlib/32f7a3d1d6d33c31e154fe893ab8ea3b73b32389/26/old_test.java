@Test
    public void testIsNull() {
        assertTrue(instance.isNull("name").getQueryCriterions()
                .contains(new IsNullCriterion("name")));
    }