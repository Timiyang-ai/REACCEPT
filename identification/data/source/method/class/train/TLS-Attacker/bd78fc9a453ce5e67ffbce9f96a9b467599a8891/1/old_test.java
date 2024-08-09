@Test
    public void testOnDecline() {
        Result result = new Result(false, false, 0, 2, null, null, null);
        assertNull(result.isGoodTrace());
        rule.onDecline(result);
        assertFalse(result.isGoodTrace());
    }