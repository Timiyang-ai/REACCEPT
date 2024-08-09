static void logProtocolHeaders(Logger logger, Level level,
                                   Map<String, List<Object>> headersMap,
                                   boolean logSensitiveHeaders) {
        if (logger.isLoggable(level)) {
            for (Map.Entry<String, List<Object>> entry : headersMap.entrySet()) {
                String key = entry.getKey();
                boolean sensitive = !logSensitiveHeaders && SENSITIVE_HEADERS.contains(key);
                List<Object> headerList = sensitive ? SENSITIVE_HEADER_MARKER : entry.getValue();
                for (Object value : headerList) {
                    logger.log(level, key + ": "
                        + (value == null ? "<null>" : value.toString()));
                }
            }
        }
    }