public static void merge(Row row1, Row row2, Columns mergedColumns, Row.Builder builder, int nowInSec)
    {
        merge(row1, row2, mergedColumns, builder, nowInSec, SecondaryIndexManager.nullUpdater);
    }