@Test
    public final void testGetNextNodeId_FailoverNode_RegularNodeLeft() {

        final String nodeId1 = "n1";
        final String nodeId2 = "n2";
        final NodeIdService cut = new NodeIdService( createNodeAvailabilityCache(),
                Arrays.asList( nodeId1 ), Arrays.asList( nodeId2 ) );

        final String actual = cut.getAvailableNodeId( nodeId2 );
        assertEquals( nodeId1, actual, "The regular node is not chosen" );
    }