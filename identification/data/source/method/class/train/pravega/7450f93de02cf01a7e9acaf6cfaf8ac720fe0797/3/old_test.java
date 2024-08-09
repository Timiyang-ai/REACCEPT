@Test
    public void testGetFloor() {
        final int itemCount = 1000;
        final int maxKey = itemCount * 10;

        // Create an index and populate sparsely.
        val index = createIndex();
        val validKeys = populate(index, itemCount, maxKey);
        validKeys.sort(KEY_REVERSE_COMPARATOR);

        val validKeysIterator = validKeys.iterator();
        Integer expectedValue = Integer.MAX_VALUE;
        for (int testKey = maxKey; testKey >= 0; testKey--) {
            // Since both testKey and validKeysIterator increase with natural ordering, finding the next expected value
            // is a straightforward call to the iterator next() method.
            while (expectedValue != null && testKey < expectedValue) {
                if (validKeysIterator.hasNext()) {
                    expectedValue = validKeysIterator.next();
                } else {
                    expectedValue = null;
                }
            }

            val ceilingEntry = index.getFloor(testKey);
            Integer actualValue = ceilingEntry != null ? ceilingEntry.key() : null;
            Assert.assertEquals("Unexpected value for getCeiling for key " + testKey, expectedValue, actualValue);
        }
    }