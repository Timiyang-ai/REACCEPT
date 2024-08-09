@Test
    public void testRebalanceNode() {
        HashMap<ByteArray, byte[]> entrySet = ServerTestUtils.createRandomKeyValuePairs(TEST_STREAM_KEYS_SIZE);
        List<Integer> fetchAndUpdatePartitionsList = Arrays.asList(0, 2);

        // insert it into server-0 store
        int fetchPartitionKeyCount = 0;
        Store<ByteArray, byte[]> store = getStore(0, testStoreName);
        for(Entry<ByteArray, byte[]> entry: entrySet.entrySet()) {
            store.put(entry.getKey(), new Versioned<byte[]>(entry.getValue()));
            if(isKeyPartition(entry.getKey(), 0, testStoreName, fetchAndUpdatePartitionsList)) {
                fetchPartitionKeyCount++;
            }
        }

        List<Integer> rebalancePartitionList = Arrays.asList(1, 3);
        RebalancePartitionsInfo stealInfo = new RebalancePartitionsInfo(1,
                                                                        0,
                                                                        rebalancePartitionList,
                                                                        rebalancePartitionList,
                                                                        rebalancePartitionList,
                                                                        Arrays.asList(testStoreName),
                                                                        0);
        int asyncId = adminClient.rebalanceNode(stealInfo);
        assertNotSame("Got a valid rebalanceAsyncId", -1, asyncId);

        getAdminClient().waitForCompletion(1, asyncId, 120, TimeUnit.SECONDS);

        // assert data is copied correctly
        store = getStore(1, testStoreName);
        for(Entry<ByteArray, byte[]> entry: entrySet.entrySet()) {
            if(isKeyPartition(entry.getKey(), 1, testStoreName, rebalancePartitionList)) {
                assertSame("entry should be present at store", 1, store.get(entry.getKey()).size());
                assertEquals("entry value should match",
                             new String(entry.getValue()),
                             new String(store.get(entry.getKey()).get(0).getValue()));
            }
        }
    }