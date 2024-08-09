    public void test_keySet() {
        AbstractMap map1 = new HashMap(0);
        assertSame("HashMap(0)", map1.keySet(), map1.keySet());

        AbstractMap map2 = new HashMap(10);
        assertSame("HashMap(10)", map2.keySet(), map2.keySet());

        Map map3 = Collections.EMPTY_MAP;
        assertSame("EMPTY_MAP", map3.keySet(), map3.keySet());

        AbstractMap map4 = new IdentityHashMap(1);
        assertSame("IdentityHashMap", map4.keySet(), map4.keySet());

        AbstractMap map5 = new LinkedHashMap(122);
        assertSame("LinkedHashMap", map5.keySet(), map5.keySet());

        AbstractMap map6 = new TreeMap();
        assertSame("TreeMap", map6.keySet(), map6.keySet());

        AbstractMap map7 = new WeakHashMap();
        assertSame("WeakHashMap", map7.keySet(), map7.keySet());
    }