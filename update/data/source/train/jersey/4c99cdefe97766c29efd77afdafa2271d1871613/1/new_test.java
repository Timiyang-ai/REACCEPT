@Test
    public void testPut() throws Exception {
        List<ParameterizedHeader> fooValues1 = new ArrayList<>();
        fooValues1.add(new ParameterizedHeader("foo1"));
        fooValues1.add(new ParameterizedHeader("foo2"));

        assertNull(map.get("foo"));

        map.put("foo", fooValues1);

        assertTrue(map.containsKey("foo"));
        assertTrue(map.containsValue(fooValues1));
        assertTrue(map.get("foo") == fooValues1);

        List<ParameterizedHeader> fooValues2 = new ArrayList<>();
        fooValues2.add(new ParameterizedHeader("foo3"));
        fooValues2.add(new ParameterizedHeader("foo4"));

        map.put("foo", fooValues2);

        assertEquals(1, map.size());
        assertTrue(map.containsKey("foo"));
        assertTrue(!map.containsValue(fooValues1));
        assertTrue(map.containsValue(fooValues2));
        assertTrue(map.get("foo") == fooValues2);
    }