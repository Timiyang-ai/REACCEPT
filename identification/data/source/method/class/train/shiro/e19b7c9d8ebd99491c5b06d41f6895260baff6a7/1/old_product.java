public PrincipalCollection getRememberedPrincipals() {
        try {

            PrincipalCollection principals = null;
            byte[] bytes = getSerializedRememberedIdentity();
            if (bytes != null) {
                if (getCipher() != null) {
                    bytes = decrypt(bytes);
                }
                try {
                    principals = deserialize(bytes);
                } catch (SerializationException e) {
                    if (log.isWarnEnabled()) {
                        log.warn("Unable to deserialize stored identity byte array.  Remembered identity " +
                                "cannot be reconstituted!  This is a non fatal exception as RememberMe identity services " +
                                "are not considered critical and execution can continue as normal, but please " +
                                "investigate and resolve to prevent seeing this message again.", e);
                    }
                }
            }
            return principals;

        } catch (Exception e) {
            return onRememberedPrincipalFailure(e);
        }
    }