@Test
    public void testTouchOne() {
        ReadIndexSummary s = new ReadIndexSummary();
        int maxGeneration = ITEMS_PER_GENERATION - 1;

        // Add a few.
        int count = 0;
        for (int generation = 0; generation < GENERATION_COUNT; generation++) {
            s.setCurrentGeneration(generation);
            for (int i = 0; i < ITEMS_PER_GENERATION; i++) {
                s.addOne();
                count++;
            }
        }

        // Touch each item, one by one, in each generation.
        for (int generation = 0; generation < GENERATION_COUNT; generation++) {
            for (int i = 0; i < ITEMS_PER_GENERATION; i++) {
                int returnedGeneration = s.touchOne(generation);
                Assert.assertEquals("Unexpected return value from touchOne().", maxGeneration, returnedGeneration);

                CacheManager.CacheStatus currentStatus = s.toCacheStatus();
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
        Assert.assertEquals("Unexpected newest generation after touching all items.", maxGeneration, currentStatus.getNewestGeneration());
        Assert.assertEquals("Unexpected oldest generation after touching all items.", maxGeneration, currentStatus.getOldestGeneration());

        // Now remove all items
        for (int i = 0; i < count; i++) {
            s.removeOne(maxGeneration);
        }

        currentStatus = s.toCacheStatus();
        Assert.assertEquals("Unexpected newest generation after removing all items.", 0, currentStatus.getNewestGeneration());
        Assert.assertEquals("Unexpected oldest generation after removing all items.", 0, currentStatus.getOldestGeneration());
    }