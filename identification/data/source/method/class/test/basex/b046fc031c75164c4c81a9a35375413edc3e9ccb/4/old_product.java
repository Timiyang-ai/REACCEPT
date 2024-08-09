private Value dtdInfo(final QueryContext qc) throws QueryException {
    return process(new Validate() {
      @Override
      void process(final ErrorHandler handler)
          throws IOException, ParserConfigurationException, SAXException, QueryException {

        final Item it = checkItem(exprs[0], qc);
        SerializerOptions sp = null;

        // integrate doctype declaration via serialization parameters
        if(exprs.length > 1) {
          sp = new SerializerOptions();
          IO dtd = checkPath(exprs[1], qc);
          tmp = createTmp(dtd);
          if(tmp != null) dtd = tmp;
          sp.set(SerializerOptions.DOCTYPE_SYSTEM, dtd.url());
        }

        final IO in = read(it, qc, sp);
        final SAXParserFactory sf = SAXParserFactory.newInstance();
        sf.setValidating(true);
        sf.newSAXParser().parse(in.inputSource(), handler);
      }
    });
  }