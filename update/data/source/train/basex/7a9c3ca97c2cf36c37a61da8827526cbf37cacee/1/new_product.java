public static void export(final Data data, final String path, final SerializerOptions sopts,
      final Export export) throws IOException {

    final IOFile root = new IOFile(path);
    root.md();

    // XML documents
    final IntList il = data.resources.docs();
    // raw files
    final IOFile bin;
    final StringList desc;
    if(data.inMemory()) {
      bin = null;
      desc = new StringList();
    } else {
      bin = data.meta.binaries();
      desc = bin.descendants();
    }

    if(export != null) {
      export.progPos = 0;
      export.progSize = il.size() + desc.size();
    }

    // XML documents
    final HashSet<String> exported = new HashSet<>();
    final int is = il.size();
    for(int i = 0; i < is; i++) {
      final int pre = il.get(i);
      // create file path
      final IOFile fl = root.resolve(Token.string(data.text(pre, true)));
      if(export != null) {
        export.checkStop();
        export.progFile = fl;
        export.progPos++;
      }
      // create dir if necessary
      fl.parent().md();

      // serialize file
      try(final PrintOutput po = new PrintOutput(unique(exported, fl.path()))) {
        final Serializer ser = Serializer.get(po, sopts);
        ser.serialize(new DBNode(data, pre));
        ser.close();
      }
    }

    // export raw files
    for(final String s : desc) {
      final IOFile fl = new IOFile(root.path(), s);
      if(export != null) {
        export.checkStop();
        export.progFile = fl;
        export.progPos++;
      }
      final String u = unique(exported, fl.path());
      new IOFile(bin, s).copyTo(new IOFile(u));
    }
  }