public PrincipalCollection getRememberedPrincipals(Map subjectContext) {
        try {
            byte[] bytes = getRememberedSerializedIdentity(subjectContext);
            return convertBytesToPrincipals(bytes, subjectContext);
        } catch (RuntimeException re) {
            return onRememberedPrincipalFailure(re, subjectContext);
        }
    }