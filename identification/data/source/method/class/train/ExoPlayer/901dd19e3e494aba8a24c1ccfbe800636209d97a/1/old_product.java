public @Nullable MediaPeriodInfo getNextMediaPeriodInfo(
      long rendererPositionUs, PlaybackInfo playbackInfo) {
    return loading == null
        ? getFirstMediaPeriodInfo(playbackInfo)
        : getFollowingMediaPeriodInfo(
            loading.info, loading.getRendererOffset(), rendererPositionUs);
  }