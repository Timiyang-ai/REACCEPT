public void deleteFromRegistrationManager(RegistrationManager rm,
                                              BookieSocketAddress address,
                                              Version version) throws BookieException {
        if (!(version instanceof LongVersion)) {
            throw new IllegalArgumentException("Invalid version type, expected ZkVersion type");
        }

        rm.removeCookie(address.toString(), version);
    }