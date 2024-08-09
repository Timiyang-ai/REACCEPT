    @Test
    public void testgetBooleanValue() {
        final Map<String, Object> in = new HashMap<>();
        in.put("key", true);
        in.put("keyNumberTrue", 1);
        in.put("keyNumberFalse", 0);
        in.put("keyUnmapped", new Object());

        assertFalse(MapUtils.getBooleanValue(null, "keyString", null));
        assertFalse(MapUtils.getBooleanValue(in, null, null));
        assertFalse(MapUtils.getBooleanValue(null, null, null));
        assertTrue(MapUtils.getBooleanValue(in,"key", true));
        assertTrue(MapUtils.getBooleanValue(in,"key"));
        assertTrue(MapUtils.getBooleanValue(in,"noKey", true));
        assertTrue(MapUtils.getBooleanValue(in,"noKey", (key)->{
            return true;
        }));
        assertTrue(!MapUtils.getBooleanValue(in,"noKey"));
        assertTrue(MapUtils.getBoolean(in,"key", true));
        assertTrue(MapUtils.getBoolean(in,"noKey", true));
        assertTrue(MapUtils.getBoolean(in,"noKey", (key)->{
            if (System.currentTimeMillis() > 0) {
                return true;
            }
            return false;
        }));
        assertNull(MapUtils.getBoolean(in, "noKey", (key) -> {
            return null;
        }));
        assertFalse(MapUtils.getBooleanValue(in, "noKey", (key) -> {
            return null;
        }));
        assertEquals(null, MapUtils.getBoolean(null,"noKey"));
        // Values are Numbers
        assertFalse(MapUtils.getBoolean(in, "keyNumberFalse"));
        assertTrue(MapUtils.getBoolean(in, "keyNumberTrue"));
        assertNull(MapUtils.getBoolean(in, "keyString"));
        assertNull(MapUtils.getBoolean(null, "keyString"));
        assertNull(MapUtils.getBoolean(in, null));
        assertNull(MapUtils.getBoolean(null, null));

        final Map<String, String> inStr = new HashMap<>();
        inStr.put("str1", "true");

        assertTrue(MapUtils.getBooleanValue(inStr,"str1", true));
        assertTrue(MapUtils.getBoolean(inStr,"str1", true));
    }