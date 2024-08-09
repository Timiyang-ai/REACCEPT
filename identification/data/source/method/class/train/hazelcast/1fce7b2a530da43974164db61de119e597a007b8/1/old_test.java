    @Test
    public void isSyncForced_whenPartitionSpecific() {
        BackpressureRegulator regulator = newEnabledBackPressureService();

        BackupAwareOperation op = new PartitionSpecificOperation(10);

        for (int iteration = 0; iteration < 10; iteration++) {
            int initialSyncDelay = regulator.syncCountDown();
            int remainingSyncDelay = initialSyncDelay - 1;
            for (int k = 0; k < initialSyncDelay - 1; k++) {
                boolean result = regulator.isSyncForced(op);
                assertFalse("no sync force expected", result);

                int syncDelay = regulator.syncCountDown();
                assertEquals(remainingSyncDelay, syncDelay);
                remainingSyncDelay--;
            }

            boolean result = regulator.isSyncForced(op);
            assertTrue("sync force expected", result);

            int syncDelay = regulator.syncCountDown();
            assertValidSyncDelay(syncDelay);
        }
    }