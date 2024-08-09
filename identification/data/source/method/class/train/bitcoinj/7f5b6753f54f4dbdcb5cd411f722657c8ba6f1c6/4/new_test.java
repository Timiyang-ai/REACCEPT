    @Test
    public void removeWatchedAddress() {
        Address watchedAddress = LegacyAddress.fromKey(UNITTEST, new ECKey());
        wallet.addWatchedAddress(watchedAddress);
        wallet.removeWatchedAddress(watchedAddress);
        assertFalse(wallet.isAddressWatched(watchedAddress));
    }