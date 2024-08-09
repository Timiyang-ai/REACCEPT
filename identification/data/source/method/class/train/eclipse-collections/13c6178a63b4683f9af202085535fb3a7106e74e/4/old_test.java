    @Test
    public void selectInstancesOf()
    {
        Iterable<Number> numbers1 = Collections.unmodifiableList(new ArrayList<Number>(FastList.newListWith(1, 2.0, 3, 4.0, 5)));
        Iterable<Number> numbers2 = Collections.unmodifiableCollection(new ArrayList<Number>(FastList.newListWith(1, 2.0, 3, 4.0, 5)));

        Verify.assertContainsAll(Iterate.selectInstancesOf(numbers1, Integer.class), 1, 3, 5);
        Verify.assertContainsAll(Iterate.selectInstancesOf(numbers2, Integer.class), 1, 3, 5);
    }