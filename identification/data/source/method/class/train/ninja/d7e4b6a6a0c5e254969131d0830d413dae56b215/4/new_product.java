protected static String convertRawUriToRegex(String rawUri) {

        // convert capturing groups in route regex to non-capturing groups
        // this is to avoid count mismatch of path params and groups in uri regex
        Matcher groupMatcher = Pattern.compile("\\(([^?].*)\\)").matcher(rawUri);
        String converted = groupMatcher.replaceAll("\\(?:$1\\)");

        Matcher matcher = PATTERN_FOR_VARIABLE_PARTS_OF_ROUTE.matcher(converted);

        StringBuffer stringBuffer = new StringBuffer();

        while (matcher.find()) {

            // By convention group 3 is the regex if provided by the user.
            // If it is not provided by the user the group 3 is null.
            String namedVariablePartOfRoute = matcher.group(3);
            String namedVariablePartOfORouteReplacedWithRegex;
            
            if (namedVariablePartOfRoute != null) {
                // we convert that into a regex matcher group itself
                namedVariablePartOfORouteReplacedWithRegex 
                    = "(" + Matcher.quoteReplacement(namedVariablePartOfRoute) + ")";
            } else {
                // we convert that into the default namedVariablePartOfRoute regex group
                namedVariablePartOfORouteReplacedWithRegex 
                    = VARIABLE_ROUTES_DEFAULT_REGEX;
            }
            // we replace the current namedVariablePartOfRoute group
            matcher.appendReplacement(stringBuffer, namedVariablePartOfORouteReplacedWithRegex);

        }

        // .. and we append the tail to complete the stringBuffer
        matcher.appendTail(stringBuffer);

        return stringBuffer.toString();
    }