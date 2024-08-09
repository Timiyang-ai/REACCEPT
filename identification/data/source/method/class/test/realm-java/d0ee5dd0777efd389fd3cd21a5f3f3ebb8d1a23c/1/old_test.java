@Test
    @RunTestInLooperThread
    public void removeAllChangeListeners() {
        final Realm realm = looperThread.getRealm();
        realm.beginTransaction();
        Dog dog = realm.createObject(Dog.class);
        dog.setAge(13);
        realm.commitTransaction();
        dog.addChangeListener(new RealmChangeListener<Dog>() {
            @Override
            public void onChange(Dog object) {
                fail();
            }
        });
        dog.addChangeListener(new RealmObjectChangeListener<Dog>() {
            @Override
            public void onChange(Dog object, ObjectChangeSet changeSet) {
                fail();
            }
        });
        dog.removeAllChangeListeners();

        realm.beginTransaction();
        Dog sameDog = realm.where(Dog.class).equalTo(Dog.FIELD_AGE, 13).findFirst();
        sameDog.setName("Jesper");
        realm.commitTransaction();
        // Try to trigger the listeners.
        realm.sharedRealm.refresh();
        looperThread.testComplete();
    }