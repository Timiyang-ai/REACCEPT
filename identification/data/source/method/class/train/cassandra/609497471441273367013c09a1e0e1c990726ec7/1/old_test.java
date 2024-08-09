    @Test
    public void concatTest()
    {
        UnfilteredRowIterator iter1, iter2, iter3, concat;
        // simple concatenation
        iter1 = rows(metadata.regularAndStaticColumns(), 1,
                     row(1, cell(v1Metadata, 1), cell(v2Metadata, 1)),
                     row(2, cell(v1Metadata, 2), cell(v2Metadata, 2)));
        iter2 = rows(metadata.regularAndStaticColumns(), 1,
                     row(3, cell(v1Metadata, 3), cell(v2Metadata, 3)),
                     row(4, cell(v1Metadata, 4), cell(v2Metadata, 4)));
        concat = UnfilteredRowIterators.concat(iter1, iter2);
        Assert.assertEquals(concat.columns(), metadata.regularAndStaticColumns());
        assertRows(concat,
                   row(1, cell(v1Metadata, 1), cell(v2Metadata, 1)),
                   row(2, cell(v1Metadata, 2), cell(v2Metadata, 2)),
                   row(3, cell(v1Metadata, 3), cell(v2Metadata, 3)),
                   row(4, cell(v1Metadata, 4), cell(v2Metadata, 4)));

        // concat with RHS empty iterator
        iter1 = rows(metadata.regularAndStaticColumns(), 1,
                     row(1, cell(v1Metadata, 1), cell(v2Metadata, 1)),
                     row(2, cell(v1Metadata, 2), cell(v2Metadata, 2)));
        Assert.assertEquals(concat.columns(), metadata.regularAndStaticColumns());
        assertRows(UnfilteredRowIterators.concat(iter1, EmptyIterators.unfilteredRow(metadata, dk(1), false, Rows.EMPTY_STATIC_ROW, DeletionTime.LIVE)),
                   row(1, cell(v1Metadata, 1), cell(v2Metadata, 1)),
                   row(2, cell(v1Metadata, 2), cell(v2Metadata, 2)));

        // concat with LHS empty iterator
        iter1 = rows(metadata.regularAndStaticColumns(), 1,
                     row(1, cell(v1Metadata, 1), cell(v2Metadata, 1)),
                     row(2, cell(v1Metadata, 2), cell(v2Metadata, 2)));
        Assert.assertEquals(concat.columns(), metadata.regularAndStaticColumns());
        assertRows(UnfilteredRowIterators.concat(EmptyIterators.unfilteredRow(metadata, dk(1), false, Rows.EMPTY_STATIC_ROW, DeletionTime.LIVE), iter1),
                   row(1, cell(v1Metadata, 1), cell(v2Metadata, 1)),
                   row(2, cell(v1Metadata, 2), cell(v2Metadata, 2)));

        // concat with different columns
        iter1 = rows(metadata.regularAndStaticColumns().without(v1Metadata), 1,
                     row(1, cell(v2Metadata, 1)), row(2, cell(v2Metadata, 2)));
        iter2 = rows(metadata.regularAndStaticColumns().without(v2Metadata), 1,
                     row(3, cell(v1Metadata, 3)), row(4, cell(v1Metadata, 4)));
        concat = UnfilteredRowIterators.concat(iter1, iter2);
        Assert.assertEquals(concat.columns(), RegularAndStaticColumns.of(v1Metadata).mergeTo(RegularAndStaticColumns.of(v2Metadata)));
        assertRows(concat,
                   row(1, cell(v2Metadata, 1)), row(2, cell(v2Metadata, 2)),
                   row(3, cell(v1Metadata, 3)), row(4, cell(v1Metadata, 4)));

        // concat with CQL limits
        iter1 = rows(metadata.regularAndStaticColumns(), 1,
                     row(1, cell(v1Metadata, 1), cell(v2Metadata, 1)),
                     row(2, cell(v1Metadata, 2), cell(v2Metadata, 2)));
        iter2 = rows(metadata.regularAndStaticColumns(), 1,
                     row(3, cell(v1Metadata, 3), cell(v2Metadata, 3)),
                     row(4, cell(v1Metadata, 4), cell(v2Metadata, 4)));
        concat = UnfilteredRowIterators.concat(DataLimits.cqlLimits(1).filter(iter1, FBUtilities.nowInSeconds(), true),
                                               DataLimits.cqlLimits(1).filter(iter2, FBUtilities.nowInSeconds(), true));
        Assert.assertEquals(concat.columns(), metadata.regularAndStaticColumns());
        assertRows(concat,
                   row(1, cell(v1Metadata, 1), cell(v2Metadata, 1)),
                   row(3, cell(v1Metadata, 3), cell(v2Metadata, 3)));

        // concat concatenated iterators
        iter1 = rows(metadata.regularAndStaticColumns(), 1,
                     row(1, cell(v1Metadata, 1), cell(v2Metadata, 1)),
                     row(2, cell(v1Metadata, 2), cell(v2Metadata, 2)));
        iter2 = rows(metadata.regularAndStaticColumns(), 1,
                     row(3, cell(v1Metadata, 3), cell(v2Metadata, 3)),
                     row(4, cell(v1Metadata, 4), cell(v2Metadata, 4)));

        concat = UnfilteredRowIterators.concat(DataLimits.cqlLimits(1).filter(iter1, FBUtilities.nowInSeconds(), true),
                                               DataLimits.cqlLimits(1).filter(iter2, FBUtilities.nowInSeconds(), true));

        iter3 = rows(metadata.regularAndStaticColumns(), 1,
                     row(4, cell(v1Metadata, 4), cell(v2Metadata, 4)),
                     row(5, cell(v1Metadata, 5), cell(v2Metadata, 5)));
        concat = UnfilteredRowIterators.concat(concat, DataLimits.cqlLimits(1).filter(iter3, FBUtilities.nowInSeconds(), true));

        Assert.assertEquals(concat.columns(), metadata.regularAndStaticColumns());
        assertRows(concat,
                   row(1, cell(v1Metadata, 1), cell(v2Metadata, 1)),
                   row(3, cell(v1Metadata, 3), cell(v2Metadata, 3)),
                   row(4, cell(v1Metadata, 4), cell(v2Metadata, 4)));
    }