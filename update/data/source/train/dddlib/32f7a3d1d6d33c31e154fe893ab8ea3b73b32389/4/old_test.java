@Test
    public void testNotIn_Array() {
        Object[] criterions = new Object[] {"a", "b"};
        assertTrue(instance.notIn("name", criterions).getQueryCriterions()
                .contains(new NotInCriterion("name", criterions)));
    }