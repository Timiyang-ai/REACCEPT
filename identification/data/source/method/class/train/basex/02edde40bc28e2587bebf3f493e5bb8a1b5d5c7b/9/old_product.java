boolean parse() throws QueryException {
    // parse all annotations
    final EnumSet<HTTPMethod> mth = EnumSet.noneOf(HTTPMethod.class);
    final boolean[] declared = new boolean[function.args.length];
    boolean found = false;
    final int as = function.ann.size();
    for(int a = 0; a < as; a++) {
      final QNm name = function.ann.names[a];
      final Value value = function.ann.values[a];
      final InputInfo info = function.ann.infos[a];
      final byte[] local = name.local();
      final byte[] uri = name.uri();
      final boolean rexq = eq(uri, QueryText.RESTURI);
      if(rexq) {
        if(eq(PATH, local)) {
          // annotation "path"
          if(path != null) error(info, ANN_TWICE, "%", name.string());
          path = new RestXqPath(toString(value, name));
          for(final String s : path) {
            if(s.trim().startsWith("{")) checkVariable(s, AtomType.AAT, declared);
          }
        } else if(eq(ERROR, local)) {
          // annotation "error"
          if(error != null) error(info, ANN_TWICE, "%", name.string());
          error = error(value, name);
        } else if(eq(CONSUMES, local)) {
          // annotation "consumes"
          strings(value, name, consumes);
        } else if(eq(PRODUCES, local)) {
          // annotation "produces"
          strings(value, name, produces);
        } else if(eq(QUERY_PARAM, local)) {
          // annotation "query-param"
          queryParams.add(param(value, name, declared));
        } else if(eq(FORM_PARAM, local)) {
          // annotation "form-param"
          formParams.add(param(value, name, declared));
        } else if(eq(HEADER_PARAM, local)) {
          // annotation "header-param"
          headerParams.add(param(value, name, declared));
        } else if(eq(COOKIE_PARAM, local)) {
          // annotation "cookie-param"
          cookieParams.add(param(value, name, declared));
        } else if(eq(ERROR_PARAM, local)) {
          // annotation "error-param"
          errorParams.add(param(value, name, declared));
        } else {
          // method annotations
          final HTTPMethod m = HTTPMethod.get(string(local));
          if(m == null) error(info, ANN_UNKNOWN, "%", name.string());
          if(!value.isEmpty()) {
            // remember post/put variable
            if(requestBody != null) error(info, ANN_TWICE, "%", name.string());
            if(m != POST && m != PUT) error(info, METHOD_VALUE, m);
            requestBody = checkVariable(toString(value, name), declared);
          }
          if(mth.contains(m)) error(info, ANN_TWICE, "%", name.string());
          mth.add(m);
        }
      } else if(eq(uri, QueryText.OUTPUTURI)) {
        // serialization parameters
        final String key = string(local);
        final String val = toString(value, name);
        if(output.get(key) == null) error(info, UNKNOWN_SER, key);
        output.set(key, val);
      }
      found |= rexq;
    }
    if(!mth.isEmpty()) methods = mth;

    if(found) {
      if(path == null && error == null)
        error(function.info, ANN_MISSING, '%', PATH, '%', ERROR);

      for(int i = 0; i < declared.length; i++) {
        if(declared[i]) continue;
        error(function.info, VAR_UNDEFINED, function.args[i].name.string());
      }
    }
    return found;
  }