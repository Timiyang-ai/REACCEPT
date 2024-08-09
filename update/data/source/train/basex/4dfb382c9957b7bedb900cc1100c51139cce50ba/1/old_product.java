public static Serializer get(final OutputStream os, final SerializerProp props)
      throws IOException {

    // no properties given: serialize as XML
    if(props == null) return get(os);

    // standard types: XHTML, HTML, text
    final String m = props.check(S_METHOD, METHODS);
    if(M_XHTML.equals(m)) return new XHTMLSerializer(os, props);
    if(M_HTML.equals(m)) return new HTMLSerializer(os, props);
    if(M_TEXT.equals(m)) return new TextSerializer(os, props);

    // serialize as raw data
    if(M_RAW.equals(m)) return new RawSerializer(os, props);

    // serialize as CSV
    if(M_CSV.equals(m)) return new CsvSerializer(os, props);

    // serialize as JSON
    if(M_JSON.equals(m)) {
      final JsonProp jp = new JsonProp(props.get(S_JSON));
      return jp.format() == JsonFormat.DEFAULT ? new JsonDefaultSerializer(os, props) :
        new JsonMLSerializer(os, props);
    }

    // otherwise, serialize as XML (default)
    return new XMLSerializer(os, props);
  }