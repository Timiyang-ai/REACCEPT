private Value xsdInfo(final QueryContext qc) throws QueryException {
    return process(new Validate() {
      @Override
      void process(final ErrorHandler handler) throws IOException, SAXException, QueryException {
        final IO in = read(toItem(exprs[0], qc), qc, null);
        final SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        final Schema schema;
        if(exprs.length < 2) {
          // assume that schema declaration is included in document
          schema = sf.newSchema();
        } else {
          final Item it = toItem(exprs[1], qc);
          // schema specified as string
          IO scio = read(it, qc, null);
          tmp = createTmp(scio);
          if(tmp != null) scio = tmp;
          schema = sf.newSchema(new URL(scio.url()));
        }

        final Validator v = schema.newValidator();
        v.setErrorHandler(handler);
        v.validate(new StreamSource(in.inputStream()));
      }
    });
  }