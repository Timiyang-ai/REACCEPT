@Test
    public void testCleanup() {
        // Expire each Segment at a different stage.
        final StreamSegmentContainerMetadata m = new StreamSegmentContainerMetadata(CONTAINER_ID);

        // Create a number of segments, out of which every 4th one has a transaction (25%).
        // Each segment has a 'LastUsed' set in incremental order.
        final ArrayList<Long> segments = new ArrayList<>();
        final HashMap<Long, Long> transactions = new HashMap<>();
        populateSegmentsForEviction(segments, transactions, m);

        long maxLastUsed = 1;
        for (Long segmentId : segments) {
            UpdateableSegmentMetadata segmentMetadata = m.getStreamSegmentMetadata(segmentId);
            segmentMetadata.setLastUsed(maxLastUsed++);
        }

        final Map<Long, UpdateableSegmentMetadata> segmentMetadatas = segments
                .stream().collect(Collectors.toMap(id -> id, m::getStreamSegmentMetadata));

        // Truncate everything and expire all segments.
        m.removeTruncationMarkers(maxLastUsed);
        Collection<SegmentMetadata> evictionCandidates = m.getEvictionCandidates(maxLastUsed);

        // Pick a Transaction and a non-related Segment and touch them. Then verify all but the three involved Segments are evicted.
        final long touchedSeqNo = maxLastUsed + 10;
        final ArrayList<Long> touchedSegments = new ArrayList<>();
        val iterator = transactions.entrySet().iterator();
        touchedSegments.add(iterator.next().getKey());
        val second = iterator.next();
        touchedSegments.add(second.getValue());
        segmentMetadatas.get(touchedSegments.get(0)).setLastUsed(touchedSeqNo);
        segmentMetadatas.get(touchedSegments.get(1)).setLastUsed(touchedSeqNo);
        touchedSegments.add(second.getKey()); // We add the Transaction's parent, but do not touch it.

        // Attempt to cleanup the eviction candidates, and even throw in a new truncation (to verify that alone won't trigger the cleanup).
        m.removeTruncationMarkers(touchedSeqNo + 1);
        Collection<SegmentMetadata> evictedSegments = m.cleanup(evictionCandidates, maxLastUsed);

        // Check that we evicted all eligible segments, and kept the 'touched' ones still.
        Assert.assertEquals("Unexpected number of segments were evicted (first-cleanup).",
                segments.size() - touchedSegments.size(), evictedSegments.size());
        for (long segmentId : touchedSegments) {
            SegmentMetadata sm = m.getStreamSegmentMetadata(segmentId);
            Assert.assertNotNull("Candidate segment that was touched was still evicted (lookup by id)", sm);
            Assert.assertEquals("Candidate segment that was touched was still evicted (lookup by name).",
                    segmentId,
                    m.getStreamSegmentId(sm.getName(), false));
        }

        // Now expire the remaining segments and verify.
        evictionCandidates = m.getEvictionCandidates(touchedSeqNo + 1);
        evictedSegments = m.cleanup(evictionCandidates, touchedSeqNo + 1);

        Assert.assertEquals("Unexpected number of segments were evicted (second-cleanup).",
                touchedSegments.size(), evictedSegments.size());
        for (long segmentId : segments) {
            Assert.assertNull("Candidate segment was not evicted (lookup by id)", m.getStreamSegmentMetadata(segmentId));
        }
    }