@Test
    public void testTouchOne() {
        ReadIndexSummary s = new ReadIndexSummary();
        long totalSize = 0;
        Random random = RandomFactory.create();
        Queue<Integer> addedSizes = new LinkedList<>();
        int maxGeneration = ITEMS_PER_GENERATION - 1;

        // Add a few.
        for (int generation = 0; generation < GENERATION_COUNT; generation++) {
            s.setCurrentGeneration(generation);
            for (int i = 0; i < ITEMS_PER_GENERATION; i++) {
                int size = random.nextInt(MAX_ITEM_SIZE);
                addedSizes.add(size);
                totalSize += size;
                s.add(size);
            }
        }

        // Touch each item, one by one, in each generation.
        for (int generation = 0; generation < GENERATION_COUNT; generation++) {
            for (int i = 0; i < ITEMS_PER_GENERATION; i++) {
                int returnedGeneration = s.touchOne(generation);
                Assert.assertEquals("Unexpected return value from touchOne().", maxGeneration, returnedGeneration);

                CacheManager.CacheStatus currentStatus = s.toCacheStatus();
                Assert.assertEquals("Not expecting a change in total size.", totalSize, currentStatus.getSize());

                if (i < ITEMS_PER_GENERATION - 1) {
                    Assert.assertEquals(
                            "Not expecting a change in oldest generation when there are elements still in that generation.",
                            generation,
                            currentStatus.getOldestGeneration());
                } else if (generation != maxGeneration) {
                    Assert.assertNotEquals(
                            "Expected a change in oldest generation when all elements in that generation were removed.",
                            generation,
                            currentStatus.getOldestGeneration());
                }

                Assert.assertEquals("Not expecting a change in newest generation.", maxGeneration, currentStatus.getNewestGeneration());
            }
        }

        CacheManager.CacheStatus currentStatus = s.toCacheStatus();
        Assert.assertEquals("Unexpected total size after touching all items.", totalSize, currentStatus.getSize());
        Assert.assertEquals("Unexpected newest generation after touching all items.", maxGeneration, currentStatus.getNewestGeneration());
        Assert.assertEquals("Unexpected oldest generation after touching all items.", maxGeneration, currentStatus.getOldestGeneration());

        // Now remove all items
        for (int size : addedSizes) {
            s.remove(size, maxGeneration);
        }

        currentStatus = s.toCacheStatus();
        Assert.assertEquals("Unexpected total size after removing all items.", 0, currentStatus.getSize());
        Assert.assertEquals("Unexpected newest generation after removing all items.", 0, currentStatus.getNewestGeneration());
        Assert.assertEquals("Unexpected oldest generation after removing all items.", 0, currentStatus.getOldestGeneration());
    }