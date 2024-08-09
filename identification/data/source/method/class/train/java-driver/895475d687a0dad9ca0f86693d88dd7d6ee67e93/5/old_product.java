public Select.Builder select(String... columns) {
        return new Select.Builder(protocolVersion, codecRegistry, Arrays.asList((Object[])columns));
    }