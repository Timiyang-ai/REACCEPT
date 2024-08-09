    public void test_toString() {
        ListIntSet set = new ListIntSet();

        assertEquals(set.toString(), "{}");

        set.add(1);

        assertEquals(set.toString(), "{1}");

        set.add(2);

        assertEquals(set.toString(), "{1, 2}");
    }