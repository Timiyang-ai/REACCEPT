public OmemoFingerprint getFingerprint(OmemoDevice userDevice, OmemoDevice contactsDevice)
            throws CorruptedOmemoKeyException, NoIdentityKeyException {

        T_IdKey identityKey = loadOmemoIdentityKey(userDevice, contactsDevice);
        if (identityKey == null) {
            throw new NoIdentityKeyException(contactsDevice);
        }
        return keyUtil().getFingerprintOfIdentityKey(identityKey);
    }