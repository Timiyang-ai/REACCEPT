public Endpoint withDefaultPort(int defaultPort) {
        ensureSingle();
        validatePort("defaultPort", defaultPort);

        return port != 0 ? this : new Endpoint(host(), defaultPort, weight());
    }