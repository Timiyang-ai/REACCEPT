@Test
    public void isValidForAddOperation() throws NoSuchAlgorithmException, CryptoException {
        KeyPair senderKeys = TestUtils.generateKeyPair();
        KeyPair receiverKeys = TestUtils.generateKeyPair();

        MailboxStoragePayload mailboxStoragePayload = buildMailboxStoragePayload(senderKeys.getPublic(), receiverKeys.getPublic());
        ProtectedStorageEntry protectedStorageEntry = buildProtectedMailboxStorageEntry(mailboxStoragePayload, senderKeys, receiverKeys.getPublic());

        Assert.assertTrue(protectedStorageEntry.isValidForAddOperation());
    }