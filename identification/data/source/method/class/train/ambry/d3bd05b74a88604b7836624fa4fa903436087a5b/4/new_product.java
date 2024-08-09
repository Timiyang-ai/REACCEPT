@Override
  public void updateTtl(MessageWriteSet messageSetToUpdate) throws StoreException {
    checkStarted();
    checkDuplicates(messageSetToUpdate);
    final Timer.Context context = metrics.ttlUpdateResponse.time();
    try {
      List<IndexValue> indexValuesToUpdate = new ArrayList<>();
      List<MessageInfo> infoList = messageSetToUpdate.getMessageSetInfo();
      Offset indexEndOffsetBeforeCheck = index.getCurrentEndOffset();
      for (MessageInfo info : infoList) {
        if (info.getExpirationTimeInMs() != Utils.Infinite_Time) {
          throw new StoreException("BlobStore only supports removing the expiration time",
              StoreErrorCodes.Update_Not_Allowed);
        }
        IndexValue value = index.findKey(info.getStoreKey());
        if (value == null) {
          throw new StoreException("Cannot update TTL of " + info.getStoreKey() + " since it's not in the index",
              StoreErrorCodes.ID_Not_Found);
        } else if (!info.getStoreKey().isAccountContainerMatch(value.getAccountId(), value.getContainerId())) {
          if (config.storeValidateAuthorization) {
            throw new StoreException(
                "UPDATE authorization failure. Key: " + info.getStoreKey() + " AccountId in store: "
                    + value.getAccountId() + " ContainerId in store: " + value.getContainerId(),
                StoreErrorCodes.Authorization_Failure);
          } else {
            logger.warn("UPDATE authorization failure. Key: {} AccountId in store: {} ContainerId in store: {}",
                info.getStoreKey(), value.getAccountId(), value.getContainerId());
            metrics.ttlUpdateAuthorizationFailureCount.inc();
          }
        } else if (value.isFlagSet(IndexValue.Flags.Delete_Index)) {
          throw new StoreException(
              "Cannot update TTL of " + info.getStoreKey() + " since it is already deleted in the index.",
              StoreErrorCodes.ID_Deleted);
        } else if (value.isFlagSet(IndexValue.Flags.Ttl_Update_Index)) {
          throw new StoreException("TTL of " + info.getStoreKey() + " is already updated in the index.",
              StoreErrorCodes.Already_Updated);
        } else if (value.getExpiresAtMs() != Utils.Infinite_Time
            && value.getExpiresAtMs() < info.getOperationTimeMs() + ttlUpdateBufferTimeMs) {
          throw new StoreException(
              "TTL of " + info.getStoreKey() + " cannot be updated because it is too close to expiry. Op time (ms): "
                  + info.getOperationTimeMs() + ". ExpiresAtMs: " + value.getExpiresAtMs(),
              StoreErrorCodes.Update_Not_Allowed);
        }
        indexValuesToUpdate.add(value);
      }
      synchronized (storeWriteLock) {
        Offset currentIndexEndOffset = index.getCurrentEndOffset();
        if (!currentIndexEndOffset.equals(indexEndOffsetBeforeCheck)) {
          FileSpan fileSpan = new FileSpan(indexEndOffsetBeforeCheck, currentIndexEndOffset);
          for (MessageInfo info : infoList) {
            IndexValue value =
                index.findKey(info.getStoreKey(), fileSpan, EnumSet.allOf(PersistentIndex.IndexEntryType.class));
            if (value != null) {
              if (value.isFlagSet(IndexValue.Flags.Delete_Index)) {
                throw new StoreException(
                    "Cannot update TTL of " + info.getStoreKey() + " since it is already deleted in the index.",
                    StoreErrorCodes.ID_Deleted);
              } else if (value.isFlagSet(IndexValue.Flags.Ttl_Update_Index)) {
                throw new StoreException("TTL of " + info.getStoreKey() + " is already updated in the index.",
                    StoreErrorCodes.Already_Updated);
              }
            }
          }
        }
        Offset endOffsetOfLastMessage = log.getEndOffset();
        messageSetToUpdate.writeTo(log);
        logger.trace("Store : {} ttl update mark written to log", dataDir);
        int correspondingPutIndex = 0;
        for (MessageInfo info : infoList) {
          FileSpan fileSpan = log.getFileSpanForMessage(endOffsetOfLastMessage, info.getSize());
          IndexValue ttlUpdateValue = index.markAsPermanent(info.getStoreKey(), fileSpan, info.getOperationTimeMs());
          endOffsetOfLastMessage = fileSpan.getEndOffset();
          blobStoreStats.handleNewTtlUpdateEntry(ttlUpdateValue, indexValuesToUpdate.get(correspondingPutIndex++));
        }
        logger.trace("Store : {} ttl update has been marked in the index ", dataDir);
      }
      onSuccess();
    } catch (StoreException e) {
      if (e.getErrorCode() == StoreErrorCodes.IOError) {
        onError();
      }
      throw e;
    } catch (Exception e) {
      throw new StoreException("Unknown error while trying to update ttl of blobs from store " + dataDir, e,
          StoreErrorCodes.Unknown_Error);
    } finally {
      context.stop();
    }
  }