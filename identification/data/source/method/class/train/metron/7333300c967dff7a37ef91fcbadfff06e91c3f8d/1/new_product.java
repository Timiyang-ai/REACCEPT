@Override
    public List<JSONObject> parse(byte[] rawMessage) {
        String originalMessage = null;
        try {
            originalMessage = new String(rawMessage, getReadCharset()).trim();
            LOG.debug(" raw message. {}", originalMessage);
            if (originalMessage.isEmpty()) {
                LOG.warn("Message is empty.");
                return Arrays.asList(new JSONObject());
            }
        } catch (Exception e) {
            LOG.error("[Metron] Could not read raw message. {} " + originalMessage, e);
            throw new RuntimeException(e.getMessage(), e);
        }

        JSONObject parsedJson = new JSONObject();
        if (messageHeaderPatternsMap.size() > 0) {
            parsedJson.putAll(extractHeaderFields(originalMessage));
        }
        parsedJson.putAll(parse(originalMessage));
        parsedJson.put(Constants.Fields.ORIGINAL.getName(), originalMessage);
        /**
         * Populate the output json with default timestamp.
         */
        parsedJson.put(Constants.Fields.TIMESTAMP.getName(), System.currentTimeMillis());
        applyFieldTransformations(parsedJson);
        return Arrays.asList(parsedJson);
    }