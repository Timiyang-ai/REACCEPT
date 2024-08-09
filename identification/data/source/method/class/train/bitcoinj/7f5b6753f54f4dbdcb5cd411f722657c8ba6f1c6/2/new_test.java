    @Test
    public void removeWatchedAddresses() {
        List<Address> addressesForRemoval = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Address watchedAddress = LegacyAddress.fromKey(UNITTEST, new ECKey());
            addressesForRemoval.add(watchedAddress);
            wallet.addWatchedAddress(watchedAddress);
        }

        wallet.removeWatchedAddresses(addressesForRemoval);
        for (Address addr : addressesForRemoval)
            assertFalse(wallet.isAddressWatched(addr));
    }