public static void diff(RowDiffListener diffListener, Row merged, Row...inputs)
    {
        Clustering clustering = merged.clustering();
        LivenessInfo mergedInfo = merged.primaryKeyLivenessInfo().isEmpty() ? null : merged.primaryKeyLivenessInfo();
        Row.Deletion mergedDeletion = merged.deletion().isLive() ? null : merged.deletion();
        for (int i = 0; i < inputs.length; i++)
        {
            Row input = inputs[i];
            LivenessInfo inputInfo = input == null || input.primaryKeyLivenessInfo().isEmpty() ? null : input.primaryKeyLivenessInfo();
            Row.Deletion inputDeletion = input == null || input.deletion().isLive() ? null : input.deletion();

            if (mergedInfo != null || inputInfo != null)
                diffListener.onPrimaryKeyLivenessInfo(i, clustering, mergedInfo, inputInfo);
            if (mergedDeletion != null || inputDeletion != null)
                diffListener.onDeletion(i, clustering, mergedDeletion, inputDeletion);
        }

        List<Iterator<ColumnData>> inputIterators = new ArrayList<>(1 + inputs.length);
        inputIterators.add(merged.iterator());
        for (Row row : inputs)
            inputIterators.add(row == null ? Collections.emptyIterator() : row.iterator());

        Iterator<?> iter = MergeIterator.get(inputIterators, ColumnData.comparator, new MergeIterator.Reducer<ColumnData, Object>()
        {
            ColumnData mergedData;
            ColumnData[] inputDatas = new ColumnData[inputs.length];
            public void reduce(int idx, ColumnData current)
            {
                if (idx == 0)
                    mergedData = current;
                else
                    inputDatas[idx - 1] = current;
            }

            protected Object getReduced()
            {
                for (int i = 0 ; i != inputDatas.length ; i++)
                {
                    ColumnData input = inputDatas[i];
                    if (mergedData != null || input != null)
                    {
                        ColumnMetadata column = (mergedData != null ? mergedData : input).column;
                        if (column.isSimple())
                        {
                            diffListener.onCell(i, clustering, (Cell) mergedData, (Cell) input);
                        }
                        else
                        {
                            ComplexColumnData mergedData = (ComplexColumnData) this.mergedData;
                            ComplexColumnData inputData = (ComplexColumnData) input;
                            if (mergedData == null)
                            {
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

                                if (!mergedData.complexDeletion().isLive() || !inputData.complexDeletion().isLive())
                                    diffListener.onComplexDeletion(i, clustering, column, mergedData.complexDeletion(), inputData.complexDeletion());

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
                return null;
            }

            protected void onKeyChange()
            {
                mergedData = null;
                Arrays.fill(inputDatas, null);
            }
        });

        while (iter.hasNext())
            iter.next();
    }