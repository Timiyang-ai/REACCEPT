    public void test_wrap() throws Exception {
        assertEquals(JSONObject.NULL, JSONObject.wrap(null));

        JSONArray a = new JSONArray();
        assertEquals(a, JSONObject.wrap(a));

        JSONObject o = new JSONObject();
        assertEquals(o, JSONObject.wrap(o));

        assertEquals(JSONObject.NULL, JSONObject.wrap(JSONObject.NULL));

        assertTrue(JSONObject.wrap(new byte[0]) instanceof JSONArray);
        assertTrue(JSONObject.wrap(new ArrayList<String>()) instanceof JSONArray);
        assertTrue(JSONObject.wrap(new HashMap<String, String>()) instanceof JSONObject);
        assertTrue(JSONObject.wrap(Double.valueOf(0)) instanceof Double);
        assertTrue(JSONObject.wrap("hello") instanceof String);
    }