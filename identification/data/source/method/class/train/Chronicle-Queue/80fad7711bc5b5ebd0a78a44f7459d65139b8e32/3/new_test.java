    @Test
    public void pretouch() {
        long[] pos = {0};
        final StringBuilder record = new StringBuilder();
        PretoucherState ps = new DummyPretoucherState(() -> pos[0] += 4096, 64 << 10, record, () -> false);
        ps.pretouch(null);
        ps.pretouch(null);
        assertEquals("debug none - Reset pretoucher to pos 4096 as the underlying MappedBytes changed.\n" +
                "touchPage 1 til 17 count 17\n" +
                "debug pretouch for only 0 of 17 min: 0 MB.\n" +
                "debug none: Advanced 4 KB, avg 4 KB between pretouch() and 4 KB while mapping of 0 KB \n", record.toString());
    }