@Test
    public void move_upInNonManagedMode() {
        RealmList<Dog> dogs = createNonManagedDogList();
        int oldIndex = TEST_SIZE / 2;
        int newIndex = oldIndex + 1;
        Dog dog = dogs.get(oldIndex);
        dogs.move(oldIndex, newIndex); // This doesn't do anything as oldIndex is now empty so the index's above gets shifted to the left.

        assertEquals(TEST_SIZE, dogs.size());
        assertEquals(oldIndex, dogs.indexOf(dog));
    }