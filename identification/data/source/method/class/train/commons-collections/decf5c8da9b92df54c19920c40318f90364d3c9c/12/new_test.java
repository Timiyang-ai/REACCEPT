    @Test
    public void testgetString() {
        final Map<String, String> in = new HashMap<>();
        in.put("key", "str");

        assertEquals("str", MapUtils.getString(in,"key", "defualt"));
        assertEquals("str", MapUtils.getString(in,"key"));
        assertEquals(null, MapUtils.getString(null,"key"));
        assertEquals("default", MapUtils.getString(in,"noKey", "default"));
        assertEquals("default", MapUtils.getString(in,"noKey", (key)->{
            if ("noKey".equals(key)) {
                return "default";
            } else {
                return "";
            }
        }));
        assertEquals("default", MapUtils.getString(null,"noKey", "default"));

    }