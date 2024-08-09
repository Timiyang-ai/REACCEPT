@Override
  public boolean equals(Object other) {
    if (!(other instanceof TagContext)) {
      return false;
    }
    HashMultiset<Tag> tags1 = HashMultiset.create(Lists.newArrayList(unsafeGetIterator()));
    HashMultiset<Tag> tags2 =
        HashMultiset.create(Lists.newArrayList(((TagContext) other).unsafeGetIterator()));
    return tags1.equals(tags2);
  }