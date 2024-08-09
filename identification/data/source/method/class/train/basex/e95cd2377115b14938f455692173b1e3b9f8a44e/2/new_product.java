public static void export(final Prop prop, final Data data,
      final String target) throws IOException {

    final SerializerProp sp = new SerializerProp(prop.get(Prop.EXPORTER));
    final IOFile root = new IOFile(target);
    root.md();

    final HashSet<String> exported = new HashSet<String>();
    for(final int pre : data.doc()) {
      // create file path
      final IO file = root.merge(Token.string(data.text(pre, true)));
      // create dir if necessary
      final IOFile dir = new IOFile(file.dir());
      if(!dir.exists()) dir.md();

      // attach counter to duplicate file names
      final String fp = file.path();
      String path = fp;
      int c = 1;
      while(exported.contains(path)) {
        path = fp.indexOf('.') == -1 ? fp + '(' + ++c + ')' :
             fp.replaceAll("(.*)\\.(.*)", "$1(" + ++c + ").$2");
      }
      exported.add(fp);

      // serialize file
      final PrintOutput po = new PrintOutput(path);
      final XMLSerializer xml = new XMLSerializer(po, sp);
      xml.node(data, pre);
      xml.close();
      po.close();
    }
  }