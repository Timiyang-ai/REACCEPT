    @Test
    public void restrictTo_fullyInside() {
        Range r1 = Range.between(5, 10);
        Range r2 = Range.between(4, 11);

        Range r3 = r1.restrictTo(r2);
        assertTrue(r1 == r3);
    }