protected void doRemove(final RuleRemovalContext context,
                            final ReteooBuilder builder,
                            final InternalWorkingMemory[] workingMemories) {
        if ( !context.getKnowledgeBase().getConfiguration().isPhreakEnabled() && context.getCleanupAdapter() != null ) {
            for ( InternalWorkingMemory workingMemory : workingMemories ) {
                CleanupAdapter adapter = context.getCleanupAdapter();
                final ObjectTypeNodeMemory memory = (ObjectTypeNodeMemory) workingMemory.getNodeMemory( this );
                Iterator it = memory.memory.iterator();
                for ( ObjectEntry entry = (ObjectEntry) it.next(); entry != null; entry = (ObjectEntry) it.next() ) {
                    InternalFactHandle handle = (InternalFactHandle) entry.getValue();
                    for ( LeftTuple leftTuple = handle.getFirstLeftTuple(); leftTuple != null; leftTuple = leftTuple.getLeftParentNext() ) {
                        adapter.cleanUp( leftTuple,
                                         workingMemory );
                    }
                }
            }
            context.setCleanupAdapter( null );
        }
    }