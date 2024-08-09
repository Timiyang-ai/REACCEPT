    @Test
    public void merge()
    {
        int now1 = FBUtilities.nowInSeconds();
        Row.Builder existingBuilder = createBuilder(c1, now1, BB1, BB1, BB1);

        int now2 = now1 + 1;
        long ts2 = secondToTs(now2);

        Cell expectedVCell = BufferCell.live(v, ts2, BB2);
        Cell expectedMCell = BufferCell.live(m, ts2, BB2, CellPath.create(BB1));
        DeletionTime expectedComplexDeletionTime = new DeletionTime(ts2 - 1, now2);

        Row.Builder updateBuilder = createBuilder(c1, now2, null, null, null);
        updateBuilder.addCell(expectedVCell);
        updateBuilder.addComplexDeletion(m, expectedComplexDeletionTime);
        updateBuilder.addCell(expectedMCell);

        RowBuilder builder = new RowBuilder();
        long td = Rows.merge(existingBuilder.build(), updateBuilder.build(), builder);

        Assert.assertEquals(c1, builder.clustering);
        Assert.assertEquals(LivenessInfo.create(ts2, now2), builder.livenessInfo);
        Assert.assertEquals(Lists.newArrayList(Pair.create(m, new DeletionTime(ts2-1, now2))), builder.complexDeletions);

        Assert.assertEquals(2, builder.cells.size());
        Assert.assertEquals(Lists.newArrayList(expectedVCell, expectedMCell), Lists.newArrayList(builder.cells));
        Assert.assertEquals(ts2 - secondToTs(now1), td);
    }