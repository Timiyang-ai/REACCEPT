public static long merge(Row existing,
                             Row update,
                             Columns mergedColumns,
                             Row.Builder builder,
                             int nowInSec,
                             SecondaryIndexManager.Updater indexUpdater)
    {
        Clustering clustering = existing.clustering();
        builder.newRow(clustering);

        LivenessInfo existingInfo = existing.primaryKeyLivenessInfo();
        LivenessInfo updateInfo = update.primaryKeyLivenessInfo();
        LivenessInfo mergedInfo = existingInfo.supersedes(updateInfo) ? existingInfo : updateInfo;

        long timeDelta = Math.abs(existingInfo.timestamp() - mergedInfo.timestamp());

        DeletionTime deletion = existing.deletion().supersedes(update.deletion()) ? existing.deletion() : update.deletion();

        if (deletion.deletes(mergedInfo))
            mergedInfo = LivenessInfo.EMPTY;

        builder.addPrimaryKeyLivenessInfo(mergedInfo);
        builder.addRowDeletion(deletion);

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
                                                            builder,
                                                            nowInSec,
                                                            indexUpdater));
        }

        for (int i = 0; i < mergedColumns.complexColumnCount(); i++)
        {
            ColumnDefinition c = mergedColumns.getComplex(i);
            ComplexColumnData existingData = existing.getComplexColumnData(c);
            ComplexColumnData updateData = update.getComplexColumnData(c);

            DeletionTime existingDt = existingData == null ? DeletionTime.LIVE : existingData.complexDeletion();
            DeletionTime updateDt = updateData == null ? DeletionTime.LIVE : updateData.complexDeletion();
            DeletionTime maxDt = existingDt.supersedes(updateDt) ? existingDt : updateDt;
            if (maxDt.supersedes(deletion))
                builder.addComplexDeletion(c, maxDt);
            else
                maxDt = deletion;

            Iterator<Cell> existingCells = existingData == null ? null : existingData.iterator();
            Iterator<Cell> updateCells = updateData == null ? null : updateData.iterator();
            timeDelta = Math.min(timeDelta, Cells.reconcileComplex(clustering, c, existingCells, updateCells, maxDt, builder, nowInSec, indexUpdater));
        }

        return timeDelta;
    }