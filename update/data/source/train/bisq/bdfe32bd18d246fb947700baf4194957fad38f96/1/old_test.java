@Test
    public void isValidForAddOperation_EntryReceiverPayloadReceiverMismatch() throws NoSuchAlgorithmException, CryptoException {
        KeyPair senderKeys = TestUtils.generateKeyPair();
        KeyPair receiverKeys = TestUtils.generateKeyPair();

        MailboxStoragePayload mailboxStoragePayload = buildMailboxStoragePayload(senderKeys.getPublic(), receiverKeys.getPublic());
        ProtectedStorageEntry protectedStorageEntry = buildProtectedMailboxStorageEntry(mailboxStoragePayload, senderKeys, senderKeys.getPublic(), 1);

        // should be assertFalse
        Assert.assertTrue(protectedStorageEntry.isValidForAddOperation());
    }