@Test
    public void testAssertObject() throws Exception {
        final AbstractWorkingMemory workingMemory = (AbstractWorkingMemory) this.ruleBase.newStatefulSession();

        // Create a Rete network with ObjectTypeNodes for List, Collection and ArrayList
        final Rete rete = ruleBase.getRete();
        final ObjectTypeNode objectTypeNode = new ObjectTypeNode(1,
                                                                 this.entryPoint,
                                                                 new ClassObjectType(List.class),
                                                                 buildContext);
        objectTypeNode.attach(buildContext);
        final MockObjectSink sink1 = new MockObjectSink();
        objectTypeNode.addObjectSink(sink1);

        // There are no String ObjectTypeNodes, make sure its not propagated

        final String string = "String";
        final DefaultFactHandle h1 = new DefaultFactHandle(1,
                                                           string);

        rete.assertObject(h1,
                          pctxFactory.createPropagationContext(0,
                                                               PropagationContext.INSERTION,
                                                               null,
                                                               null,
                                                               null),
                          workingMemory);

        assertLength(0,
                     sink1.getAsserted());

        // There is a List ObjectTypeNode, make sure it was propagated
        final List list = new ArrayList();
        final DefaultFactHandle h2 = new DefaultFactHandle(1,
                                                           list);

        rete.assertObject(h2,
                          pctxFactory.createPropagationContext(0,
                                                               PropagationContext.INSERTION,
                                                               null,
                                                               null,
                                                               null),
                          workingMemory);

        final List asserted = sink1.getAsserted();
        assertLength(1,
                     asserted);

        final Object[] results = (Object[]) asserted.get(0);
        assertSame(list,
                   ((DefaultFactHandle) results[0]).getObject());
    }