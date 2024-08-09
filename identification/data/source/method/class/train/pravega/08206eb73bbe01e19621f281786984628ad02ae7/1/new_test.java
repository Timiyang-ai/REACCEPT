@Test
    @SuppressWarnings("checkstyle:CyclomaticComplexity")
    public void testDeleteStreamSegment() {
        StreamSegmentContainerMetadata m = new StreamSegmentContainerMetadata(CONTAINER_ID);
        ArrayList<Long> segmentIds = new ArrayList<>();
        for (long i = 0; i < SEGMENT_COUNT; i++) {
            final long segmentId = segmentIds.size();
            segmentIds.add(segmentId);
            m.mapStreamSegmentId(getName(segmentId), segmentId);
            for (long j = 0; j < TRANSACTIONS_PER_SEGMENT_COUNT; j++) {
                final long transactionId = segmentIds.size();
                segmentIds.add(transactionId);
                m.mapStreamSegmentId(getName(transactionId), transactionId, segmentId);
            }
        }

        // By construction (see above, any index i=3n is a parent StreamSegment, and any index i=3n+1 or 3n+2 is a Transaction).
        // Let's delete a few parent StreamSegments and verify their Transactions are also deleted.
        // Then delete only Transactions, and verify those are the only ones to be deleted.
        final int groupSize = TRANSACTIONS_PER_SEGMENT_COUNT + 1;
        ArrayList<Integer> streamSegmentsToDelete = new ArrayList<>();
        ArrayList<Integer> transactionsToDelete = new ArrayList<>();
        for (int i = 0; i < segmentIds.size(); i++) {
            if (i < segmentIds.size() / 2) {
                // In the first half, we only delete the parents (which will force the Transactions to be deleted too).
                if (i % groupSize == 0) {
                    streamSegmentsToDelete.add(i);
                }
            } else {
                // In the second half, we only delete the first Transaction of any segment.
                if (i % groupSize == 1) {
                    transactionsToDelete.add(i);
                }
            }
        }

        // Delete stand-alone StreamSegments (and verify Transactions are also deleted).
        Collection<Long> deletedStreamSegmentIds = new HashSet<>();
        for (int index : streamSegmentsToDelete) {
            long segmentId = segmentIds.get(index);
            String name = m.getStreamSegmentMetadata(segmentId).getName();
            Collection<String> expectedDeletedSegmentNames = new ArrayList<>();
            expectedDeletedSegmentNames.add(name);
            deletedStreamSegmentIds.add(segmentId);
            for (int transIndex = 0; transIndex < TRANSACTIONS_PER_SEGMENT_COUNT; transIndex++) {
                long transactionId = segmentIds.get(index + transIndex + 1);
                deletedStreamSegmentIds.add(transactionId);
                expectedDeletedSegmentNames.add(m.getStreamSegmentMetadata(transactionId).getName());
            }

            Map<Long, String> deletedSegmentNames = m.deleteStreamSegment(name);
            AssertExtensions.assertContainsSameElements("Unexpected StreamSegments were deleted.", expectedDeletedSegmentNames, deletedSegmentNames.values());
        }

        // Delete Transactions.
        for (int index : transactionsToDelete) {
            long transactionId = segmentIds.get(index);
            String name = m.getStreamSegmentMetadata(transactionId).getName();
            Collection<String> expectedDeletedSegmentNames = new ArrayList<>();
            deletedStreamSegmentIds.add(transactionId);
            expectedDeletedSegmentNames.add(name);

            Map<Long, String> deletedSegmentNames = m.deleteStreamSegment(name);
            AssertExtensions.assertContainsSameElements("Unexpected StreamSegments were deleted.", expectedDeletedSegmentNames, deletedSegmentNames.values());
        }

        // Verify deleted segments have not been actually removed from the metadata.
        Collection<Long> metadataSegmentIds = m.getAllStreamSegmentIds();
        AssertExtensions.assertContainsSameElements("Metadata does not contain the expected Segment Ids", segmentIds, metadataSegmentIds);

        // Verify individual StreamSegmentMetadata.
        for (long segmentId : segmentIds) {
            boolean expectDeleted = deletedStreamSegmentIds.contains(segmentId);
            Assert.assertEquals("Unexpected value for isDeleted.", expectDeleted, m.getStreamSegmentMetadata(segmentId).isDeleted());
        }
    }