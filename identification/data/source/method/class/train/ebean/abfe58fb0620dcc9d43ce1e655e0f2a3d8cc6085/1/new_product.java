DbType byName(String name) {
    return nameLookup.get(name.trim().toUpperCase());
  }