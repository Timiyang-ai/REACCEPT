public static CacheControl parse(Headers headers) {
    boolean noCache = false;
    boolean noStore = false;
    int maxAgeSeconds = -1;
    int sMaxAgeSeconds = -1;
    boolean isPrivate = false;
    boolean isPublic = false;
    boolean mustRevalidate = false;
    int maxStaleSeconds = -1;
    int minFreshSeconds = -1;
    boolean onlyIfCached = false;
    boolean noTransform = false;

    boolean canUseHeaderValue = true;
    String headerValue = null;

    for (int i = 0, size = headers.size(); i < size; i++) {
      String name = headers.name(i);
      String value = headers.value(i);

      if (name.equalsIgnoreCase("Cache-Control")) {
        if (headerValue != null) {
          // Multiple cache-control headers means we can't use the raw value.
          canUseHeaderValue = false;
        } else {
          headerValue = value;
        }
      } else if (name.equalsIgnoreCase("Pragma")) {
        // Might specify additional cache-control params. We invalidate just in case.
        canUseHeaderValue = false;
      } else {
        continue;
      }

      int pos = 0;
      while (pos < value.length()) {
        int tokenStart = pos;
        pos = HeaderParser.skipUntil(value, pos, "=,;");
        String directive = value.substring(tokenStart, pos).trim();
        String parameter;

        if (pos == value.length() || value.charAt(pos) == ',' || value.charAt(pos) == ';') {
          pos++; // consume ',' or ';' (if necessary)
          parameter = null;
        } else {
          pos++; // consume '='
          pos = HeaderParser.skipWhitespace(value, pos);

          // quoted string
          if (pos < value.length() && value.charAt(pos) == '\"') {
            pos++; // consume '"' open quote
            int parameterStart = pos;
            pos = HeaderParser.skipUntil(value, pos, "\"");
            parameter = value.substring(parameterStart, pos);
            pos++; // consume '"' close quote (if necessary)

            // unquoted string
          } else {
            int parameterStart = pos;
            pos = HeaderParser.skipUntil(value, pos, ",;");
            parameter = value.substring(parameterStart, pos).trim();
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
        } else if ("private".equalsIgnoreCase(directive)) {
          isPrivate = true;
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

    if (!canUseHeaderValue) {
      headerValue = null;
    }
    return new CacheControl(noCache, noStore, maxAgeSeconds, sMaxAgeSeconds, isPrivate, isPublic,
        mustRevalidate, maxStaleSeconds, minFreshSeconds, onlyIfCached, noTransform, headerValue);
  }