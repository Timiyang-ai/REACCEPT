@Test
    public void testBuild() {
        Map<Integer, Integer> data = new HashMap<>();
        for (int i = 0; i < 100; i++)
            data.put(i, i);

        LocalDatasetBuilder<Integer, Integer> builder = new LocalDatasetBuilder<>(data, 10);

        LocalDataset<Serializable, TestPartitionData> dataset = buildDataset(builder);

        assertEquals(10, dataset.getCtx().size());
        assertEquals(10, dataset.getData().size());

        AtomicLong cnt = new AtomicLong();

        dataset.compute((partData, env) -> {
           cnt.incrementAndGet();

           int[] arr = partData.data;

           assertEquals(10, arr.length);

           for (int i = 0; i < 10; i++)
               assertEquals(env.partition() * 10 + i, arr[i]);
        });

        assertEquals(10, cnt.intValue());
    }