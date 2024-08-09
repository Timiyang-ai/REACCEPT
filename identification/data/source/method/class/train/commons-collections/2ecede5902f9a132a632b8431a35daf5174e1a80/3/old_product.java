public static Collection select( Collection inputCollection, Predicate predicate, Collection outputCollection ) {
        if ( inputCollection != null && predicate != null ) {            
            for ( Iterator iter = inputCollection.iterator(); iter.hasNext(); ) {
                Object item = iter.next();
                if ( predicate.evaluate( item ) ) {
                    outputCollection.add( item );
                }
            }
        }
        return outputCollection;
    }