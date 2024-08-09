public static long merge(Row existing,
                             Row update,
                             Columns mergedColumns,
                             Row.Writer writer,
                             int nowInSec,
                             SecondaryIndexManager.Updater indexUpdater)
    {
        Clustering clustering = existing.clustering();
        writeClustering(clustering, writer);

        LivenessInfo existingInfo = existing.primaryKeyLivenessInfo();
        LivenessInfo updateInfo = update.primaryKeyLivenessInfo();
        LivenessInfo mergedInfo = existingInfo.mergeWith(updateInfo);

        long timeDelta = Math.abs(existingInfo.timestamp() - mergedInfo.timestamp());

        DeletionTime deletion = existing.deletion().supersedes(update.deletion()) ? existing.deletion() : update.deletion();

        if (deletion.deletes(mergedInfo))
            mergedInfo = LivenessInfo.NONE;

        writer.writePartitionKeyLivenessInfo(mergedInfo);
        writer.writeRowDeletion(deletion);

        indexUpdater.maybeIndex(clustering, mergedInfo.timestamp(), mergedInfo.ttl(), deletion);

        for (int i = 0; i < mergedColumns.simpleColumnCount(); i++)
        {
            ColumnDefinition c = mergedColumns.getSimple(i);
            Cell existingCell = existing.getCell(c);
            Cell updateCell = update.getCell(c);
            timeDelta = Math.min(timeDelta, Cells.reconcile(clustering,
                                                            existingCell,
                                                            updateCell,
                                                            deletion,
                                                            writer,
                                                            nowInSec,
                                                            indexUpdater));
        }

        for (int i = 0; i < mergedColumns.complexColumnCount(); i++)
        {
            ColumnDefinition c = mergedColumns.getComplex(i);
            DeletionTime existingDt = existing.getDeletion(c);
            DeletionTime updateDt = update.getDeletion(c);
            DeletionTime maxDt = existingDt.supersedes(updateDt) ? existingDt : updateDt;
            if (maxDt.supersedes(deletion))
                writer.writeComplexDeletion(c, maxDt);
            else
                maxDt = deletion;

            Iterator<Cell> existingCells = existing.getCells(c);
            Iterator<Cell> updateCells = update.getCells(c);
            timeDelta = Math.min(timeDelta, Cells.reconcileComplex(clustering, c, existingCells, updateCells, maxDt, writer, nowInSec, indexUpdater));
        }

        writer.endOfRow();
        return timeDelta;
    }