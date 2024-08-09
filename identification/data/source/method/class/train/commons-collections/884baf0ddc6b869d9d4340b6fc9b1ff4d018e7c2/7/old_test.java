    @Test
    public void testgetFloatValue() {
        final Map<String, Float> in = new HashMap<>();
        in.put("key", 2.0f);

        assertEquals(2.0, MapUtils.getFloatValue(in,"key", 0.0f), 0);
        assertEquals(2.0, MapUtils.getFloatValue(in,"key"), 0);
        assertEquals(1.0, MapUtils.getFloatValue(in,"noKey", 1.0f), 0);
        assertEquals(1.0, MapUtils.getFloatValue(in,"noKey", (key)->{
            return 1.0F;
        }), 0);
        assertEquals(0, MapUtils.getFloatValue(in,"noKey"), 0);
        assertEquals(2.0, MapUtils.getFloat(in,"key", 0.0f), 0);
        assertEquals(1.0, MapUtils.getFloat(in,"noKey", 1.0f), 0);
        assertEquals(1.0, MapUtils.getFloat(in,"noKey", (key)->{
            return 1.0F;
        }), 0);

        final Map<String, String> inStr = new HashMap<>();
        final char decimalSeparator = getDecimalSeparator();
        inStr.put("str1", "2" + decimalSeparator + "0");

        assertEquals(MapUtils.getFloatValue(inStr,"str1", 0.0f), 2.0, 0);
    }