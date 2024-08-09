    @Test
    void requirePositive() {
        testHelper(IS_POSITIVE, IntRangeUtil::requirePositive);
    }