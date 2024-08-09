    @Test
    public void testgetIntValue() {
        final Map<String, Integer> in = new HashMap<>();
        in.put("key", 2);

        assertEquals(2, MapUtils.getIntValue(in,"key", 0), 0);
        assertEquals(2, MapUtils.getIntValue(in,"key"), 0);
        assertEquals(0, MapUtils.getIntValue(in,"noKey", 0), 0);
        assertEquals(0, MapUtils.getIntValue(in,"noKey", (key)->{
            return 0;
        }), 0);
        assertEquals(0, MapUtils.getIntValue(in,"noKey"), 0);
        assertEquals(2, MapUtils.getInteger(in,"key", 0), 0);
        assertEquals(0, MapUtils.getInteger(in,"noKey", 0), 0);
        assertEquals(0, MapUtils.getInteger(in,"noKey", (key)->{
            return 0;
        }), 0);

        final Map<String, String> inStr = new HashMap<>();
        inStr.put("str1", "2");

        assertEquals(MapUtils.getIntValue(inStr,"str1", 0), 2, 0);
    }