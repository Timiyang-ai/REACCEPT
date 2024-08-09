    @Test
    public void test_parse() {
        SlotKey parsed = SlotKey.parse("metrics_1440m,10,20");
        assertEquals(parsed.getGranularity(), Granularity.MIN_1440);
        assertEquals(parsed.getSlot(), 10);
        assertEquals(parsed.getShard(), 20);

        // invalid results
        Assert.assertNull(SlotKey.parse("metrics_6m,10,20"));
        Assert.assertNull(SlotKey.parse("metrics_1440m,200,20"));
        Assert.assertNull(SlotKey.parse("metrics_1440m,10,128"));
    }