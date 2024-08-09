ParameterMap extractParameters(final String requestString) {
        // extract parameters...
        final ParameterMap parameters = new ParameterMap();
        StringTokenizer stoken = new StringTokenizer(requestString == null ? "" : requestString, QUERY_STRING_SEPARATOR);
        while (stoken.hasMoreTokens()) {
            String token = stoken.nextToken();            
            if (token.indexOf("=") == -1) 
                continue;
            String paramName = null;
            String value = null;
            paramName = token.substring(0, token.indexOf("="));
            value = token.substring(token.indexOf("=") + 1);
            try {
                value = URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                aalogger.log(Level.WARNING, adminStrings.getLocalString("adapter.param.decode",
                        "Cannot decode parameter {0} = {1}"));
            }

            // indicates a password parameter
            if (paramName.startsWith(ASADMIN_CMD_PREFIX) && (value != null)) {
                try {               
                      value = new String(decoder.decodeBuffer(value));
                } catch (IOException e) {
                    // ignore for now. Not much can be done anyway.
                    // todo: improve this error condition reporting
                }
            }
            parameters.add(paramName, value);
        }

        // Dump parameters...
        if (aalogger.isLoggable(Level.FINER)) {
            for (Map.Entry<String, List<String>> entry : parameters.entrySet()) {
                for (String v : entry.getValue())
                    aalogger.log(Level.FINER, "Key {0} = {1}", new Object[]{entry.getKey(), v});
            }
        }
        return parameters;
    }