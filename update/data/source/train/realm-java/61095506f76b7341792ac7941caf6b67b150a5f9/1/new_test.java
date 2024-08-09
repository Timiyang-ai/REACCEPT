@Test
    public void deleteFromRealm_removedFromResults() {
        realm.beginTransaction();
        realm.delete(Dog.class);
        Dog dogToAdd = realm.createObject(Dog.class);
        dogToAdd.setName("Rex");
        realm.commitTransaction();

        assertEquals(1, realm.allObjects(Dog.class).size());

        Dog dogToRemove = realm.where(Dog.class).findFirst();
        assertNotNull(dogToRemove);
        realm.beginTransaction();
        dogToRemove.deleteFromRealm();
        realm.commitTransaction();

        assertEquals(0, realm.allObjects(Dog.class).size());
        try {
            dogToAdd.getName();
            realm.close();
            fail();
        } catch (IllegalStateException ignored) {
        }
        try {
            dogToRemove.getName();
            realm.close();
            fail();
        } catch (IllegalStateException ignored) {
        }
        realm.close();
    }