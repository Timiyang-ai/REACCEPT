@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "hashCode",
        args = {}
    )
    public void test_hashCode2() {
        // Test for method int java.lang.Integer.hashCode()

        Integer i1 = new Integer(1000);
        Integer i2 = new Integer(-1000);
        assertTrue("Returned incorrect hashcode", i1.hashCode() == 1000
                && (i2.hashCode() == -1000));
    }