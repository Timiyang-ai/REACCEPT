private static byte[] transform(final IO in, final IO xsl, final HashMap<String, String> params,
      final XsltOptions xopts, final QueryContext qc) throws TransformerException {

    // retrieve new or cached transformer
    final Transformer tr = transformer(xsl.streamSource(), xopts.get(XsltOptions.CACHE));
    // bind parameters
    params.forEach(tr::setParameter);

    // set URI resolver
    final CatalogWrapper cw = CatalogWrapper.get(qc.context.options.get(MainOptions.CATFILE));
    if(cw != null) tr.setURIResolver(cw.getURIResolver());

    // do transformation and return result
    final ArrayOutput ao = new ArrayOutput();
    tr.transform(in.streamSource(), new StreamResult(ao));
    return ao.finish();
  }