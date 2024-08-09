void logProtocolHeaders(Level level) {
        if (LOG.isLoggable(level)) {
            for (String header : headers.keySet()) {
                List<?> headerList = headers.get(header);
                for (Object value : headerList) {
                    LOG.log(level, header + ": " 
                        + (value == null ? "<null>" : value.toString()));
                }
            }
        }
    }