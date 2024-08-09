public void assertObject(final InternalFactHandle factHandle,
                             final PropagationContext context,
                             final InternalWorkingMemory workingMemory) {
        boolean useLeftMemory = true;
        if ( !this.leftTupleMemoryEnabled ) {
            // This is a hack, to not add closed DroolsQuery objects
            Object object = ((InternalFactHandle)context.getFactHandle()).getObject();
            if ( object instanceof DroolsQuery &&  !((DroolsQuery)object).isOpen() ) {
                useLeftMemory = false;
            }
        }
        
        if ( !workingMemory.isSequential() ) {
            this.sink.createAndPropagateAssertLeftTuple( factHandle,
                                                         context,
                                                         workingMemory,
                                                         useLeftMemory );
        } else {
            workingMemory.addLIANodePropagation( new LIANodePropagation( this,
                                                                         factHandle,
                                                                         context ) );
        }
    }