public void serialize(final Value value) throws IOException {
    more = false;
    for(final Item it : value) serialize(it);
  }