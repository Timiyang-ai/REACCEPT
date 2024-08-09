    @Test
    public void isWatching() {
        assertFalse(wallet.isWatching());
        Wallet watchingWallet = Wallet.fromWatchingKey(UNITTEST,
                wallet.getWatchingKey().dropPrivateBytes().dropParent(), Script.ScriptType.P2PKH);
        assertTrue(watchingWallet.isWatching());
        wallet.encrypt(PASSWORD1);
        assertFalse(wallet.isWatching());
    }