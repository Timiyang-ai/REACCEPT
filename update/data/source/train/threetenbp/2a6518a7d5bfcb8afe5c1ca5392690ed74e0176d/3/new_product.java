public String format(TemporalAccessor temporal) {
        StringBuilder buf = new StringBuilder(32);
        formatTo(temporal, buf);
        return buf.toString();
    }