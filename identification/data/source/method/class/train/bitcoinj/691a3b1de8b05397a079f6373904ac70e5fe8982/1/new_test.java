    @Test
    public void fromKeys() {
        ECKey key = ECKey.fromPrivate(Utils.HEX.decode("00905b93f990267f4104f316261fc10f9f983551f9ef160854f40102eb71cffdcc"));
        Wallet wallet = Wallet.fromKeys(UNITTEST, Arrays.asList(key));
        assertEquals(1, wallet.getImportedKeys().size());
        assertEquals(key, wallet.getImportedKeys().get(0));
        wallet.upgradeToDeterministic(Script.ScriptType.P2PKH, null);
        String seed = wallet.getKeyChainSeed().toHexString();
        assertEquals("5ca8cd6c01aa004d3c5396c628b78a4a89462f412f460a845b594ac42eceaa264b0e14dcd4fe73d4ed08ce06f4c28facfa85042d26d784ab2798a870bb7af556", seed);
    }