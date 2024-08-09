public Endpoint withDefaultPort(int defaultPort) {
        ensureSingle();
        validatePort("defaultPort", defaultPort);

        if (port != 0) {
            return this;
        }

        return new Endpoint(host(), ipAddr(), defaultPort, weight(), hostIpAddrType);
    }