    @Test
    public void testgetByteValue() {
        final Map<String, Byte> in = new HashMap<>();
        final byte val = 100;
        in.put("key", val);

        assertEquals(val, MapUtils.getByteValue(in,"key", val), 0);
        assertEquals(val, MapUtils.getByteValue(in,"key"), 0);
        assertEquals(val, MapUtils.getByteValue(in,"noKey", val), 0);
        assertEquals(val, MapUtils.getByteValue(in,"noKey", (key)->{
            return (byte)100;
        }), 0);
        assertEquals(0, MapUtils.getByteValue(in,"noKey"), 0);
        assertEquals(val, MapUtils.getByte(in,"key", val), 0);
        assertEquals(val, MapUtils.getByte(in,"noKey", val), 0);
        assertEquals(val, MapUtils.getByte(in,"noKey", (key)->{
            return val;
        }), 0);


        final Map<String, String> inStr = new HashMap<>();
        inStr.put("str1", "100");

        assertEquals(MapUtils.getByteValue(inStr,"str1", val), val, 0);
    }