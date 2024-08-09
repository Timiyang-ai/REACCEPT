public static CacheControl parse(Headers headers) {
    boolean noCache = false;
    boolean noStore = false;
    int maxAgeSeconds = -1;
    int sMaxAgeSeconds = -1;
    boolean isPublic = false;
    boolean mustRevalidate = false;
    int maxStaleSeconds = -1;
    int minFreshSeconds = -1;
    boolean onlyIfCached = false;
    boolean noTransform = false;

    for (int i = 0; i < headers.size(); i++) {
      if (!headers.name(i).equalsIgnoreCase("Cache-Control")
          && !headers.name(i).equalsIgnoreCase("Pragma")) {
        continue;
      }

      String string = headers.value(i);
      int pos = 0;
      while (pos < string.length()) {
        int tokenStart = pos;
        pos = HeaderParser.skipUntil(string, pos, "=,;");
        String directive = string.substring(tokenStart, pos).trim();
        String parameter;

        if (pos == string.length() || string.charAt(pos) == ',' || string.charAt(pos) == ';') {
          pos++; // consume ',' or ';' (if necessary)
          parameter = null;
        } else {
          pos++; // consume '='
          pos = HeaderParser.skipWhitespace(string, pos);

          // quoted string
          if (pos < string.length() && string.charAt(pos) == '\"') {
            pos++; // consume '"' open quote
            int parameterStart = pos;
            pos = HeaderParser.skipUntil(string, pos, "\"");
            parameter = string.substring(parameterStart, pos);
            pos++; // consume '"' close quote (if necessary)

            // unquoted string
          } else {
            int parameterStart = pos;
            pos = HeaderParser.skipUntil(string, pos, ",;");
            parameter = string.substring(parameterStart, pos).trim();
          }
        }

        if ("no-cache".equalsIgnoreCase(directive)) {
          noCache = true;
        } else if ("no-store".equalsIgnoreCase(directive)) {
          noStore = true;
        } else if ("max-age".equalsIgnoreCase(directive)) {
          maxAgeSeconds = HeaderParser.parseSeconds(parameter, -1);
        } else if ("s-maxage".equalsIgnoreCase(directive)) {
          sMaxAgeSeconds = HeaderParser.parseSeconds(parameter, -1);
        } else if ("public".equalsIgnoreCase(directive)) {
          isPublic = true;
        } else if ("must-revalidate".equalsIgnoreCase(directive)) {
          mustRevalidate = true;
        } else if ("max-stale".equalsIgnoreCase(directive)) {
          maxStaleSeconds = HeaderParser.parseSeconds(parameter, Integer.MAX_VALUE);
        } else if ("min-fresh".equalsIgnoreCase(directive)) {
          minFreshSeconds = HeaderParser.parseSeconds(parameter, -1);
        } else if ("only-if-cached".equalsIgnoreCase(directive)) {
          onlyIfCached = true;
        } else if ("no-transform".equalsIgnoreCase(directive)) {
          noTransform = true;
        }
      }
    }

    return new CacheControl(noCache, noStore, maxAgeSeconds, sMaxAgeSeconds, isPublic,
        mustRevalidate, maxStaleSeconds, minFreshSeconds, onlyIfCached, noTransform);
  }