    static void dump(String description, URI dburi) {
        System.err.printf(
                "Test %s\n: isOpaque=%s, isAbsolute=%s Scheme=%s,\n SchemeSpecificPart=%s,\n Host=%s,\n Port=%s,\n Path=%s,\n Fragment=%s,\n Query=%s\n",
                description, dburi.isOpaque(), dburi.isAbsolute(), dburi.getScheme(), dburi.getSchemeSpecificPart(),
                dburi.getHost(), dburi.getPort(), dburi.getPath(), dburi.getFragment(), dburi.getQuery());
        String query = dburi.getQuery();
        if (null != query && !"".equals(query)) {
            String[] params = query.split("&");
            Map<String, String> map = new HashMap<>();
            for (String param : params) {
                String[] splits = param.split("=");
                String name = splits[0];
                String value = null;
                if (splits.length > 1) {
                    value = splits[1];
                }
                map.put(name, value);
                System.err.printf("name=%s,value=%s\n", name, value);
            }
        }
        // return map;
    }