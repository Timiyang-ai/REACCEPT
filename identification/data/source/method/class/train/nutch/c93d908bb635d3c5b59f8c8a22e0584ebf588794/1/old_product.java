public NutchDocument filter(NutchDocument doc, Parse parse, Text url,
      CrawlDatum datum, Inlinks inlinks) throws IndexingException {

    // check if LANGUAGE found, possibly put there by HTMLLanguageParser
    String lang = parse.getData().getParseMeta().get(Metadata.LANGUAGE);

    // check if HTTP-header tels us the language
    if (lang == null) {
      lang = parse.getData().getContentMeta().get(Response.CONTENT_LANGUAGE);
    }

    if (lang == null || lang.length() == 0) {
      lang = "unknown";
    }

    doc.add("lang", lang);

    return doc;
  }