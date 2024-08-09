@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "equals",
        args = {java.lang.Object.class}
    )
    public void test_equals() throws Exception {
        // comparing TreeMaps with different object types
        Map m1 = new TreeMap();
        Map m2 = new TreeMap();
        m1.put("key1", "val1");
        m1.put("key2", "val2");
        m2.put(new Integer(1), "val1");
        m2.put(new Integer(2), "val2");
        assertFalse("Maps should not be equal 1", m1.equals(m2));
        assertFalse("Maps should not be equal 2", m2.equals(m1));

        // comparing TreeMap with HashMap
        m1 = new TreeMap();
        m2 = new HashMap();
        m1.put("key", "val");
        m2.put(new Object(), "val");
        assertFalse("Maps should not be equal 3", m1.equals(m2));
        assertFalse("Maps should not be equal 4", m2.equals(m1));

        // comparing TreeMaps with not-comparable objects inside
        m1 = new TreeMap();
        m2 = new TreeMap();
        m1.put(new Object(), "val1");
        m2.put(new Object(), "val1");
        assertFalse("Maps should not be equal 5", m1.equals(m2));
        assertFalse("Maps should not be equal 6", m2.equals(m1));
    }