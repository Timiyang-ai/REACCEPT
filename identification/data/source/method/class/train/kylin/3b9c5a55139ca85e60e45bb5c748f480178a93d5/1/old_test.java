    @Test
    public void generateMandatoryCuboidSetTest() {
        Map<Long, Long> srcCuboidSet = CuboidStatsUtil.generateSourceCuboidStats(simulateCount(),
                simulateHitProbability(baseCuboidId), simulateRollingUpCount());

        Assert.assertTrue(srcCuboidSet.get(239L) == 200L);
        Assert.assertTrue(srcCuboidSet.get(187L) == 1000L);
        Assert.assertTrue(srcCuboidSet.get(178L) == 800L);

        Assert.assertTrue(!srcCuboidSet.containsKey(0L));
    }