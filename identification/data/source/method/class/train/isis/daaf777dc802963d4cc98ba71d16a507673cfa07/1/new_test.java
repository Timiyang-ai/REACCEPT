    @Override
    public VisibilityContext<?> createVisibleInteractionContext(
            final ManagedObject targetObjectAdapter, final InteractionInitiatedBy interactionInitiatedBy,
            Where where) {
        return new PropertyVisibilityContext(targetObjectAdapter, getIdentifier(), interactionInitiatedBy,
                where);
    }