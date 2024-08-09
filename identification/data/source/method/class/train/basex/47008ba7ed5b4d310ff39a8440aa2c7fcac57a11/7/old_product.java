private boolean installJAR(final byte[] content, final String path)
      throws QueryException, IOException {

    final IOContent manifest = new IOContent(new Zip(new IOContent(content)).read(MANIFEST_MF));
    final NewlineInput nli = new NewlineInput(manifest);
    for(String s; (s = nli.readLine()) != null;) {
      // write file to rewritten file path
      final Matcher main = MAIN_CLASS.matcher(s);
      if(main.find()) return write(main.group(1).replace('.', '/') + IO.JARSUFFIX, content);
    }
    throw BXRE_MAIN_X.get(info, path);
  }