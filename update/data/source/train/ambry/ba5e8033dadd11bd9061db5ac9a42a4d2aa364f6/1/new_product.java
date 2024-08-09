boolean hardDelete() throws StoreException {
    if (index.getCurrentEndOffset().compareTo(index.getStartOffset()) > 0) {
      final Timer.Context context = metrics.hardDeleteTime.time();
      try {
        FindInfo info =
            index.findDeletedEntriesSince(startToken, scanSizeInBytes, time.seconds() - messageRetentionSeconds);
        endToken = info.getFindToken();
        pruneHardDeleteRecoveryRange();
        if (!endToken.equals(startToken)) {
          if (!info.getMessageEntries().isEmpty()) {
            performHardDeletes(info.getMessageEntries());
          }
          startToken = endToken;
          return true;
        }
      } catch (StoreException e) {
        if (e.getErrorCode() != StoreErrorCodes.Store_Shutting_Down) {
          metrics.hardDeleteExceptionsCount.inc();
        }
        throw e;
      } finally {
        context.stop();
      }
    }
    return false;
  }