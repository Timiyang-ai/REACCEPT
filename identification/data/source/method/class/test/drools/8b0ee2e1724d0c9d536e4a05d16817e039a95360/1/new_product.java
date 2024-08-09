public void retractObject(final InternalFactHandle handle,
                              final PropagationContext context,
                              final ObjectTypeConf objectTypeConf,
                              final InternalWorkingMemory workingMemory) {
        if ( log.isTraceEnabled() ) {
            log.trace( "Delete {}", handle.toString()  );
        }

        workingMemory.addPropagation(new PropagationEntry.Delete(this, handle, context, objectTypeConf));
    }