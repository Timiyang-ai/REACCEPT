public Map<String, String> getNestedProperties(String prefix) {
    Map<String, String> subProps = new HashMap<String, String>();
    for(Map.Entry<String, String> entry : options.entrySet()) {
      if(entry.getKey().startsWith(prefix)) {
        subProps.put(entry.getKey().substring(prefix.length()), entry.getKey());
      }
    }

    return subProps;
  }