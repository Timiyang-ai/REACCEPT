void logProtocolHeaders(Level level) {
        for (String header : headers.keySet()) {
            List<?> headerList = headers.get(header);
            for (Object value : headerList) {
                LOG.log(level, header + ": " + value.toString());
            }
        }
    }