public Delete.Builder delete(String... columns) {
        return new Delete.Builder(protocolVersion, codecRegistry, columns);
    }