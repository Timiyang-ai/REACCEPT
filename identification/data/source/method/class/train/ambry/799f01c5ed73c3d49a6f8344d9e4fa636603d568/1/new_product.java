private void copy() throws InterruptedException, IOException, StoreException {
    setupState();
    List<String> logSegmentsUnderCompaction = compactionLog.getCompactionDetails().getLogSegmentsUnderCompaction();
    FileSpan duplicateSearchSpan = null;
    if (compactionLog.getCurrentIdx() > 0) {
      // only records in the the very first log segment in the cycle could have been copied in a previous cycle
      LogSegment firstLogSegment = srcLog.getSegment(logSegmentsUnderCompaction.get(0));
      LogSegment prevSegment = srcLog.getPrevSegment(firstLogSegment);
      if (prevSegment != null) {
        // duplicate data, if it exists, can only be in the log segment just before the first log segment in the cycle.
        Offset startOffset = new Offset(prevSegment.getName(), prevSegment.getStartOffset());
        Offset endOffset = new Offset(prevSegment.getName(), prevSegment.getEndOffset());
        duplicateSearchSpan = new FileSpan(startOffset, endOffset);
      }
      logger.trace("Duplicate search span is {} for {}", duplicateSearchSpan, storeId);
    }

    for (String logSegmentName : logSegmentsUnderCompaction) {
      logger.debug("Processing {} in {}", logSegmentName, storeId);
      LogSegment srcLogSegment = srcLog.getSegment(logSegmentName);
      Offset logSegmentEndOffset = new Offset(srcLogSegment.getName(), srcLogSegment.getEndOffset());
      if (needsCopying(logSegmentEndOffset) && !copyDataByLogSegment(srcLogSegment, duplicateSearchSpan)) {
        if (isActive) {
          // split the cycle only if there is no shutdown in progress
          logger.debug("Splitting current cycle with segments {} at {} for {}", logSegmentsUnderCompaction,
              logSegmentName, storeId);
          compactionLog.splitCurrentCycle(logSegmentName);
        }
        break;
      }
      duplicateSearchSpan = null;
    }

    numSwapsUsed = tgtIndex.getLogSegmentCount();
    logger.debug("Swaps used to copy {} is {} for {}", compactionLog.getCompactionDetails(), numSwapsUsed, storeId);
    if (isActive) {
      // it is possible to double count based on the time at which shutdown occurs (if it occurs b/w this statement
      // and before the subsequent commit can take effect)
      long segmentCountDiff =
          compactionLog.getCompactionDetails().getLogSegmentsUnderCompaction().size() - numSwapsUsed;
      long savedBytes = srcLog.getSegmentCapacity() * segmentCountDiff;
      srcMetrics.compactionBytesReclaimedCount.inc(savedBytes);
    }
    tgtIndex.close();
    tgtLog.close();
    // persist the bloom of the "latest" index segment if it exists
    if (numSwapsUsed > 0) {
      tgtIndex.getIndexSegments().lastEntry().getValue().map(true);
    } else {
      // there were no valid entries copied, return any temp segments back to the pool
      logger.trace("Cleaning up temp segments in {} because no swap spaces were used", storeId);
      cleanupUnusedTempSegments();
    }
  }