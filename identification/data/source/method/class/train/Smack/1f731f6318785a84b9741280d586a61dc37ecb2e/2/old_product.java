public OmemoFingerprint getFingerprint(OmemoManager omemoManager) {
        try {
            return keyUtil().getFingerprint(keyUtil().identityKeyFromPair(loadOmemoIdentityKeyPair(omemoManager)));

        } catch (CorruptedOmemoKeyException e) {
            LOGGER.log(Level.WARNING, "getFingerprint failed due to corrupted identityKeyPair: " + e.getMessage());
            return null;
        }
    }