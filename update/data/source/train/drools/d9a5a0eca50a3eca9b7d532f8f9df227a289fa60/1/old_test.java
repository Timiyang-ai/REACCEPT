@Test
    public void testAssertObject() throws Exception {
        when( constraint.isAllowedCachedLeft(any(ContextEntry.class), any(InternalFactHandle.class))).thenReturn(true);
        when( constraint.isAllowedCachedRight(any(LeftTuple.class), any(ContextEntry.class))).thenReturn(true);

        final DefaultFactHandle f0 = (DefaultFactHandle) this.workingMemory
                .insert("test0");

        // assert object, should add one to right memory
        this.node.assertObject(f0, this.context, this.workingMemory);
        assertEquals(0, this.memory.getLeftTupleMemory().size());
        assertEquals(1, this.memory.getRightTupleMemory().size());

        // check new objects/handles still assert
        final DefaultFactHandle f1 = (DefaultFactHandle) this.workingMemory
                .insert("test1");
        this.node.assertObject(f1, this.context, this.workingMemory);
        assertEquals(2, this.memory.getRightTupleMemory().size());

        RightTuple rightTuple = this.memory.getRightTupleMemory().getFirst(
                new LeftTuple(f0, this.node, true), null);

        final InternalFactHandle rf0 = rightTuple.getFactHandle();
        final InternalFactHandle rf1 = ((RightTuple) rightTuple.getNext())
                .getFactHandle();

        assertEquals(f0, rf0);
        assertEquals(f1, rf1);
    }