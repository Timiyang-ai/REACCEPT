public static PrimitiveIntIterator filter( PrimitiveIntIterator source, final PrimitiveIntPredicate filter )
    {
        return new PrimitiveIntFilteringIterator( source )
        {
            @Override
            public boolean accept( int item )
            {
                return filter.accept( item );
            }
        };
    }