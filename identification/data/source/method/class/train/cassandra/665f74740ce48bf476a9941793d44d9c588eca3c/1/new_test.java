    @Test
    public void collectStats()
    {
        int now = FBUtilities.nowInSeconds();
        long ts = secondToTs(now);
        Row.Builder builder = BTreeRow.unsortedBuilder();
        builder.newRow(c1);
        LivenessInfo liveness = LivenessInfo.create(ts, now);
        builder.addPrimaryKeyLivenessInfo(liveness);
        DeletionTime complexDeletion = new DeletionTime(ts-1, now);
        builder.addComplexDeletion(m, complexDeletion);
        List<Cell> expectedCells = Lists.newArrayList(BufferCell.live(v, ts, BB1),
                                                      BufferCell.live(m, ts, BB1, CellPath.create(BB1)),
                                                      BufferCell.live(m, ts, BB2, CellPath.create(BB2)));
        expectedCells.forEach(builder::addCell);
        // We need to use ts-1 so the deletion doesn't shadow what we've created
        Row.Deletion rowDeletion = new Row.Deletion(new DeletionTime(ts-1, now), false);
        builder.addRowDeletion(rowDeletion);

        StatsCollector collector = new StatsCollector();
        Rows.collectStats(builder.build(), collector);

        Assert.assertEquals(Lists.newArrayList(liveness), collector.liveness);
        Assert.assertEquals(Sets.newHashSet(rowDeletion.time(), complexDeletion), Sets.newHashSet(collector.deletions));
        Assert.assertEquals(Sets.newHashSet(expectedCells), Sets.newHashSet(collector.cells));
        Assert.assertEquals(2, collector.columnCount);
        Assert.assertFalse(collector.hasLegacyCounterShards);
    }