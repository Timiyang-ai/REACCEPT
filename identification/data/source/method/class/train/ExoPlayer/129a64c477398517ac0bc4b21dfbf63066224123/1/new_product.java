public CastTimeline getCastTimeline(RemoteMediaClient remoteMediaClient) {
    int[] itemIds = remoteMediaClient.getMediaQueue().getItemIds();
    if (itemIds.length > 0) {
      // Only remove unused items when there is something in the queue to avoid removing all entries
      // if the remote media client clears the queue temporarily. See [Internal ref: b/128825216].
      removeUnusedItemDataEntries(itemIds);
    }

    // TODO: Reset state when the app instance changes [Internal ref: b/129672468].
    MediaStatus mediaStatus = remoteMediaClient.getMediaStatus();
    if (mediaStatus == null) {
      return CastTimeline.EMPTY_CAST_TIMELINE;
    }

    int currentItemId = mediaStatus.getCurrentItemId();
    long durationUs = CastUtils.getStreamDurationUs(mediaStatus.getMediaInfo());
    itemIdToData.put(
        currentItemId,
        itemIdToData
            .get(currentItemId, CastTimeline.ItemData.EMPTY)
            .copyWithDurationUs(durationUs));

    for (MediaQueueItem item : mediaStatus.getQueueItems()) {
      int itemId = item.getItemId();
      itemIdToData.put(
          itemId,
          itemIdToData
              .get(itemId, CastTimeline.ItemData.EMPTY)
              .copyWithDefaultPositionUs((long) (item.getStartTime() * C.MICROS_PER_SECOND)));
    }

    return new CastTimeline(itemIds, itemIdToData);
  }