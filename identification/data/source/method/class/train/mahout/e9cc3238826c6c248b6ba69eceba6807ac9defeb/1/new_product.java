public static Dataset generateDataset(CharSequence descriptor,
                                        boolean regression,
                                        String[] data) throws DescriptorException {
    Attribute[] attrs = DescriptorUtils.parseDescriptor(descriptor);
    
    // used to convert CATEGORICAL attributes to Integer
    @SuppressWarnings("unchecked")
    Set<String>[] valsets = new Set[attrs.length];
    
    int size = 0;
    for (String aData : data) {
      if (aData.isEmpty()) {
        continue;
      }
      
      if (parseString(attrs, valsets, aData, regression)) {
        size++;
      }
    }

    @SuppressWarnings("unchecked")
    List<String>[] values = new List[attrs.length];
    for (int i = 0; i < valsets.length; i++) {
      if (valsets[i] != null) {
        values[i] = Lists.newArrayList(valsets[i]);
      }
    }
    
    return new Dataset(attrs, values, size, regression);
  }