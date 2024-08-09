public static List <NameValuePair> parse (final URI uri, final String encoding) {
        final String query = uri.getRawQuery();
        if (query != null && query.length() > 0) {
            List<NameValuePair> result = new ArrayList<NameValuePair>();
            Scanner scanner = new Scanner(query);
            parse(result, scanner, encoding);
            return result;
        } else {
            return Collections.emptyList();
        }
    }