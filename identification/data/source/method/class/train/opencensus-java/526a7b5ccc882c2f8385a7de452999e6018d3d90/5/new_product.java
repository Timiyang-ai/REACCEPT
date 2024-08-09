@Override
  public boolean equals(@Nullable Object other) {
    if (!(other instanceof TagContext)) {
      return false;
    }
    TagContext otherTags = (TagContext) other;
    Iterator<Tag> iter1 = getIterator();
    Iterator<Tag> iter2 = otherTags.getIterator();
    HashMap<Tag, Integer> tags = new HashMap<Tag, Integer>();
    while (iter1 != null && iter1.hasNext()) {
      Tag tag = iter1.next();
      if (tags.containsKey(tag)) {
        tags.put(tag, tags.get(tag) + 1);
      } else {
        tags.put(tag, 1);
      }
    }
    while (iter2 != null && iter2.hasNext()) {
      Tag tag = iter2.next();
      if (!tags.containsKey(tag)) {
        return false;
      }
      int count = tags.get(tag);
      if (count > 1) {
        tags.put(tag, count - 1);
      } else {
        tags.remove(tag);
      }
    }
    return tags.isEmpty();
  }