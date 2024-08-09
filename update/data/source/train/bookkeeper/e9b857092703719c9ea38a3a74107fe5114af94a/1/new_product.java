public void writeToRegistrationManager(RegistrationManager rm, ServerConfiguration conf, Version version)
            throws BookieException {
        BookieSocketAddress address = null;
        try {
            address = Bookie.getBookieAddress(conf);
        } catch (UnknownHostException e) {
            throw new UnknownBookieIdException(e);
        }
        byte[] data = toString().getBytes(UTF_8);
        rm.writeCookie(address.toString(), new Versioned<>(data, version));
    }