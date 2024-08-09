public static Dataset generateDataset(String descriptor, boolean regression, FileSystem fs, Path path) throws DescriptorException,
                                                                                    IOException {
    Attribute[] attrs = DescriptorUtils.parseDescriptor(descriptor);
    
    FSDataInputStream input = fs.open(path);
    Scanner scanner = new Scanner(input);
    
    // used to convert CATEGORICAL attribute to Integer
    List<String>[] values = new List[attrs.length];
    
    int id = 0;
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.isEmpty()) {
        continue;
      }
      
      if (parseString(id, attrs, values, line) != null) {
        id++;
      }
    }
    
    scanner.close();
    
    return new Dataset(attrs, values, id, regression);
  }