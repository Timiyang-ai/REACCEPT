@Test
    @SuppressWarnings("checkstyle:CyclomaticComplexity")
    public void testDeleteStreamSegment() {
        final UpdateableContainerMetadata m = new MetadataBuilder(CONTAINER_ID).build();
        final int alreadyDeletedTransactionFrequency = 11;
        ArrayList<Long> segmentIds = new ArrayList<>();
        HashSet<Long> deletedStreamSegmentIds = new HashSet<>();
        for (long i = 0; i < SEGMENT_COUNT; i++) {
            final long segmentId = segmentIds.size();
            segmentIds.add(segmentId);
            m.mapStreamSegmentId(getName(segmentId), segmentId);
            for (long j = 0; j < TRANSACTIONS_PER_SEGMENT_COUNT; j++) {
                final long transactionId = segmentIds.size();
                segmentIds.add(transactionId);
                val tm = m.mapStreamSegmentId(getName(transactionId), transactionId, segmentId);
                if (segmentIds.size() % alreadyDeletedTransactionFrequency == 0) {
                    // Mark this transaction as already deleted in Storage.
                    tm.markDeleted();
                    deletedStreamSegmentIds.add(transactionId);
                } else if (segmentIds.size() % alreadyDeletedTransactionFrequency == 1) {
                    // Decoy: this is merged, but not in Storage.
                    tm.markMerged();
                }
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
        for (int index : streamSegmentsToDelete) {
            long segmentId = segmentIds.get(index);
            String name = m.getStreamSegmentMetadata(segmentId).getName();
            Collection<String> expectedDeletedSegmentNames = new ArrayList<>();
            expectedDeletedSegmentNames.add(name);
            deletedStreamSegmentIds.add(segmentId);
            for (int transIndex = 0; transIndex < TRANSACTIONS_PER_SEGMENT_COUNT; transIndex++) {
                long transactionId = segmentIds.get(index + transIndex + 1);
                if (deletedStreamSegmentIds.add(transactionId)) {
                    // We only expect a Transaction to be deleted if it hasn't already been deleted.
                    expectedDeletedSegmentNames.add(m.getStreamSegmentMetadata(transactionId).getName());
                }
            }

            Collection<String> deletedSegmentNames = extract(m.deleteStreamSegment(name), SegmentMetadata::getName);
            AssertExtensions.assertContainsSameElements("Unexpected StreamSegments were deleted.", expectedDeletedSegmentNames, deletedSegmentNames);
        }

        // Delete Transactions.
        for (int index : transactionsToDelete) {
            long transactionId = segmentIds.get(index);
            String name = m.getStreamSegmentMetadata(transactionId).getName();
            Collection<String> expectedDeletedSegmentNames = new ArrayList<>();
            deletedStreamSegmentIds.add(transactionId);
            expectedDeletedSegmentNames.add(name);

            Collection<String> deletedSegmentNames = extract(m.deleteStreamSegment(name), SegmentMetadata::getName);
            AssertExtensions.assertContainsSameElements("Unexpected StreamSegments were deleted.", expectedDeletedSegmentNames, deletedSegmentNames);
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