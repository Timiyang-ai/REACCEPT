public OmemoFingerprint getFingerprint(OmemoDevice userDevice)
            throws CorruptedOmemoKeyException {

        T_IdKeyPair keyPair = loadOmemoIdentityKeyPair(userDevice);
        if (keyPair == null) {
            return null;
        }

        return keyUtil().getFingerprintOfIdentityKey(keyUtil().identityKeyFromPair(keyPair));
    }