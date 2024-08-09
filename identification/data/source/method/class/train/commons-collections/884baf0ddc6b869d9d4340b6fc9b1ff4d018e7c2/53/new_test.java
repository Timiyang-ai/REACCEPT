    @Test
    public void testgetDoubleValue() {
        final Map<String, Double> in = new HashMap<>();
        in.put("key", 2.0);

        assertEquals(2.0, MapUtils.getDoubleValue(in,"key", 0.0), 0);
        assertEquals(2.0, MapUtils.getDoubleValue(in,"key"), 0);
        assertEquals(1.0, MapUtils.getDoubleValue(in,"noKey", 1.0), 0);
        assertEquals(5.0, MapUtils.getDoubleValue(in,"noKey", (key)->{
            //sometimes the default value need to be calculated,such as System.currentTimeMillis()
            return 5.0D;
        }),0);

        assertEquals(0, MapUtils.getDoubleValue(in,"noKey"), 0);
        assertEquals(2.0, MapUtils.getDouble(in,"key", 0.0), 0);
        assertEquals(1.0, MapUtils.getDouble(in,"noKey", 1.0), 0);
        assertEquals(1.0, MapUtils.getDouble(in,"noKey", (key)->{
            return 1.0;
        }), 0);


        final Map<String, String> inStr = new HashMap<>();
        final char decimalSeparator = getDecimalSeparator();
        inStr.put("str1", "2" + decimalSeparator + "0");

        assertEquals(MapUtils.getDoubleValue(inStr,"str1", 0.0), 2.0, 0);
    }