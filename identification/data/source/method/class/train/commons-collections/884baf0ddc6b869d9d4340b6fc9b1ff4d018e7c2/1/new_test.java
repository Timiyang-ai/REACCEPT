    @Test
    public void testgetLongValue() {
        final Map<String, Long> in = new HashMap<>();
        in.put("key", 2L);

        assertEquals(2.0, MapUtils.getLongValue(in,"key", 0L), 0);
        assertEquals(2.0, MapUtils.getLongValue(in,"key"), 0);
        assertEquals(1, MapUtils.getLongValue(in,"noKey", 1L), 0);
        assertEquals(1, MapUtils.getLongValue(in,"noKey", (key)->{
            return 1L;
        }), 0);
        assertEquals(0, MapUtils.getLongValue(in,"noKey"), 0);
        assertEquals(2.0, MapUtils.getLong(in,"key", 0L), 0);
        assertEquals(1, MapUtils.getLong(in,"noKey", 1L), 0);
        assertEquals(1, MapUtils.getLong(in,"noKey", (key)->{
            return 1L;
        }), 0);

        final Map<String, String> inStr = new HashMap<>();
        inStr.put("str1", "2");

        assertEquals(MapUtils.getLongValue(inStr,"str1", 0L), 2, 0);
        assertEquals(MapUtils.getLong(inStr, "str1", 1L), 2, 0);

    }