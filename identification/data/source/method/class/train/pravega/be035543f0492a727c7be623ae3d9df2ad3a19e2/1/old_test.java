@Test
    public void testCommit() throws Exception {
        // Commit 1 at a time, then 2, then 3, then 4, etc until we have nothing left to commit.
        // At each step verify that the base metadata has been properly updated.
        val referenceMetadata = createBlankMetadata();
        val metadata = createBlankMetadata();
        val updater = new OperationMetadataUpdater(metadata);
        val lastSegmentId = new AtomicLong(-1);
        val lastSegmentTxnId = new AtomicLong(-1);
        long lastCommittedTxnId = -1;
        int txnGroupSize = 1;

        val updateTransactions = new ArrayList<Map.Entry<Long, ContainerMetadata>>();
        while (updateTransactions.size() < TRANSACTION_COUNT) {
            populateUpdateTransaction(updater, referenceMetadata, lastSegmentId, lastSegmentTxnId);

            long utId = updater.sealTransaction();
            if (updateTransactions.size() > 0) {
                long prevUtId = updateTransactions.get(updateTransactions.size() - 1).getKey();
                Assert.assertEquals("UpdateTransaction.Id is not sequential and increasing.", prevUtId + 1, utId);
            }

            updateTransactions.add(new AbstractMap.SimpleImmutableEntry<>(utId, clone(referenceMetadata)));
        }

        ContainerMetadata previousMetadata = null;
        for (val t : updateTransactions) {
            val utId = t.getKey();
            val expectedMetadata = t.getValue();

            // Check to see if it's time to commit.
            if (utId - lastCommittedTxnId >= txnGroupSize) {
                if (previousMetadata != null) {
                    // Verify no changes to the metadata prior to commit.
                    ContainerMetadataUpdateTransactionTests.assertMetadataSame("Before commit " + utId, previousMetadata, metadata);
                }

                // Commit and verify.
                updater.commit(utId);
                ContainerMetadataUpdateTransactionTests.assertMetadataSame("After commit " + utId, expectedMetadata, metadata);
                lastCommittedTxnId = utId;
                txnGroupSize++;
                previousMetadata = expectedMetadata;
            }
        }
    }