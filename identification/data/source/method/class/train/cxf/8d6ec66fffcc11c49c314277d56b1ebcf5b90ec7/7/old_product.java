void logProtocolHeaders(Level level) {
        if (LOG.isLoggable(level)) {
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                List<String> headerList = entry.getValue();
                for (String value : headerList) {
                    LOG.log(level, entry.getKey() + ": " 
                        + (value == null ? "<null>" : value.toString()));
                }
            }
        }
    }