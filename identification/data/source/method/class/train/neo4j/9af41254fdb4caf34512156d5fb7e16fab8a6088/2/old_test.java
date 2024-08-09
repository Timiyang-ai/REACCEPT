    private <T> int compare( T left, T right )
    {
        try
        {
            int cmp1 = CypherOrderability.compare( left, right );
            int cmp2 = CypherOrderability.compare( right, left );
            if ( sign( cmp1 ) != -sign( cmp2 ) )
            {
                throw new AssertionError( format( "Comparator is not symmetric on %s and %s", left, right ) );
            }
            return cmp1;
        }
        catch ( IncomparableValuesException e )
        {
            throw new AssertionError(
                    format( "Failed to compare %s:%s and %s:%s", left, left.getClass().getName(), right,
                            right.getClass().getName() ), e );
        }
    }