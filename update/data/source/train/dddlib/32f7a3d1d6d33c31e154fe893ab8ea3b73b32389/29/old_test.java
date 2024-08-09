@Test
    public void testIn_Array() {
        Object[] criterions = new Object[] {"a", "b"};
        assertTrue(instance.in("name", criterions).getQueryCriterions()
                .contains(new InCriterion("name", criterions)));
    }