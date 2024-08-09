public static long merge(Row existing,
                             Row update,
                             Row.Builder builder,
                             int nowInSec)
    {
        Clustering clustering = existing.clustering();
        builder.newRow(clustering);

        LivenessInfo existingInfo = existing.primaryKeyLivenessInfo();
        LivenessInfo updateInfo = update.primaryKeyLivenessInfo();
        LivenessInfo mergedInfo = existingInfo.supersedes(updateInfo) ? existingInfo : updateInfo;

        long timeDelta = Math.abs(existingInfo.timestamp() - mergedInfo.timestamp());

        Row.Deletion rowDeletion = existing.deletion().supersedes(update.deletion()) ? existing.deletion() : update.deletion();

        if (rowDeletion.deletes(mergedInfo))
            mergedInfo = LivenessInfo.EMPTY;
        else if (rowDeletion.isShadowedBy(mergedInfo))
            rowDeletion = Row.Deletion.LIVE;

        builder.addPrimaryKeyLivenessInfo(mergedInfo);
        builder.addRowDeletion(rowDeletion);

        DeletionTime deletion = rowDeletion.time();

        Iterator<ColumnData> a = existing.iterator();
        Iterator<ColumnData> b = update.iterator();
        ColumnData nexta = a.hasNext() ? a.next() : null, nextb = b.hasNext() ? b.next() : null;
        while (nexta != null | nextb != null)
        {
            int comparison = nexta == null ? 1 : nextb == null ? -1 : nexta.column.compareTo(nextb.column);
            ColumnData cura = comparison <= 0 ? nexta : null;
            ColumnData curb = comparison >= 0 ? nextb : null;
            ColumnDefinition column = (cura != null ? cura : curb).column;
            if (column.isSimple())
            {
                timeDelta = Math.min(timeDelta, Cells.reconcile((Cell) cura, (Cell) curb, deletion, builder, nowInSec));
            }
            else
            {
                ComplexColumnData existingData = (ComplexColumnData) cura;
                ComplexColumnData updateData = (ComplexColumnData) curb;

                DeletionTime existingDt = existingData == null ? DeletionTime.LIVE : existingData.complexDeletion();
                DeletionTime updateDt = updateData == null ? DeletionTime.LIVE : updateData.complexDeletion();
                DeletionTime maxDt = existingDt.supersedes(updateDt) ? existingDt : updateDt;
                if (maxDt.supersedes(deletion))
                    builder.addComplexDeletion(column, maxDt);
                else
                    maxDt = deletion;

                Iterator<Cell> existingCells = existingData == null ? null : existingData.iterator();
                Iterator<Cell> updateCells = updateData == null ? null : updateData.iterator();
                timeDelta = Math.min(timeDelta, Cells.reconcileComplex(column, existingCells, updateCells, maxDt, builder, nowInSec));
            }

            if (cura != null)
                nexta = a.hasNext() ? a.next() : null;
            if (curb != null)
                nextb = b.hasNext() ? b.next() : null;
        }
        return timeDelta;
    }