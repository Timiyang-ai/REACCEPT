    @Test
    public void test_removeFirstOccurrence() throws Exception {
        DelayedEntry<Data, Object> entry = newEntry(1);
        queue.addLast(entry, false);
        queue.removeFirstOccurrence(entry);

        assertEquals(0, queue.size());
    }