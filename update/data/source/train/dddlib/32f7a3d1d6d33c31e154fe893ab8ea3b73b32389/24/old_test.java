@Test
    public void testStartsWithText() {
        assertTrue(instance.startsWithText("name", "a").getQueryCriterions()
                .contains(new StartsWithTextCriterion("name", "a")));
    }