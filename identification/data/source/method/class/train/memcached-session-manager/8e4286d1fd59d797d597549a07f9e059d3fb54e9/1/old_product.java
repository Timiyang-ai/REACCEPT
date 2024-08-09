protected String getNextNodeId( final String nodeId, final Collection<String> nodeIds ) {

        /* create a list of nodeIds to check randomly
         */
        final List<String> otherNodeIds = new ArrayList<String>( nodeIds );
        otherNodeIds.remove( nodeId );

        while ( !otherNodeIds.isEmpty() ) {
            final String nodeIdToCheck = otherNodeIds.get( _random.nextInt( otherNodeIds.size() ) );
            if ( isNodeAvailable( nodeIdToCheck ) ) {
                return nodeIdToCheck;
            }
            otherNodeIds.remove( nodeIdToCheck );
        }

        return null;
    }