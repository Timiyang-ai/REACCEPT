@Test
    public void testDoRemove() throws FactException {
        final MockEvalCondition eval = new MockEvalCondition( true );

        final EvalConditionNode parent = new EvalConditionNode( 1,
                                                                new MockTupleSource( 15 ),
                                                                eval,
                                                                buildContext );

        // Create a test node that always returns false 
        final EvalConditionNode node = new EvalConditionNode( 2,
                                                              parent,
                                                              eval,
                                                              buildContext );

        parent.addTupleSink( node );

        final MockLeftTupleSink sink = new MockLeftTupleSink();
        node.addTupleSink( sink );

        // Create the Tuple
        final DefaultFactHandle f0 = new DefaultFactHandle( 0,
                                                            "stilton" );
        // an eval node always has at least a LIAN before it, so, tuples that reach it 
        // always have at least one tuple parent
        final LeftTupleImpl parentTuple = new LeftTupleImpl( f0,
                                                     null,
                                                     true );
        final LeftTuple tuple0 = sink.createLeftTuple( parentTuple,
                                                       sink,
                                                       true );

        // Tuple should pass and propagate 
        node.assertLeftTuple( tuple0,
                              this.context,
                              this.workingMemory );

        // make sure assertions were propagated
        assertEquals( 1,
                      sink.getAsserted().size() );
    }