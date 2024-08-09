@Test
    public void testContainsValue() throws Exception {
        List<ParameterizedHeader> values = new ArrayList<>();
        values.add(new ParameterizedHeader("bar"));
        values.add(new ParameterizedHeader("bop"));

        map.put("foo", values);
        assertTrue(map.containsValue(values));

        map.clear();
        assertTrue(!map.containsValue(values));
    }