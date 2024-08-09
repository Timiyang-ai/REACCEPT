@Test
    public void testLastIndexOf()
    {
        list.add(asArray(0, 1, 2, 1, 0));

        /*! #if ($TemplateOptions.KTypeGeneric) !*/
        list.add((KType) null);
        assertEquals(5, list.lastIndexOf(null));
        /*! #end !*/

        assertEquals2(4, list.lastIndexOf(k0));
        assertEquals2(-1, list.lastIndexOf(k3));
        assertEquals2(2, list.lastIndexOf(k2));
    }