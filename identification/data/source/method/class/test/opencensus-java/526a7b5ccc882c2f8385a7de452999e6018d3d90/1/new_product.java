@Override
  public boolean equals(@Nullable Object other) {
    if (!(other instanceof TagContext)) {
      return false;
    }
    TagContext otherTags = (TagContext) other;
    Iterator<Tag> iter1 = getIterator();
    Iterator<Tag> iter2 = otherTags.getIterator();
    Multiset<Tag> tags1 =
        iter1 == null
            ? ImmutableMultiset.<Tag>of()
            : HashMultiset.create(Lists.newArrayList(iter1));
    Multiset<Tag> tags2 =
        iter2 == null
            ? ImmutableMultiset.<Tag>of()
            : HashMultiset.create(Lists.newArrayList(iter2));
    return tags1.equals(tags2);
  }