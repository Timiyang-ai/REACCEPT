@Test
    @RunTestInLooperThread
    public void removeAllChangeListeners() {
        final Realm realm = looperThread.realm;
        realm.beginTransaction();
        Dog dog = realm.createObject(Dog.class);
        dog.setAge(13);
        realm.commitTransaction();
        dog.addChangeListener(new RealmChangeListener<Dog>() {
            @Override
            public void onChange(Dog object) {
                assertTrue(false);
            }
        });
        dog.removeAllChangeListeners();

        realm.beginTransaction();
        Dog sameDog = realm.where(Dog.class).equalTo(Dog.FIELD_AGE, 13).findFirst();
        sameDog.setName("Jesper");
        realm.commitTransaction();
        looperThread.testComplete();
    }