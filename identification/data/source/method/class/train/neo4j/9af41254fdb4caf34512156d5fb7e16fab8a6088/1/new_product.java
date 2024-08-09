public static int compare( Object lhs, Object rhs )
    {
        if ( lhs == rhs )
        {
            return 0;
        }
        // null is greater than any other type
        else if ( lhs == null )
        {
            return 1;
        }
        else if ( rhs == null )
        {
            return -1;
        }
        else if ( lhs instanceof AnyValue )
        {
            AnyValue rhsValue = (rhs instanceof AnyValue) ? (AnyValue) rhs : ValueUtils.of( rhs );
            return AnyValues.COMPARATOR.compare( (AnyValue) lhs, rhsValue );
        }
        else if ( rhs instanceof AnyValue )
        {
            AnyValue lhsValue = (lhs instanceof AnyValue) ? (AnyValue) lhs : ValueUtils.of( lhs );
            return AnyValues.COMPARATOR.compare( lhsValue, (AnyValue) rhs );
        }
        // Compare the types
        // TODO: Test coverage for the Orderability CIP
        SuperType leftType = SuperType.ofValue( lhs );
        SuperType rightType = SuperType.ofValue( rhs );

        int typeComparison = SuperType.TYPE_ID_COMPARATOR.compare( leftType, rightType );
        if ( typeComparison != 0 )
        {
            // Types are different an decides the order
            return typeComparison;
        }

        return leftType.comparator.compare( lhs, rhs );
    }