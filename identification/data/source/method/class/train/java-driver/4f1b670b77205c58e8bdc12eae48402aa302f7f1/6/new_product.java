public Truncate truncate(String table) {
        return new Truncate(protocolVersion, codecRegistry, null, table);
    }