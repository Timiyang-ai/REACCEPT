public static boolean isPublishOrMerge(long state) {
    return (state & PUBLISH_MERGE) != 0;
  }