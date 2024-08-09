@Test
    public void testEntrySet() throws Exception {
        List<ParameterizedHeader> valuesFoo = new ArrayList<>();
        valuesFoo.add(new ParameterizedHeader("foo1"));
        valuesFoo.add(new ParameterizedHeader("foo2"));

        map.put("foo", valuesFoo);

        List<ParameterizedHeader> valuesBar = new ArrayList<>();
        valuesBar.add(new ParameterizedHeader("bar1"));
        valuesBar.add(new ParameterizedHeader("bar2"));

        map.put("bar", valuesBar);

        Set<Entry<String, List<ParameterizedHeader>>> entrySet = map.entrySet();

        assertEquals(2, entrySet.size());

        // TODO - detailed tests for the HeadersEntries methods
    }