@Test
    public void testAdd()
    {
        list.addv(
            /* intrinsic:ktypecast */ 1, 
            /* intrinsic:ktypecast */ 2);
        assertListEquals(list.toArray(), 1, 2);
    }