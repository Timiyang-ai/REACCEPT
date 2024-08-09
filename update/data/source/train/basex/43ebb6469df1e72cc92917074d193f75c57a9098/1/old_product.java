public void serialize(final Value value) throws IOException {
    final boolean s = sep;
    for(final Item it : value) serialize(it, false, false);
    sep = s;
  }