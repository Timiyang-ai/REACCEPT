public CastTimeline getCastTimeline(MediaStatus status) {
    MediaInfo mediaInfo = status.getMediaInfo();
    List<MediaQueueItem> items = status.getQueueItems();
    removeUnusedDurationEntries(items);

    if (mediaInfo != null) {
      String contentId = mediaInfo.getContentId();
      long durationUs = CastUtils.getStreamDurationUs(mediaInfo);
      contentIdToDurationUsMap.put(contentId, durationUs);
    }
    return new CastTimeline(items, contentIdToDurationUsMap);
  }