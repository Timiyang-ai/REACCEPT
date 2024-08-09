public static Endpoint parse(String authority) {
        requireNonNull(authority, "authority");
        if (authority.startsWith("group:")) {
            return ofGroup(authority.substring(6));
        }

        final HostAndPort parsed = HostAndPort.fromString(authority).withDefaultPort(0);
        return create(parsed.getHost(), parsed.getPort());
    }