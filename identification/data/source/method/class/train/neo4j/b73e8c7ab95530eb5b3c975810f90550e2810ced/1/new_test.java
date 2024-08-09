    @Test
    public void filter()
    {
        // GIVEN
        PrimitiveLongIterator items = PrimitiveLongCollections.iterator( 1, 2, 3 );

        // WHEN
        PrimitiveLongIterator filtered = PrimitiveLongCollections.filter( items, item -> item != 2 );

        // THEN
        assertItems( filtered, 1, 3 );
    }