public void assertObject(final InternalFactHandle factHandle,
                             final PropagationContext context,
                             final InternalWorkingMemory workingMemory) {
        EntryPointId entryPoint = context.getEntryPoint();
        EntryPointNode node = this.entryPoints.get( entryPoint );
        ObjectTypeConf typeConf = ((InternalWorkingMemoryEntryPoint) workingMemory.getWorkingMemoryEntryPoint( entryPoint.getEntryPointId() )).getObjectTypeConfigurationRegistry().getObjectTypeConf( entryPoint,
                                                                                                                                                                                                       factHandle.getObject() );
        node.assertObject( factHandle,
                           context,
                           typeConf,
                           workingMemory );
    }