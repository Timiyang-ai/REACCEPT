@Test
    public void createObject_duplicatedNullPrimaryKeyThrows() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        addPrimaryKeyObjectToTestRealm(realm);

        realm.beginTransaction();
        try {
            realm.createObject(testClazz, null);
            fail("Null value as primary key already exists.");
        } catch (RealmPrimaryKeyConstraintException expected) {
            assertEquals("Value already exists: null", expected.getMessage());
        } finally {
            realm.cancelTransaction();
        }
    }