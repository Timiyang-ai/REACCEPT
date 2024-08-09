    @Test
    public void testgetNumber() {
        final Map<String, Number> in = new HashMap<>();
        final Number val = 1000;
        in.put("key", val);

        assertEquals(val.intValue(), MapUtils.getNumber(in,"key", val).intValue(), 0);
        assertEquals(val.intValue(), MapUtils.getNumber(in,"noKey", val).intValue(), 0);
        assertEquals(val.intValue(), MapUtils.getNumber(in,"noKey", (key)->{
            if (true) {
                return val;
            } else {
                return null;
            }
        }).intValue(), 0);

    }