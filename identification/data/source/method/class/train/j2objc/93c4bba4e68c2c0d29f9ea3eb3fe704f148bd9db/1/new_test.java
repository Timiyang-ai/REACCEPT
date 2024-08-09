    public void test_values() {

        IdentityHashMap map = new IdentityHashMap();
        for (int i = 0; i < 10; i++) {
            map.put(new Integer(i), new Integer(i));
        }

        Integer key = new Integer(20);
        Integer value = new Integer(40);
        map.put(key, value);

        Collection vals = map.values();
        boolean result = vals.remove(key);
        assertTrue("removed entries incorrectly", map.size() == 11 && !result);
        assertTrue("removed key incorrectly", map.containsKey(key));
        assertTrue("removed value incorrectly", map.containsValue(value));

        result = vals.remove(value);
        assertTrue("Did not remove entry as expected", map.size() == 10
                && result);
        assertTrue("Did not remove key as expected", !map.containsKey(key));
        assertTrue("Did not remove value as expected", !map
                .containsValue(value));

        // put an equivalent key to a value
        key = new Integer(1);
        value = new Integer(100);
        map.put(key, value);

        result = vals.remove(key);
        assertTrue("TestB. removed entries incorrectly", map.size() == 11
                && !result);
        assertTrue("TestB. removed key incorrectly", map.containsKey(key));
        assertTrue("TestB. removed value incorrectly", map.containsValue(value));

        result = vals.remove(value);
        assertTrue("TestB. Did not remove entry as expected", map.size() == 10
                && result);
        assertTrue("TestB. Did not remove key as expected", !map
                .containsKey(key));
        assertTrue("TestB. Did not remove value as expected", !map
                .containsValue(value));

        vals.clear();
        assertEquals("Did not remove all entries as expected", 0, map.size());
    }