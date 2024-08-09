public static boolean isPublishMergeOrNormal(int state) {
    return (state & PUBLISH_MERGE_NORMAL) != 0;
  }