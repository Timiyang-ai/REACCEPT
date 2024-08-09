    @Test
    public void isWatching() {
        group = KeyChainGroup.builder(MAINNET)
                .addChain(DeterministicKeyChain.builder().watch(DeterministicKey.deserializeB58(
                        "xpub69bjfJ91ikC5ghsqsVDHNq2dRGaV2HHVx7Y9LXi27LN9BWWAXPTQr4u8U3wAtap8bLdHdkqPpAcZmhMS5SnrMQC4ccaoBccFhh315P4UYzo",
                        MAINNET)).outputScriptType(Script.ScriptType.P2PKH).build())
                .build();
        final ECKey watchingKey = ECKey.fromPublicOnly(new ECKey());
        group.importKeys(watchingKey);
        assertTrue(group.isWatching());
    }