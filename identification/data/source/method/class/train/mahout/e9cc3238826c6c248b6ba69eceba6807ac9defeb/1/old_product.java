public static Dataset generateDataset(CharSequence descriptor,
                                        boolean regression,
                                        String[] data) throws DescriptorException {
    Attribute[] attrs = DescriptorUtils.parseDescriptor(descriptor);
    
    // used to convert CATEGORICAL and LABEL attributes to Integer
    List<String>[] values = new List[attrs.length];
    
    int size = 0;
    for (String aData : data) {
      if (aData.isEmpty()) {
        continue;
      }
      
      if (parseString(attrs, values, aData) != null) {
        size++;
      }
    }
    
    return new Dataset(attrs, values, size, regression);
  }