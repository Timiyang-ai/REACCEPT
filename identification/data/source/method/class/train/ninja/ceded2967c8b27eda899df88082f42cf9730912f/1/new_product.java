static public Map<String, RouteParameter> parse(String path) {
        Map<String,RouteParameter> params = new LinkedHashMap<>();
        
        // extract any named parameters
        Matcher matcher = Route.PATTERN_FOR_VARIABLE_PARTS_OF_ROUTE.matcher(path);
        while (matcher.find()) {
            RouteParameter param = new RouteParameter(
                matcher.start(0), matcher.group(0), matcher.group(1), matcher.group(3));
            params.put(param.getName(), param);
        }
        
        return params;
    }