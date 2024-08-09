@Test
    public void add_nonManagedObjectToManagedList() {
        realm.beginTransaction();
        CyclicType parent = realm.createObject(CyclicType.class);
        RealmList<CyclicType> children = parent.getObjects();
        children.add(new CyclicType());
        realm.commitTransaction();
        assertEquals(1, realm.where(CyclicType.class).findFirst().getObjects().size());
    }