@Test
    public void testRetractObject() throws Exception {
        final ReteooWorkingMemory workingMemory = (ReteooWorkingMemory) this.ruleBase.newStatefulSession();

        // Create a Rete network with ObjectTypeNodes for List, Collection and ArrayList
        final Rete rete = ruleBase.getRete();
        final ObjectTypeNode objectTypeNode = new ObjectTypeNode( 1,
                                                                  this.entryPoint,
                                                                  new ClassObjectType( List.class ),
                                                                  buildContext );
        objectTypeNode.attach(buildContext);
        final MockObjectSink sink1 = new MockObjectSink();
        objectTypeNode.addObjectSink( sink1 );

        // There are no String ObjectTypeNodes, make sure its not propagated
        final String string = "String";
        final DefaultFactHandle h1 = new DefaultFactHandle( 1,
                                                            string );

        rete.assertObject( h1,
                           new PropagationContextImpl( 0,
                                                       PropagationContext.INSERTION,
                                                       null,
                                                       null,
                                                       null ),
                           workingMemory );
        assertLength( 0,
                      sink1.getAsserted() );
        assertLength( 0,
                      sink1.getRetracted() );

        // There is a List ObjectTypeNode, make sure it was propagated
        final List list = new ArrayList();
        final DefaultFactHandle h2 = new DefaultFactHandle( 1,
                                                            list );

        // need  to assert first, to force it to build  up the cache
        rete.assertObject( h2,
                           new PropagationContextImpl( 0,
                                                       PropagationContext.INSERTION,
                                                       null,
                                                       null,
                                                       null ),
                           workingMemory );

        rete.retractObject( h2,
                            new PropagationContextImpl( 0,
                                                        PropagationContext.INSERTION,
                                                        null,
                                                        null,
                                                        null ),
                            workingMemory );

        final List retracted = sink1.getRetracted();
        assertLength( 1,
                      retracted );

        final Object[] results = (Object[]) retracted.get( 0 );
        assertSame( list,
                    ((DefaultFactHandle) results[0]).getObject() );
    }