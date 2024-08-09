    @Test
    public void addAndActivateHDChain_freshCurrentAddress() {
        DeterministicSeed seed = new DeterministicSeed(ENTROPY, "", 0);
        DeterministicKeyChain chain1 = DeterministicKeyChain.builder().seed(seed)
                .accountPath(DeterministicKeyChain.ACCOUNT_ZERO_PATH).outputScriptType(Script.ScriptType.P2PKH).build();
        group = KeyChainGroup.builder(MAINNET).addChain(chain1).build();
        assertEquals("1M5T5k9yKtGWRtWYMjQtGx3K2sshrABzCT", group.currentAddress(KeyPurpose.RECEIVE_FUNDS).toString());

        final DeterministicKeyChain chain2 = DeterministicKeyChain.builder().seed(seed)
                .accountPath(DeterministicKeyChain.ACCOUNT_ONE_PATH).outputScriptType(Script.ScriptType.P2PKH).build();
        group.addAndActivateHDChain(chain2);
        assertEquals("1JLnjJEXcyByAaW6sqSxNvGiiSEWRhdvPb", group.currentAddress(KeyPurpose.RECEIVE_FUNDS).toString());

        final DeterministicKeyChain chain3 = DeterministicKeyChain.builder().seed(seed)
                .accountPath(DeterministicKeyChain.BIP44_ACCOUNT_ZERO_PATH).outputScriptType(Script.ScriptType.P2WPKH)
                .build();
        group.addAndActivateHDChain(chain3);
        assertEquals("bc1q5fa84aghxd6uzk5g2ywkppmzlut5d77vg8cd20",
                group.currentAddress(KeyPurpose.RECEIVE_FUNDS).toString());
    }