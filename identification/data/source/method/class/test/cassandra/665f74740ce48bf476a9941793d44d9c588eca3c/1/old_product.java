public static int collectStats(Row row, PartitionStatisticsCollector collector)
    {
        assert !row.isEmpty();

        collector.update(row.primaryKeyLivenessInfo());
        collector.update(row.deletion());

        int columnCount = 0;
        int cellCount = 0;
        for (ColumnData cd : row)
        {
            if (cd.column().isSimple())
            {
                ++columnCount;
                ++cellCount;
                Cells.collectStats((Cell)cd, collector);
            }
            else
            {
                ComplexColumnData complexData = (ComplexColumnData)cd;
                collector.update(complexData.complexDeletion());
                if (complexData.hasCells())
                {
                    ++columnCount;
                    for (Cell cell : complexData)
                    {
                        ++cellCount;
                        Cells.collectStats(cell, collector);
                    }
                }
            }

        }
        collector.updateColumnSetPerRow(columnCount);
        return cellCount;
    }