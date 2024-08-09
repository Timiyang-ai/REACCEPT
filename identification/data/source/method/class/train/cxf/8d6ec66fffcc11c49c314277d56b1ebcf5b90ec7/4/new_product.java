void logProtocolHeaders(Level level) {
        if (LOG.isLoggable(level)) {
            for (String header : headers.keySet()) {
                List<String> headerList = headers.get(header);
                for (String value : headerList) {
                    LOG.log(level, header + ": "
                            + (value == null ? "<null>" : value.toString()));
                }
            }
        }
    }