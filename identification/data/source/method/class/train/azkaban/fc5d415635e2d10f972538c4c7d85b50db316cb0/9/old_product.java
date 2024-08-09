public static void writePropsNoJarDependency(Map<String, String> properties,
      Writer writer) throws IOException {
    writer.write("{\n");
    int size = properties.size();

    for (Map.Entry<String, String> entry : properties.entrySet()) {
      // tab the space
      writer.write('\t');
      // Write key
      writer.write(quoteAndClean(entry.getKey()));
      writer.write(':');
      writer.write(quoteAndClean(entry.getValue()));

      size -= 1;
      // Add comma only if it's not the last one
      if (size > 0) {
        writer.write(',');
      }
      writer.write('\n');
    }
    writer.write("}");
  }