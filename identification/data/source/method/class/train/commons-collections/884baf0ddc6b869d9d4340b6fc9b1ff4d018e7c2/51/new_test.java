    @Test
    public void testgetShortValue() {
        final Map<String, Short> in = new HashMap<>();
        final short val = 10;
        in.put("key", val);

        assertEquals(val, MapUtils.getShortValue(in,"key", val), 0);
        assertEquals(val, MapUtils.getShortValue(in,"key"), 0);
        assertEquals(val, MapUtils.getShortValue(in,"noKey", val), 0);
        assertEquals(val, MapUtils.getShortValue(in,"noKey", (key)->{
            return val;
        }), 0);
        assertEquals(0, MapUtils.getShortValue(in,"noKey"), 0);
        assertEquals(val, MapUtils.getShort(in,"key", val), 0);
        assertEquals(val,MapUtils.getShort(in,"noKey", val), 0);
        assertEquals(val,MapUtils.getShort(in,"noKey", (key)->{
            return val;
        }), 0);

        final Map<String, String> inStr = new HashMap<>();
        inStr.put("str1", "10");

        assertEquals(MapUtils.getShortValue(inStr,"str1", val), val, 0);
    }