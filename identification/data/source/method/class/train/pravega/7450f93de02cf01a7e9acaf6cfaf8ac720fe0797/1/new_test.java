@Test
    public void testRemove() {
        val index = createIndex();
        val keys = populate(index);

        // Remove the items, in order.
        keys.sort(KEY_COMPARATOR);
        val keysToRemove = new LinkedList<Long>(keys);
        int expectedSize = index.size();
        while (keysToRemove.size() > 0) {
            // Remove either the first or the last key - this helps test getFirst/getLast properly.
            long key = expectedSize % 2 == 0 ? keysToRemove.removeLast() : keysToRemove.removeFirst();
            val entry = index.get(key);
            val removedEntry = index.remove(key);
            expectedSize--;

            Assert.assertEquals("Unexpected removed entry for key " + key, entry, removedEntry);
            Assert.assertEquals("Unexpected size after removing key " + key, expectedSize, index.size());
            Assert.assertNull("Entry was not removed for key " + key, index.get(key));

            if (expectedSize == 0) {
                Assert.assertNull("Unexpected value from getFirst() when index is empty.", index.getFirst());
                Assert.assertNull("Unexpected value from getLast() when index is empty.", index.getLast());
            } else {
                Assert.assertEquals("Unexpected value from getFirst() after removing key " + key, (long) keysToRemove.getFirst(), index.getFirst().key());
                Assert.assertEquals("Unexpected value from getLast() after removing key " + key, (long) keysToRemove.getLast(), index.getLast().key());
            }
        }
    }