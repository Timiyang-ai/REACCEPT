public static void diff(Row merged, Columns columns, Row[] inputs, RowDiffListener diffListener)
    {
        Clustering clustering = merged.clustering();
        LivenessInfo mergedInfo = merged.primaryKeyLivenessInfo().isEmpty() ? null : merged.primaryKeyLivenessInfo();
        DeletionTime mergedDeletion = merged.deletion().isLive() ? null : merged.deletion();
        for (int i = 0; i < inputs.length; i++)
        {
            Row input = inputs[i];
            LivenessInfo inputInfo = input == null || input.primaryKeyLivenessInfo().isEmpty() ? null : input.primaryKeyLivenessInfo();
            DeletionTime inputDeletion = input == null || input.deletion().isLive() ? null : input.deletion();

            if (mergedInfo != null || inputInfo != null)
                diffListener.onPrimaryKeyLivenessInfo(i, clustering, mergedInfo, inputInfo);
            if (mergedDeletion != null || inputDeletion != null)
                diffListener.onDeletion(i, clustering, mergedDeletion, inputDeletion);
        }

        SearchIterator<ColumnDefinition, ColumnData> mergedIterator = merged.searchIterator();
        List<SearchIterator<ColumnDefinition, ColumnData>> inputIterators = new ArrayList<>(inputs.length);

        for (Row row : inputs)
            inputIterators.add(row == null ? EMPTY_SEARCH_ITERATOR : row.searchIterator());

        Iterator<ColumnDefinition> simpleColumns = columns.simpleColumns();
        while (simpleColumns.hasNext())
        {
            ColumnDefinition column = simpleColumns.next();
            Cell mergedCell = (Cell)mergedIterator.next(column);
            for (int i = 0; i < inputs.length; i++)
            {
                Cell inputCell = (Cell)inputIterators.get(i).next(column);
                if (mergedCell != null || inputCell != null)
                    diffListener.onCell(i, clustering, mergedCell, inputCell);
            }
        }

        Iterator<ColumnDefinition> complexColumns = columns.complexColumns();
        while (complexColumns.hasNext())
        {
            ColumnDefinition column = complexColumns.next();
            ComplexColumnData mergedData = (ComplexColumnData)mergedIterator.next(column);
            // Doing one input at a time is not the most efficient, but it's a lot simpler for now
            for (int i = 0; i < inputs.length; i++)
            {
                ComplexColumnData inputData = (ComplexColumnData)inputIterators.get(i).next(column);
                if (mergedData == null)
                {
                    if (inputData == null)
                        continue;

                    // Everything in inputData has been shadowed
                    if (!inputData.complexDeletion().isLive())
                        diffListener.onComplexDeletion(i, clustering, column, null, inputData.complexDeletion());
                    for (Cell inputCell : inputData)
                        diffListener.onCell(i, clustering, null, inputCell);
                }
                else if (inputData == null)
                {
                    // Everything in inputData is new
                    if (!mergedData.complexDeletion().isLive())
                        diffListener.onComplexDeletion(i, clustering, column, mergedData.complexDeletion(), null);
                    for (Cell mergedCell : mergedData)
                        diffListener.onCell(i, clustering, mergedCell, null);
                }
                else
                {
                    PeekingIterator<Cell> mergedCells = Iterators.peekingIterator(mergedData.iterator());
                    PeekingIterator<Cell> inputCells = Iterators.peekingIterator(inputData.iterator());
                    while (mergedCells.hasNext() && inputCells.hasNext())
                    {
                        int cmp = column.cellPathComparator().compare(mergedCells.peek().path(), inputCells.peek().path());
                        if (cmp == 0)
                            diffListener.onCell(i, clustering, mergedCells.next(), inputCells.next());
                        else if (cmp < 0)
                            diffListener.onCell(i, clustering, mergedCells.next(), null);
                        else // cmp > 0
                            diffListener.onCell(i, clustering, null, inputCells.next());
                    }
                    while (mergedCells.hasNext())
                        diffListener.onCell(i, clustering, mergedCells.next(), null);
                    while (inputCells.hasNext())
                        diffListener.onCell(i, clustering, null, inputCells.next());
                }
            }
        }
    }