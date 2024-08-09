@Test
    public void testRemoveAll()
    {
        list.add(newArray(list.buffer, 0, 1, 0, 1, 0));

        assertEquals(0, list.removeAllOccurrences(/* intrinsic:ktypecast */ 2));
        assertEquals(3, list.removeAllOccurrences(/* intrinsic:ktypecast */ 0));
        assertListEquals(list.toArray(), 1, 1);

        assertEquals(2, list.removeAllOccurrences(/* intrinsic:ktypecast */ 1));
        assertTrue(list.isEmpty());

        /* removeIf:primitive */
        list.clear();
        list.add(newArray(list.buffer, 0, null, 2, null, 0));
        assertEquals(2, list.removeAllOccurrences((Object) null));
        assertEquals(0, list.removeAllOccurrences((Object) null));
        assertListEquals(list.toArray(), 0, 2, 0);
        /* end:removeIf */
    }