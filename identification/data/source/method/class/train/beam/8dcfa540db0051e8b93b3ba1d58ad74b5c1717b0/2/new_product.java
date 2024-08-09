String registerPCollection(PCollection<?> pCollection) throws IOException {
    String existing = pCollectionIds.get(pCollection);
    if (existing != null) {
      return existing;
    }
    String uniqueName = uniqify(pCollection.getName(), pCollectionIds.values());
    pCollectionIds.put(pCollection, uniqueName);
    componentsBuilder.putPcollections(
        uniqueName, PCollectionTranslation.toProto(pCollection, this));
    return uniqueName;
  }