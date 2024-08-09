    @Test
    public void diff()
    {
        int now1 = FBUtilities.nowInSeconds();
        long ts1 = secondToTs(now1);
        Row.Builder r1Builder = BTreeRow.unsortedBuilder();
        r1Builder.newRow(c1);
        LivenessInfo r1Liveness = LivenessInfo.create(ts1, now1);
        r1Builder.addPrimaryKeyLivenessInfo(r1Liveness);
        DeletionTime r1ComplexDeletion = new DeletionTime(ts1-1, now1);
        r1Builder.addComplexDeletion(m, r1ComplexDeletion);

        Cell r1v = BufferCell.live(v, ts1, BB1);
        Cell r1m1 = BufferCell.live(m, ts1, BB1, CellPath.create(BB1));
        Cell r1m2 = BufferCell.live(m, ts1, BB2, CellPath.create(BB2));
        List<Cell> r1ExpectedCells = Lists.newArrayList(r1v, r1m1, r1m2);

        r1ExpectedCells.forEach(r1Builder::addCell);

        int now2 = now1 + 1;
        long ts2 = secondToTs(now2);
        Row.Builder r2Builder = BTreeRow.unsortedBuilder();
        r2Builder.newRow(c1);
        LivenessInfo r2Liveness = LivenessInfo.create(ts2, now2);
        r2Builder.addPrimaryKeyLivenessInfo(r2Liveness);
        Cell r2v = BufferCell.live(v, ts2, BB2);
        Cell r2m2 = BufferCell.live(m, ts2, BB1, CellPath.create(BB2));
        Cell r2m3 = BufferCell.live(m, ts2, BB2, CellPath.create(BB3));
        Cell r2m4 = BufferCell.live(m, ts2, BB3, CellPath.create(BB4));
        List<Cell> r2ExpectedCells = Lists.newArrayList(r2v, r2m2, r2m3, r2m4);

        r2ExpectedCells.forEach(r2Builder::addCell);
        Row.Deletion r2RowDeletion = new Row.Deletion(new DeletionTime(ts1 - 2, now2), false);
        r2Builder.addRowDeletion(r2RowDeletion);

        Row r1 = r1Builder.build();
        Row r2 = r2Builder.build();
        Row merged = Rows.merge(r1, r2);

        Assert.assertEquals(r1ComplexDeletion, merged.getComplexColumnData(m).complexDeletion());

        DiffListener listener = new DiffListener();
        Rows.diff(listener, merged, r1, r2);

        Assert.assertEquals(c1, listener.clustering);

        // check cells
        Set<MergedPair<Cell>> expectedCells = Sets.newHashSet();
        addExpectedCells(expectedCells, r2v,  r1v,  r2v);     // v
        addExpectedCells(expectedCells, r1m1, r1m1, null);   // m[1]
        addExpectedCells(expectedCells, r2m2, r1m2, r2m2);   // m[2]
        addExpectedCells(expectedCells, r2m3, null, r2m3);   // m[3]
        addExpectedCells(expectedCells, r2m4, null, r2m4);   // m[4]

        Assert.assertEquals(expectedCells.size(), listener.cells.size());
        Assert.assertEquals(expectedCells, Sets.newHashSet(listener.cells));

        // liveness
        List<MergedPair<LivenessInfo>> expectedLiveness = Lists.newArrayList(MergedPair.create(0, r2Liveness, r1Liveness),
                                                                             MergedPair.create(1, r2Liveness, r2Liveness));
        Assert.assertEquals(expectedLiveness, listener.liveness);

        // deletions
        List<MergedPair<Row.Deletion>> expectedDeletions = Lists.newArrayList(MergedPair.create(0, r2RowDeletion, null),
                                                                              MergedPair.create(1, r2RowDeletion, r2RowDeletion));
        Assert.assertEquals(expectedDeletions, listener.deletions);

        // complex deletions
        List<MergedPair<DeletionTime>> expectedCmplxDeletions = Lists.newArrayList(MergedPair.create(0, r1ComplexDeletion, r1ComplexDeletion),
                                                                                   MergedPair.create(1, r1ComplexDeletion, DeletionTime.LIVE));
        Assert.assertEquals(ImmutableMap.builder().put(m, expectedCmplxDeletions).build(), listener.complexDeletions);
    }