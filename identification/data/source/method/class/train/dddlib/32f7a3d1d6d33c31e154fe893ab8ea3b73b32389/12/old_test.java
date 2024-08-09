@Test
    public void testBetween() {
        assertTrue(instance.between("name", "a", "b").getQueryCriterions()
                .contains(new BetweenCriterion("name", "a", "b")));
    }