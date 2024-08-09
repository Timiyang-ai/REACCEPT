public void retractObject(final InternalFactHandle handle,
                              final PropagationContext context,
                              final ObjectTypeConf objectTypeConf,
                              final InternalWorkingMemory workingMemory) {
        if ( log.isTraceEnabled() ) {
            log.trace( "Delete {}", handle.toString()  );
        }

        ObjectTypeNode[] cachedNodes = objectTypeConf.getObjectTypeNodes();

        if ( cachedNodes == null ) {
            // it is  possible that there are no ObjectTypeNodes for an  object being retracted
            return;
        }

        for ( int i = 0; i < cachedNodes.length; i++ ) {
            cachedNodes[i].retractObject( handle,
                                          context,
                                          workingMemory );
        }

        if (handle instanceof EventFactHandle) {
            ((EventFactHandle) handle).unscheduleAllJobs(workingMemory);
        }
    }