    @Test
    public void getToAddress() throws Exception {
        // P2PK
        ECKey toKey = new ECKey();
        Address toAddress = LegacyAddress.fromKey(TESTNET, toKey);
        assertEquals(toAddress, ScriptBuilder.createP2PKOutputScript(toKey).getToAddress(TESTNET, true));
        // pay to pubkey hash
        assertEquals(toAddress, ScriptBuilder.createOutputScript(toAddress).getToAddress(TESTNET, true));
        // pay to script hash
        Script p2shScript = ScriptBuilder.createP2SHOutputScript(new byte[20]);
        Address scriptAddress = LegacyAddress.fromScriptHash(TESTNET,
                ScriptPattern.extractHashFromP2SH(p2shScript));
        assertEquals(scriptAddress, p2shScript.getToAddress(TESTNET, true));
    }