@Test
    public void move_downInUnmanagedMode() {
        RealmList<Dog> dogs = createUnmanagedDogList();
        Dog dog1 = dogs.get(1);
        Dog dog2 = dogs.get(0);

        dogs.move(1, 0);

        assertEquals(TEST_SIZE, dogs.size());
        assertEquals(0, dogs.indexOf(dog1));
        assertEquals(1, dogs.indexOf(dog2));
    }