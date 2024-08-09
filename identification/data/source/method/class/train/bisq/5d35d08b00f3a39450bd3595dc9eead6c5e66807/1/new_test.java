@Test
    public void isMetadataEquals() throws NoSuchAlgorithmException, CryptoException {
        KeyPair ownerKeys = TestUtils.generateKeyPair();
        ProtectedStorageEntry seqNrOne = buildProtectedStorageEntry(ownerKeys, ownerKeys, 1);

        ProtectedStorageEntry seqNrTwo = buildProtectedStorageEntry(ownerKeys, ownerKeys, 2);

        Assert.assertTrue(seqNrOne.matchesRelevantPubKey(seqNrTwo));
    }