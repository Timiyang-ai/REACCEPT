private boolean installJAR(final byte[] content, final String path)
      throws QueryException, IOException {

    final IOContent mf = new IOContent(new Zip(new IOContent(content)).read(MANIFEST_MF));
    final NewlineInput nli = new NewlineInput(mf);
    for(String s; (s = nli.readLine()) != null;) {
      // write file to rewritten file path
      final Matcher m = MAIN_CLASS.matcher(s);
      if(m.find()) return write(m.group(1).replace('.', '/') + IO.JARSUFFIX, content);
    }
    throw BXRE_MAIN_X.get(info, path);
  }