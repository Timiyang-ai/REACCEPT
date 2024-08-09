@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "Class is abstract. Functionality tested in subclasses for example in java.util.HashMap.",
        method = "clone",
        args = {}
    )
    public void test_clone() {
        class MyMap extends AbstractMap implements Cloneable {
            private Map map = new HashMap();

            public Set entrySet() {
                return map.entrySet();
            }

            public Object put(Object key, Object value) {
                return map.put(key, value);
            }

            public Map getMap() {
                return map;
            }

            public Object clone() {
                try {
                    return super.clone();
                } catch (CloneNotSupportedException e) {
                    return null;
                }
            }
        }
        ;
        MyMap map = new MyMap();
        map.put("one", "1");
        Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
        assertTrue("entry not added", entry.getKey() == "one"
                && entry.getValue() == "1");
        MyMap mapClone = (MyMap) map.clone();
        assertTrue("clone not shallow", map.getMap() == mapClone.getMap());
    }