public static int indexOf( LongIterator iterator, long item )
    {
        for ( int i = 0; iterator.hasNext(); i++ )
        {
            if ( item == iterator.next() )
            {
                return i;
            }
        }
        return -1;
    }