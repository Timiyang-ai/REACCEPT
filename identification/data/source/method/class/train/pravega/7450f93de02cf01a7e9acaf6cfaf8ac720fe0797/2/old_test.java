@Test
    public void testGetCeiling() {
        final int itemCount = 1000;
        final int maxKey = itemCount * 10;

        // Create an index and populate sparsely.
        val index = createIndex();
        val validKeys = populate(index, itemCount, maxKey);
        validKeys.sort(KEY_COMPARATOR);

        val validKeysIterator = validKeys.iterator();
        Integer expectedValue = -1;
        for (int testKey = 0; testKey < maxKey; testKey++) {
            // Since both testKey and validKeysIterator increase with natural ordering, finding the next expected value
            // is a straightforward call to the iterator next() method.
            while (expectedValue != null && testKey > expectedValue) {
                if (validKeysIterator.hasNext()) {
                    expectedValue = validKeysIterator.next();
                } else {
                    expectedValue = null;
                }
            }

            val ceilingEntry = index.getCeiling(testKey);
            Integer actualValue = ceilingEntry != null ? ceilingEntry.key() : null;
            Assert.assertEquals("Unexpected value for getCeiling for key " + testKey, expectedValue, actualValue);
        }
    }