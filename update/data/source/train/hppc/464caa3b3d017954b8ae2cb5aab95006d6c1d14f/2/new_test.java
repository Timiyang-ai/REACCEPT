@Test
    public void testIndexOf()
    {
        list.add(asArray(0, 1, 2, 1, 0));

        /*! #if ($TemplateOptions.KTypeGeneric) !*/
        list.add((KType) null);
        assertEquals(5, list.indexOf(null));
        /*! #end !*/

        assertEquals(0, list.indexOf(k0));
        assertEquals(-1, list.indexOf(k3));
        assertEquals(2, list.indexOf(k2));
    }