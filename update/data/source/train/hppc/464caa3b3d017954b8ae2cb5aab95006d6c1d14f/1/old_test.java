@Test
    public void testRemoveAll()
    {
        list.add(asArray(0, 1, 0, 1, 0));

        assertEquals(0, list.removeAll(k2));
        assertEquals(3, list.removeAll(k0));
        assertListEquals(list.toArray(), 1, 1);

        assertEquals(2, list.removeAll(k1));
        assertTrue(list.isEmpty());

        /*! #if ($TemplateOptions.KTypeGeneric) !*/
        list.clear();
        list.add(newArray(k0, null, k2, null, k0));
        assertEquals(2, list.removeAll((KType) null));
        assertEquals(0, list.removeAll((KType) null));
        assertListEquals(list.toArray(), 0, 2, 0);
        /*! #end !*/
    }