public static int collectStats(Row row, PartitionStatisticsCollector collector)
    {
        assert !row.isEmpty();

        collector.update(row.primaryKeyLivenessInfo());
        collector.update(row.deletion().time());

        //we have to wrap these for the lambda
        final WrappedInt columnCount = new WrappedInt(0);
        final WrappedInt cellCount = new WrappedInt(0);

        row.apply(cd -> {
            if (cd.column().isSimple())
            {
                columnCount.increment();
                cellCount.increment();
                Cells.collectStats((Cell) cd, collector);
            }
            else
            {
                ComplexColumnData complexData = (ComplexColumnData)cd;
                collector.update(complexData.complexDeletion());
                if (complexData.hasCells())
                {
                    columnCount.increment();
                    for (Cell cell : complexData)
                    {
                        cellCount.increment();
                        Cells.collectStats(cell, collector);
                    }
                }
            }
        }, false);

        collector.updateColumnSetPerRow(columnCount.get());
        return cellCount.get();
    }