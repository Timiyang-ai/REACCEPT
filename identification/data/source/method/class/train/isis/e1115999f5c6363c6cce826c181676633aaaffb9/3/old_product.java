@Override
    public Consent isUsable(final AuthenticationSession session, final ObjectAdapter target, Where where) {
        final boolean isHomePage = containsDoOpFacet(HomePageFacet.class);
        if(isHomePage) {
            return Allow.DEFAULT;
        }
        return isUsableResult(session, target, where).createConsent();
    }