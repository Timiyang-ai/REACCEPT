@Test
    public void testPutAll() throws Exception {
        Map<String, List<ParameterizedHeader>> all = new HashMap<String, List<ParameterizedHeader>>();

        List<ParameterizedHeader> fooValues = new ArrayList<ParameterizedHeader>();
        fooValues.add(new ParameterizedHeader("foo1"));
        fooValues.add(new ParameterizedHeader("foo2"));

        all.put("foo", fooValues);

        List<ParameterizedHeader> barValues = new ArrayList<ParameterizedHeader>();
        barValues.add(new ParameterizedHeader("bar1"));
        barValues.add(new ParameterizedHeader("bar2"));

        all.put("bar", barValues);

        assertTrue(map.isEmpty());

        map.putAll(all);

        assertTrue(!map.isEmpty());
        assertEquals(2, map.size());
        assertTrue(map.containsKey("foo"));
        assertTrue(map.containsKey("bar"));
        assertTrue(map.containsValue(fooValues));
        assertTrue(map.containsValue(barValues));
    }