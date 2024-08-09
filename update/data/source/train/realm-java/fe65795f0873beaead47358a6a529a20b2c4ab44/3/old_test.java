@Test
    public void set_nonManagedPrimaryKeyObjectToManagedList() {
        realm.beginTransaction();
        CyclicTypePrimaryKey parent = realm.copyToRealm(new CyclicTypePrimaryKey(1, "Parent"));
        RealmList<CyclicTypePrimaryKey> children = parent.getObjects();
        children.add(new CyclicTypePrimaryKey(2));
        children.add(new CyclicTypePrimaryKey(3, "original"));
        children.add(new CyclicTypePrimaryKey(4));
        children.set(1, new CyclicTypePrimaryKey(3, "updated"));
        realm.commitTransaction();

        RealmList<CyclicTypePrimaryKey> list = realm.where(CyclicTypePrimaryKey.class).findFirst().getObjects();
        assertEquals(3, list.size());
        assertEquals("updated", list.get(1).getName());
    }