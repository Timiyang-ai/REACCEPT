@Test
    public void add_nonManagedObjectToManagedList() {
        testRealm.beginTransaction();
        CyclicType parent = testRealm.createObject(CyclicType.class);
        RealmList<CyclicType> children = parent.getObjects();
        children.add(new CyclicType());
        testRealm.commitTransaction();
        assertEquals(1, testRealm.where(CyclicType.class).findFirst().getObjects().size());
    }