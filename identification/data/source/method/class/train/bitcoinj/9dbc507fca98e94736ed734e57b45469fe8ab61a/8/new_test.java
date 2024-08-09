    @Test
    public void freshAddress() throws Exception {
        group = createMarriedKeyChainGroup();
        Address a1 = group.freshAddress(KeyChain.KeyPurpose.RECEIVE_FUNDS);
        Address a2 = group.freshAddress(KeyChain.KeyPurpose.RECEIVE_FUNDS);
        assertEquals(ScriptType.P2SH, a1.getOutputScriptType());
        assertNotEquals(a1, a2);
        group.getBloomFilterElementCount();
        assertEquals(((group.getLookaheadSize() + group.getLookaheadThreshold()) * 2)   // * 2 because of internal/external
                + (2 - group.getLookaheadThreshold())  // keys issued
                + group.getActiveKeyChain().getAccountPath().size() + 3  /* master, account, int, ext */, group.numKeys());

        Address a3 = group.currentAddress(KeyChain.KeyPurpose.RECEIVE_FUNDS);
        assertEquals(a2, a3);
    }