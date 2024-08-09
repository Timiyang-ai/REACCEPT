@Test
    public void testDeleteStreamSegment() {
        final UpdateableContainerMetadata m = new MetadataBuilder(CONTAINER_ID).build();
        ArrayList<Long> segmentIds = new ArrayList<>();
        HashSet<Long> deletedStreamSegmentIds = new HashSet<>();
        for (long i = 0; i < SEGMENT_COUNT; i++) {
            final long segmentId = segmentIds.size();
            segmentIds.add(segmentId);
            val sm = m.mapStreamSegmentId(getName(segmentId), segmentId);
            if (i % 3 == 1) {
                // Every 3i+1 Segment is deleted.
                sm.markDeleted();
            } else if (i % 3 == 2) {
                // Every 3i+1 Segment is merged.
                sm.markMerged();
            }
        }

        // Delete segments.
        for (long segmentId : segmentIds) {
            SegmentMetadata existingMetadata = m.getStreamSegmentMetadata(segmentId);
            boolean alreadyMergedOrDeleted = existingMetadata.isMerged() || existingMetadata.isDeleted();
            SegmentMetadata sm = m.deleteStreamSegment(existingMetadata.getName());
            if (alreadyMergedOrDeleted) {
                Assert.assertNull("Expected deletion to not succeed for already deleted or merged Segment.", sm);
                Assert.assertNotEquals("Not expecting isMerged to equal isDeleted.", existingMetadata.isMerged(), existingMetadata.isDeleted());
            } else {
                Assert.assertNotNull("Expected deletion to not succeed for non-deleted and non-merged Segment.", sm);
                Assert.assertEquals("Unexpected SegmentMetadata returned.", segmentId, sm.getId());
            }
            if (existingMetadata.isDeleted()) {
                deletedStreamSegmentIds.add(segmentId);
            }
        }

        // Verify deleted segments have not been actually removed from the metadata.
        Collection<Long> metadataSegmentIds = m.getAllStreamSegmentIds();
        AssertExtensions.assertContainsSameElements("Metadata does not contain the expected Segment Ids", segmentIds, metadataSegmentIds);

        // Verify individual StreamSegmentMetadata.
        for (long segmentId : segmentIds) {
            boolean expectDeleted = deletedStreamSegmentIds.contains(segmentId);
            val sm = m.getStreamSegmentMetadata(segmentId);
            Assert.assertEquals("Unexpected value for isDeleted.", expectDeleted, sm.isDeleted());
            Assert.assertNotEquals("Unexpected value for isMerged.", expectDeleted, sm.isMerged());
        }
    }