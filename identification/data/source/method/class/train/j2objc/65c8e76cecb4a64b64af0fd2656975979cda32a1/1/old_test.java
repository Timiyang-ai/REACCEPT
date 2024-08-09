    public void test_toString_listAsMapValue() throws Exception {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add("a");
        list.add(new ArrayList<String>());
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("x", "l");
        map.put("y", list);
        assertEquals("{\"x\":\"l\",\"y\":[\"a\",[]]}", new JSONObject(map).toString());
    }