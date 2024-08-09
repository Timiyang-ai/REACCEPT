    @Test
    public void filter()
    {
        // GIVEN
        PrimitiveIntIterator items = PrimitiveIntCollections.iterator( 1, 2, 3 );

        // WHEN
        PrimitiveIntIterator filtered = PrimitiveIntCollections.filter( items, item -> item != 2 );

        // THEN
        assertItems( filtered, 1, 3 );
    }