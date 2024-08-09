static public LinkedHashMap<String, RouteParameter> parse(String path) {
        LinkedHashMap<String, RouteParameter> params = null;
        
        // extract any named parameters
        Matcher matcher = Route.PATTERN_FOR_VARIABLE_PARTS_OF_ROUTE.matcher(path);
        while (matcher.find()) {
            RouteParameter param = new RouteParameter(
                matcher.start(0), matcher.group(0), matcher.group(1), matcher.group(3));
            
            // lazily create map (so it's null if no path params exist)
            if (params == null) {
                params = new LinkedHashMap<>();
            }
            
            params.put(param.getName(), param);
        }
        
        return params;
    }