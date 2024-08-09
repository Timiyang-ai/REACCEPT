@TestTargetNew(
        level = TestLevel.PARTIAL,
        notes = "Checks one value.",
        method = "toString",
        args = {}
    )
    public void test_toString2() {
        // Test for method java.lang.String java.lang.Integer.toString()

        Integer i = new Integer(-80001);

        assertEquals("Returned incorrect String", "-80001", i.toString());
    }