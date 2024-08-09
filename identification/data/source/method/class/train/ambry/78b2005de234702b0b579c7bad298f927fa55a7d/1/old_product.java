FindInfo findEntriesSince(FindToken token, long maxTotalSizeOfEntries) throws StoreException {
    long startTimeInMs = time.milliseconds();
    try {
      Offset logEndOffsetBeforeFind = log.getEndOffset();
      StoreFindToken storeToken = resetTokenIfRequired((StoreFindToken) token);
      logger.trace("Time used to validate token: {}", (time.milliseconds() - startTimeInMs));
      ConcurrentSkipListMap<Offset, IndexSegment> indexSegments = validIndexSegments;
      storeToken = revalidateStoreFindToken(storeToken, indexSegments);

      List<MessageInfo> messageEntries = new ArrayList<MessageInfo>();
      if (!storeToken.getType().equals(StoreFindToken.Type.IndexBased)) {
        startTimeInMs = time.milliseconds();
        Offset offsetToStart = storeToken.getOffset();
        if (storeToken.getType().equals(StoreFindToken.Type.Uninitialized)) {
          offsetToStart = getStartOffset();
        }
        logger.trace("Index : " + dataDir + " getting entries since " + offsetToStart);
        // check journal
        List<JournalEntry> entries = journal.getEntriesSince(offsetToStart, storeToken.getInclusive());
        logger.trace("Journal based token, Time used to get entries: {}", (time.milliseconds() - startTimeInMs));
        // we deliberately obtain a snapshot of the index segments AFTER fetching from the journal. This ensures that
        // any and all entries returned from the journal are guaranteed to be in the obtained snapshot of indexSegments.
        indexSegments = validIndexSegments;

        // recheck to make sure that offsetToStart hasn't slipped out of the journal. It is possible (but highly
        // improbable) that the offset has slipped out of the journal and has been compacted b/w the time of getting
        // entries from the journal to when another view of the index segments was obtained.
        if (entries != null && offsetToStart.compareTo(journal.getFirstOffset()) >= 0) {
          startTimeInMs = time.milliseconds();
          logger.trace(
              "Index : " + dataDir + " retrieving from journal from offset " + offsetToStart + " total entries "
                  + entries.size());
          Offset offsetEnd = offsetToStart;
          long currentTotalSizeOfEntries = 0;
          for (JournalEntry entry : entries) {
            IndexValue value =
                findKey(entry.getKey(), new FileSpan(entry.getOffset(), getCurrentEndOffset(indexSegments)),
                    EnumSet.allOf(IndexEntryType.class), indexSegments);
            messageEntries.add(
                new MessageInfo(entry.getKey(), value.getSize(), value.isFlagSet(IndexValue.Flags.Delete_Index),
                    value.isFlagSet(IndexValue.Flags.Ttl_Update_Index), value.getExpiresAtMs(), value.getAccountId(),
                    value.getContainerId(), value.getOperationTimeInMs()));
            currentTotalSizeOfEntries += value.getSize();
            offsetEnd = entry.getOffset();
            if (currentTotalSizeOfEntries >= maxTotalSizeOfEntries) {
              break;
            }
          }
          logger.trace("Journal based token, Time used to generate message entries: {}",
              (time.milliseconds() - startTimeInMs));

          startTimeInMs = time.milliseconds();
          logger.trace("Index : " + dataDir + " new offset from find info " + offsetEnd);
          eliminateDuplicates(messageEntries);
          logger.trace("Journal based token, Time used to eliminate duplicates: {}",
              (time.milliseconds() - startTimeInMs));

          StoreFindToken storeFindToken = new StoreFindToken(offsetEnd, sessionId, incarnationId, false);
          // use the latest ref of indexSegments
          long bytesRead = getTotalBytesRead(storeFindToken, messageEntries, logEndOffsetBeforeFind, indexSegments);
          storeFindToken.setBytesRead(bytesRead);
          return new FindInfo(messageEntries, storeFindToken);
        } else {
          storeToken = revalidateStoreFindToken(storeToken, indexSegments);
          offsetToStart = storeToken.getType().equals(StoreFindToken.Type.Uninitialized) ? getStartOffset(indexSegments)
              : storeToken.getOffset();
          // Find index segment closest to the token offset.
          // Get entries starting from the first key in this offset.
          Map.Entry<Offset, IndexSegment> entry = indexSegments.floorEntry(offsetToStart);
          StoreFindToken newToken;
          if (entry != null && !entry.getKey().equals(indexSegments.lastKey())) {
            startTimeInMs = time.milliseconds();
            newToken = findEntriesFromSegmentStartOffset(entry.getKey(), null, messageEntries,
                new FindEntriesCondition(maxTotalSizeOfEntries), indexSegments);
            logger.trace("Journal based to segment based token, Time used to find entries: {}",
                (time.milliseconds() - startTimeInMs));

            startTimeInMs = time.milliseconds();
            updateStateForMessages(messageEntries);
            logger.trace("Journal based to segment based token, Time used to update state: {}",
                (time.milliseconds() - startTimeInMs));
          } else {
            newToken = storeToken;
          }
          startTimeInMs = time.milliseconds();
          eliminateDuplicates(messageEntries);
          logger.trace("Journal based to segment based token, Time used to eliminate duplicates: {}",
              (time.milliseconds() - startTimeInMs));
          logger.trace("Index [{}]: new FindInfo [{}]", dataDir, newToken);
          long totalBytesRead = getTotalBytesRead(newToken, messageEntries, logEndOffsetBeforeFind, indexSegments);
          newToken.setBytesRead(totalBytesRead);
          return new FindInfo(messageEntries, newToken);
        }
      } else {
        // Find the index segment corresponding to the token indexStartOffset.
        // Get entries starting from the token Key in this index.
        startTimeInMs = time.milliseconds();
        StoreFindToken newToken =
            findEntriesFromSegmentStartOffset(storeToken.getOffset(), storeToken.getStoreKey(), messageEntries,
                new FindEntriesCondition(maxTotalSizeOfEntries), indexSegments);
        logger.trace("Segment based token, Time used to find entries: {}", (time.milliseconds() - startTimeInMs));

        startTimeInMs = time.milliseconds();
        updateStateForMessages(messageEntries);
        logger.trace("Segment based token, Time used to update state: {}", (time.milliseconds() - startTimeInMs));

        startTimeInMs = time.milliseconds();
        eliminateDuplicates(messageEntries);
        logger.trace("Segment based token, Time used to eliminate duplicates: {}",
            (time.milliseconds() - startTimeInMs));

        long totalBytesRead = getTotalBytesRead(newToken, messageEntries, logEndOffsetBeforeFind, indexSegments);
        newToken.setBytesRead(totalBytesRead);
        return new FindInfo(messageEntries, newToken);
      }
    } catch (IOException e) {
      throw new StoreException("IOError when finding entries for index " + dataDir, e, StoreErrorCodes.IOError);
    } catch (Exception e) {
      throw new StoreException("Unknown error when finding entries for index " + dataDir, e,
          StoreErrorCodes.Unknown_Error);
    }
  }