@SuppressWarnings("unchecked")
    /*! #end !*/
    @Test
    public void testClone()
    {
        this.deque.addLast(key1, key2, key3);

        KTypeArrayDeque<KType> cloned = deque.clone();
        cloned.removeAllOccurrences(key1);

        assertSortedListEquals(deque.toArray(), key1, key2, key3);
        assertSortedListEquals(cloned.toArray(), key2, key3);
    }