public boolean removeWatchedAddress(final Address address) {
        return removeWatchedAddresses(ImmutableList.of(address));
    }