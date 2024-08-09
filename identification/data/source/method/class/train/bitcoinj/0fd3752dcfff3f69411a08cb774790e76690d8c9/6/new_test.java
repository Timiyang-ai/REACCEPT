    @Test
    public void getWatchedAddresses() throws Exception {
        Address watchedAddress = LegacyAddress.fromKey(UNITTEST, new ECKey());
        wallet.addWatchedAddress(watchedAddress);
        List<Address> watchedAddresses = wallet.getWatchedAddresses();
        assertEquals(1, watchedAddresses.size());
        assertEquals(watchedAddress, watchedAddresses.get(0));
    }