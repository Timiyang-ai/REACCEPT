public boolean removeWatchedAddress(final LegacyAddress address) {
        return removeWatchedAddresses(ImmutableList.of(address));
    }