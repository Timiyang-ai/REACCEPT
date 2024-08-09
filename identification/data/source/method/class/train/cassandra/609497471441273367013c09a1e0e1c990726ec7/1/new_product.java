public static UnfilteredRowIterator concat(final UnfilteredRowIterator iter1, final UnfilteredRowIterator iter2)
    {
        assert iter1.metadata().cfId.equals(iter2.metadata().cfId)
            && iter1.partitionKey().equals(iter2.partitionKey())
            && iter1.partitionLevelDeletion().equals(iter2.partitionLevelDeletion())
            && iter1.isReverseOrder() == iter2.isReverseOrder()
            && iter1.columns().equals(iter2.columns())
            && iter1.staticRow().equals(iter2.staticRow());

        class Extend implements MoreRows<UnfilteredRowIterator>
        {
            boolean returned = false;
            public UnfilteredRowIterator moreContents()
            {
                if (returned)
                    return null;
                returned = true;
                return iter2;
            }
        }

        return MoreRows.extend(iter1, new Extend());
    }