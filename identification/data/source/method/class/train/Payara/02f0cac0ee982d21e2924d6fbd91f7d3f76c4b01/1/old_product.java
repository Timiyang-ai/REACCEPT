ParameterMap extractParameters(final String requestString) {
        // extract parameters...
        final ParameterMap parameters = new ParameterMap();
        StringTokenizer stoken = new StringTokenizer(requestString == null ? "" : requestString, QUERY_STRING_SEPARATOR);
        while (stoken.hasMoreTokens()) {
            String token = stoken.nextToken();
            if (token.indexOf("=") == -1)
                continue;
            String paramName = token.substring(0, token.indexOf("="));
            String value = token.substring(token.indexOf("=") + 1);
            try {
                value = URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                aalogger.log(Level.WARNING, KernelLoggerInfo.cantDecodeParameter,
                        new Object[] {paramName, value});
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