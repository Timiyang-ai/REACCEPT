@Override
    public Consent isUsable(
            final ObjectAdapter target,
            final InteractionInitiatedBy interactionInitiatedBy,
            final Where where) {
        return isUsableResult(target, interactionInitiatedBy, where).createConsent();
    }