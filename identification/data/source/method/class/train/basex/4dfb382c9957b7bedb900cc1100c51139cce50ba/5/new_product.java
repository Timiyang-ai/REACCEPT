public static Serializer get(final OutputStream os, final SerializerOptions opts)
      throws IOException {

    // no parameters given: serialize as XML
    if(opts == null) return get(os);

    // standard types: XHTML, HTML, text
    final String m = opts.check(S_METHOD, METHODS);
    if(M_XHTML.equals(m)) return new XHTMLSerializer(os, opts);
    if(M_HTML.equals(m)) return new HTMLSerializer(os, opts);
    if(M_TEXT.equals(m)) return new TextSerializer(os, opts);

    // serialize as raw data
    if(M_RAW.equals(m)) return new RawSerializer(os, opts);

    // serialize as CSV
    if(M_CSV.equals(m)) return new CsvSerializer(os, opts);

    // serialize as JSON
    if(M_JSON.equals(m)) {
      final JsonOptions jp = new JsonOptions(opts.get(S_JSON));
      return jp.format() == JsonFormat.DEFAULT ? new JsonDefaultSerializer(os, opts) :
        new JsonMLSerializer(os, opts);
    }

    // otherwise, serialize as XML (default)
    return new XMLSerializer(os, opts);
  }