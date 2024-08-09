    @Test
    public void getFingerprint() throws IOException, CorruptedOmemoKeyException {
        assertNull("Method must return null for a non-existent fingerprint.", store.getFingerprint(alice));
        store.storeOmemoIdentityKeyPair(alice, store.generateOmemoIdentityKeyPair());
        OmemoFingerprint fingerprint = store.getFingerprint(alice);
        assertNotNull("fingerprint must not be null", fingerprint);
        assertEquals("Fingerprint must be of length 64", 64, fingerprint.length());

        store.removeOmemoIdentityKeyPair(alice); //clean up
    }