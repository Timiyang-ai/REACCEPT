@SuppressWarnings("unchecked")
    /*! #end !*/
    @Test
    public void testFrom()
    {
        KTypeArrayList<KType> variable = KTypeArrayList.from(k1, k2, k3);
        assertEquals(3, variable.size());
        assertListEquals(variable.toArray(), 1, 2, 3);
    }