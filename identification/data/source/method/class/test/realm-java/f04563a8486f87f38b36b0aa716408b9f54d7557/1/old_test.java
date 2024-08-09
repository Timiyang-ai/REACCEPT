@Test
    public void move_downInUnmanagedMode() {
        RealmList<Dog> dogs = createUnmanagedDogList();
        Dog dog1 = dogs.get(1);
        dogs.move(1, 0);

        assertEquals(0, dogs.indexOf(dog1));
    }