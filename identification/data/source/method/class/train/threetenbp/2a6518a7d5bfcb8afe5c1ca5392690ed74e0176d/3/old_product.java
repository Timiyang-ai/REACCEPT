public String print(TemporalAccessor temporal) {
        StringBuilder buf = new StringBuilder(32);
        printTo(temporal, buf);
        return buf.toString();
    }