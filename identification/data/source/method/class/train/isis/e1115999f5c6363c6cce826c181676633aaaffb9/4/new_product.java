@Override
    public Consent isUsable(
            final ManagedObject target,
            final InteractionInitiatedBy interactionInitiatedBy,
            final Where where) {
        return isUsableResult(target, interactionInitiatedBy, where).createConsent();
    }