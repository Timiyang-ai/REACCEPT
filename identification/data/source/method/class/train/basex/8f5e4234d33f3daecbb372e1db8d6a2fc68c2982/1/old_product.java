private Value query(final QueryContext qc) throws QueryException {
    final ClientSession cs = session(qc, false);
    final String query = Token.string(checkStr(exprs[1], qc));
    final ValueBuilder vb = new ValueBuilder();
    ClientQuery cq = null;
    try {
      cq = cs.query(query);
      // bind variables and context item
      for(final Map.Entry<String, Value> binding : bindings(2, qc).entrySet()) {
        final String k = binding.getKey();
        final Value v = binding.getValue();
        if(!v.isItem()) throw BXCL_ITEM.get(info, v);
        final Item it = (Item) v;
        final Type t = v.type;
        if(it instanceof FuncItem) throw FIVALUE.get(info, t);

        final Object value = t instanceof NodeType ? v.serialize() : Token.string(it.string(info));
        if(k.isEmpty()) cq.context(value, t.toString());
        else cq.bind(k, value, t.toString());
      }
      // evaluate query
      while(cq.more()) {
        final String result = cq.next();
        vb.add(cq.type().castString(result, qc, sc, info));
      }
      return vb.value();
    } catch(final QueryIOException ex) {
      throw ex.getCause(info);
    } catch(final BaseXException ex) {
      final Matcher m = QUERYPAT.matcher(ex.getMessage());
      if(m.find()) {
        final QueryException exc = get(m.group(1), info, m.group(2));
        throw exc == null ? new QueryException(info, new QNm(m.group(1)), m.group(2)) : exc;
      }
      throw BXCL_QUERY.get(info, ex);
    } catch(final IOException ex) {
      throw BXCL_COMM.get(info, ex);
    } finally {
      if(cq != null) try { cq.close(); } catch(final IOException ignored) { }
    }
  }