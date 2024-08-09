    public void test_values() {
        AbstractMap map1 = new HashMap(0);
        assertSame("HashMap(0)", map1.values(), map1.values());

        AbstractMap map2 = new HashMap(10);
        assertSame("HashMap(10)", map2.values(), map2.values());

        Map map3 = Collections.EMPTY_MAP;
        assertSame("EMPTY_MAP", map3.values(), map3.values());

        AbstractMap map4 = new IdentityHashMap(1);
        assertSame("IdentityHashMap", map4.values(), map4.values());

        AbstractMap map5 = new LinkedHashMap(122);
        assertSame("IdentityHashMap", map5.values(), map5.values());

        AbstractMap map6 = new TreeMap();
        assertSame("TreeMap", map6.values(), map6.values());

        AbstractMap map7 = new WeakHashMap();
        assertSame("WeakHashMap", map7.values(), map7.values());
    }