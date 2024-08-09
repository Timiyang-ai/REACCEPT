public void bind(final String name, final Object val) throws QueryException {
    final Expr ex = val instanceof Expr ? (Expr) val :
      JavaFunc.type(val).e(val, null);

    // remove optional $ prefix
    String loc = name.indexOf('$') == 0 ? name.substring(1) : name;
    byte[] uri = EMPTY;

    // check for namespace declaration
    final Matcher m = BIND.matcher(loc);
    if(m.find()) {
      String u = m.group(3);
      if(u == null) u = m.group(5);
      uri = token(u);
      loc = m.group(6);
    }
    final byte[] ln = token(loc);
    if(loc.isEmpty() || !XMLToken.isNCName(ln)) return;

    // bind variable
    final QNm nm = uri.length == 0 ? new QNm(ln, this) : new QNm(ln, uri);
    final Var gl = vars.global().get(nm);
    if(gl == null) {
      // assign new variable
      vars.setGlobal(Var.create(this, null, nm).bind(ex, this));
    } else {
      // reset declaration state and bind new expression
      gl.declared = false;
      gl.bind(gl.type != null ? gl.type.type.e(ex.item(this, null),
          this, null) : ex, this);
    }
  }