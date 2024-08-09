@Override
    public Consent isUsable(final AuthenticationSession session, final ObjectAdapter target, Where where) {
        return isUsableResult(session, target, where).createConsent();
    }