@Test
    public void createSnapshot_shouldUseTargetTable() {
        int sizeBefore = collection.size();
        OrderedRealmCollectionSnapshot<Dog> snapshot = collection.createSnapshot();
        realm.beginTransaction();
        snapshot.get(0).deleteFromRealm();
        realm.commitTransaction();
        assertEquals(sizeBefore - 1, collection.size());

        assertNotNull(collection.view);
        assertEquals(collection.view.getTargetTable().getName(), snapshot.getTable().getName());
    }