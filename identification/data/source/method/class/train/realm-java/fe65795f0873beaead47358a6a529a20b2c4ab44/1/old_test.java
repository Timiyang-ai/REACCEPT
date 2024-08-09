@Test
    public void add_nonManagedPrimaryKeyObjectToManagedList() {
        realm.beginTransaction();
        realm.copyToRealm(new CyclicTypePrimaryKey(2, "original"));
        RealmList<CyclicTypePrimaryKey> children = realm.copyToRealm(new CyclicTypePrimaryKey(1)).getObjects();
        children.add(new CyclicTypePrimaryKey(2, "new"));
        realm.commitTransaction();

        assertEquals(1, realm.where(CyclicTypePrimaryKey.class).equalTo("id", 1).findFirst().getObjects().size());
        assertEquals("new", realm.where(CyclicTypePrimaryKey.class).equalTo("id", 2).findFirst().getName());
    }