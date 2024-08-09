    @Test
    public void complementRowCountForMandatoryCuboidsTest() {
        Map<Long, Long> countMap = simulateCount();
        Map<Long, Long> srcCuboidsStats = CuboidStatsUtil.generateSourceCuboidStats(countMap,
                simulateHitProbability(baseCuboidId), simulateRollingUpCount());
        for (long mandatoryCuboid : srcCuboidsStats.keySet()) {
            Assert.assertNull(countMap.get(mandatoryCuboid));
        }
        Assert.assertTrue(srcCuboidsStats.get(239L) == 200L);

        Map<Long, Long> mandatoryCuboidsWithStats2 = Maps.newHashMap();
        mandatoryCuboidsWithStats2.put(215L, countMap.get(255L));
        mandatoryCuboidsWithStats2.put(34L, countMap.get(50L));
        Assert.assertEquals(mandatoryCuboidsWithStats2,
                CuboidStatsUtil.complementRowCountForCuboids(countMap, mandatoryCuboidsWithStats2.keySet()));
    }