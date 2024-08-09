public static UnfilteredRowIterator concat(final UnfilteredRowIterator iter1, final UnfilteredRowIterator iter2)
    {
        assert iter1.metadata().cfId.equals(iter2.metadata().cfId)
            && iter1.partitionKey().equals(iter2.partitionKey())
            && iter1.partitionLevelDeletion().equals(iter2.partitionLevelDeletion())
            && iter1.isReverseOrder() == iter2.isReverseOrder()
            && iter1.columns().equals(iter2.columns())
            && iter1.staticRow().equals(iter2.staticRow());

        return new AbstractUnfilteredRowIterator(iter1.metadata(),
                                                 iter1.partitionKey(),
                                                 iter1.partitionLevelDeletion(),
                                                 iter1.columns(),
                                                 iter1.staticRow(),
                                                 iter1.isReverseOrder(),
                                                 iter1.stats())
        {
            protected Unfiltered computeNext()
            {
                if (iter1.hasNext())
                    return iter1.next();

                return iter2.hasNext() ? iter2.next() : endOfData();
            }

            @Override
            public void close()
            {
                try
                {
                    iter1.close();
                }
                finally
                {
                    iter2.close();
                }
            }
        };
    }