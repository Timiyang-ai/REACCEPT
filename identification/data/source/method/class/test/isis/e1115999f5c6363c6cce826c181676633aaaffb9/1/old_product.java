public Consent isUsable(final AuthenticationSession session, final ObjectAdapter target) {
        return isUsableResult(session, target).createConsent();
    }