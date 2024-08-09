@Test
    public void testRemoveAll()
    {
        list.add(asArray(0, 1, 0, 1, 0));

        assertEquals(0, list.removeAllOccurrences(k2));
        assertEquals(3, list.removeAllOccurrences(k0));
        assertListEquals(list.toArray(), 1, 1);

        assertEquals(2, list.removeAllOccurrences(k1));
        assertTrue(list.isEmpty());

        /*! #if ($TemplateOptions.KTypeGeneric) !*/
        list.clear();
        list.add(newArray(k0, null, k2, null, k0));
        assertEquals(2, list.removeAllOccurrences(null));
        assertEquals(0, list.removeAllOccurrences(null));
        assertListEquals(list.toArray(), 0, 2, 0);
        /*! #end !*/
    }