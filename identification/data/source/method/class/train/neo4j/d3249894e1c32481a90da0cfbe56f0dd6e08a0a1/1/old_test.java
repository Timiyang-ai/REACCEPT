    @Test
    public void indexOf()
    {
        // GIVEN
        PrimitiveLongIterable items = () -> PrimitiveLongCollections.iterator( 10, 20, 30 );

        // THEN
        assertEquals( -1, PrimitiveLongCollections.indexOf( items.iterator(), 55 ) );
        assertEquals( 0, PrimitiveLongCollections.indexOf( items.iterator(), 10 ) );
        assertEquals( 1, PrimitiveLongCollections.indexOf( items.iterator(), 20 ) );
        assertEquals( 2, PrimitiveLongCollections.indexOf( items.iterator(), 30 ) );
    }