public PGPPublicKeyRingCollection get(long keyId)
      throws PGPException, IOException {
    if (reader == null) {
      load();
    }
    if (notes == null) {
      return empty();
    }
    Note note = notes.getNote(keyObjectId(keyId));
    if (note == null) {
      return empty();
    }

    List<PGPPublicKeyRing> keys = new ArrayList<>();
    try (InputStream in = reader.open(note.getData(), OBJ_BLOB).openStream()) {
      while (true) {
        @SuppressWarnings("unchecked")
        Iterator<Object> it =
            new BcPGPObjectFactory(new ArmoredInputStream(in)).iterator();
        if (!it.hasNext()) {
          break;
        }
        Object obj = it.next();
        if (obj instanceof PGPPublicKeyRing) {
          keys.add((PGPPublicKeyRing) obj);
        }
        checkState(!it.hasNext(),
            "expected one PGP object per ArmoredInputStream");
      }
      return new PGPPublicKeyRingCollection(keys);
    }
  }