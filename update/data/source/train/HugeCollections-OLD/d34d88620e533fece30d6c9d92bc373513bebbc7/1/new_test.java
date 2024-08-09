@Test
    public void testPut() throws ExecutionException, InterruptedException {
        int count = 1000000;
        HugeConfig config = HugeConfig.DEFAULT.clone()
                .setSegments(256)
                .setSmallEntrySize(72) // TODO 64 corrupts the values !!
                .setCapacity(count);

        final HugeHashMap<CharSequence, SampleValues> map =
                new HugeHashMap<CharSequence, SampleValues>(
                        config, CharSequence.class, SampleValues.class);
        long start = System.nanoTime();
        final SampleValues value = new SampleValues();
        StringBuilder user = new StringBuilder();
        for (int i = 0; i < count; i++) {
            value.ee = i;
            value.gg = i;
            value.ii = i;
            map.put(users(user, i), value);
        }
        for (int i = 0; i < count; i++) {
            assertNotNull(map.get(users(user, i), value));
            assertEquals(i, value.ee);
            assertEquals(i, value.gg, 0.0);
            assertEquals(i, value.ii);
        }
        for (int i = 0; i < count; i++)
            assertNotNull(map.get(users(user, i), value));
        for (int i = 0; i < count; i++)
            map.remove(users(user, i));
        long time = System.nanoTime() - start;
        System.out.printf("Put/get %,d K operations per second%n",
                (int) (count * 4 * 1e6 / time));
    }