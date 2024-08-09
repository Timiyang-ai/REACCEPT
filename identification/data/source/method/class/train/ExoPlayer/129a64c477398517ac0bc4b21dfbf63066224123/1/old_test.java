@Test
  public void testGetCastTimeline() {
    MediaInfo mediaInfo;
    MediaStatus status =
        mockMediaStatus(
            new int[] {1, 2, 3},
            new String[] {"contentId1", "contentId2", "contentId3"},
            new long[] {DURATION_1_MS, MediaInfo.UNKNOWN_DURATION, MediaInfo.UNKNOWN_DURATION});

    CastTimelineTracker tracker = new CastTimelineTracker();
    mediaInfo = getMediaInfo("contentId1", DURATION_1_MS);
    Mockito.when(status.getMediaInfo()).thenReturn(mediaInfo);
    TimelineAsserts.assertPeriodDurations(
        tracker.getCastTimeline(status), C.msToUs(DURATION_1_MS), C.TIME_UNSET, C.TIME_UNSET);

    mediaInfo = getMediaInfo("contentId3", DURATION_3_MS);
    Mockito.when(status.getMediaInfo()).thenReturn(mediaInfo);
    TimelineAsserts.assertPeriodDurations(
        tracker.getCastTimeline(status),
        C.msToUs(DURATION_1_MS),
        C.TIME_UNSET,
        C.msToUs(DURATION_3_MS));

    mediaInfo = getMediaInfo("contentId2", DURATION_2_MS);
    Mockito.when(status.getMediaInfo()).thenReturn(mediaInfo);
    TimelineAsserts.assertPeriodDurations(
        tracker.getCastTimeline(status),
        C.msToUs(DURATION_1_MS),
        C.msToUs(DURATION_2_MS),
        C.msToUs(DURATION_3_MS));

    MediaStatus newStatus =
        mockMediaStatus(
            new int[] {4, 1, 5, 3},
            new String[] {"contentId4", "contentId1", "contentId5", "contentId3"},
            new long[] {
              MediaInfo.UNKNOWN_DURATION,
              MediaInfo.UNKNOWN_DURATION,
              DURATION_5_MS,
              MediaInfo.UNKNOWN_DURATION
            });
    mediaInfo = getMediaInfo("contentId5", DURATION_5_MS);
    Mockito.when(newStatus.getMediaInfo()).thenReturn(mediaInfo);
    TimelineAsserts.assertPeriodDurations(
        tracker.getCastTimeline(newStatus),
        C.TIME_UNSET,
        C.msToUs(DURATION_1_MS),
        C.msToUs(DURATION_5_MS),
        C.msToUs(DURATION_3_MS));

    mediaInfo = getMediaInfo("contentId3", DURATION_3_MS);
    Mockito.when(newStatus.getMediaInfo()).thenReturn(mediaInfo);
    TimelineAsserts.assertPeriodDurations(
        tracker.getCastTimeline(newStatus),
        C.TIME_UNSET,
        C.msToUs(DURATION_1_MS),
        C.msToUs(DURATION_5_MS),
        C.msToUs(DURATION_3_MS));

    mediaInfo = getMediaInfo("contentId4", DURATION_4_MS);
    Mockito.when(newStatus.getMediaInfo()).thenReturn(mediaInfo);
    TimelineAsserts.assertPeriodDurations(
        tracker.getCastTimeline(newStatus),
        C.msToUs(DURATION_4_MS),
        C.msToUs(DURATION_1_MS),
        C.msToUs(DURATION_5_MS),
        C.msToUs(DURATION_3_MS));
  }