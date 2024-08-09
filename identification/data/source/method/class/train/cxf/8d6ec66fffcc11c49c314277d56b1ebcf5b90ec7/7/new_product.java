static void logProtocolHeaders(Logger logger, Level level, 
                                   Map<String, List<String>> headersMap,
                                   boolean logSensitiveHeaders) {
        if (logger.isLoggable(level)) {
            for (Map.Entry<String, List<String>> entry : headersMap.entrySet()) {
                String key = entry.getKey();
                boolean sensitive = !logSensitiveHeaders && SENSITIVE_HEADERS.contains(key);
                List<String> headerList = sensitive ? SENSITIVE_HEADER_MARKER : entry.getValue();
                for (String value : headerList) {
                    logger.log(level, key + ": " 
                        + (value == null ? "<null>" : value.toString()));
                }
            }
        }
    }