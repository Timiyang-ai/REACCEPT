    @Test
    public void findRedeemScriptFromPubHash() throws Exception {
        group = createMarriedKeyChainGroup();
        Address address = group.freshAddress(KeyChain.KeyPurpose.RECEIVE_FUNDS);
        assertTrue(group.findRedeemDataFromScriptHash(address.getHash()) != null);
        group.getBloomFilterElementCount();
        KeyChainGroup group2 = createMarriedKeyChainGroup();
        group2.freshAddress(KeyChain.KeyPurpose.RECEIVE_FUNDS);
        group2.getBloomFilterElementCount();  // Force lookahead.
        // test address from lookahead zone and lookahead threshold zone
        for (int i = 0; i < group.getLookaheadSize() + group.getLookaheadThreshold(); i++) {
            address = group.freshAddress(KeyChain.KeyPurpose.RECEIVE_FUNDS);
            assertTrue(group2.findRedeemDataFromScriptHash(address.getHash()) != null);
        }
        assertFalse(group2.findRedeemDataFromScriptHash(group.freshAddress(KeyChain.KeyPurpose.RECEIVE_FUNDS).getHash()) != null);
    }