@Deprecated
    public static PrimitiveLongIterator filter( PrimitiveLongIterator source, final PrimitiveLongPredicate filter )
    {
        return new PrimitiveLongFilteringIterator( source )
        {
            @Override
            public boolean accept( long item )
            {
                return filter.accept( item );
            }
        };
    }