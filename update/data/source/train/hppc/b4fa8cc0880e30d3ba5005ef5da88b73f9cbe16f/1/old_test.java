@Test
    public void testRemoveAll()
    {
        list.add(newArray(list.buffer, 0, 1, 0, 1, 0));

        assertEquals(0, list.removeAll(/* intrinsic:ktypecast */ 2));
        assertEquals(3, list.removeAll(/* intrinsic:ktypecast */ 0));
        assertListEquals(list.toArray(), 1, 1);

        assertEquals(2, list.removeAll(/* intrinsic:ktypecast */ 1));
        assertTrue(list.isEmpty());

        /* removeIf:primitive */
        list.clear();
        list.add(newArray(list.buffer, 0, null, 2, null, 0));
        assertEquals(2, list.removeAll((Object) null));
        assertEquals(0, list.removeAll((Object) null));
        assertListEquals(list.toArray(), 0, 2, 0);
        /* end:removeIf */
    }