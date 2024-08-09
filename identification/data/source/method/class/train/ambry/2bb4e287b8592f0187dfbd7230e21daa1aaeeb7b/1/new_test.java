@Test
  public void getBlobReadInfoTest() throws StoreException {
    final AtomicReference<MockId> idRequested = new AtomicReference<>();
    state.hardDelete = new MessageStoreHardDelete() {
      @Override
      public Iterator<HardDeleteInfo> getHardDeleteMessages(MessageReadSet readSet, StoreKeyFactory factory,
          List<byte[]> recoveryInfoList) throws IOException {
        throw new UnsupportedOperationException();
      }

      @Override
      public MessageInfo getMessageInfo(Read read, long offset, StoreKeyFactory factory) throws IOException {
        MockId id = idRequested.get();
        if (id == null) {
          throw new IllegalStateException("No ID was set before making a call to getBlobReadInfo()");
        }
        IndexValue value = state.getExpectedValue(id, true);
        return new MessageInfo(id, value.getSize(), value.getExpiresAtMs(), value.getAccountId(),
            value.getContainerId(), value.getOperationTimeInMs());
      }
    };
    state.reloadIndex(true, false);
    List<EnumSet<StoreGetOptions>> allCombos = new ArrayList<>();
    allCombos.add(EnumSet.noneOf(StoreGetOptions.class));
    allCombos.add(EnumSet.of(StoreGetOptions.Store_Include_Expired));
    allCombos.add(EnumSet.of(StoreGetOptions.Store_Include_Deleted));
    allCombos.add(EnumSet.allOf(StoreGetOptions.class));
    for (MockId id : state.allKeys.keySet()) {
      idRequested.set(id);
      for (EnumSet<StoreGetOptions> getOptions : allCombos) {
        if (state.liveKeys.contains(id)) {
          verifyBlobReadOptions(id, getOptions, null);
        } else if (state.expiredKeys.contains(id)) {
          StoreErrorCodes expectedErrorCode =
              getOptions.contains(StoreGetOptions.Store_Include_Expired) ? null : StoreErrorCodes.TTL_Expired;
          verifyBlobReadOptions(id, getOptions, expectedErrorCode);
        } else if (state.deletedKeys.contains(id)) {
          StoreErrorCodes expectedErrorCode =
              state.getExpectedValue(id, true) != null && getOptions.contains(StoreGetOptions.Store_Include_Deleted)
                  ? null : StoreErrorCodes.ID_Deleted;
          verifyBlobReadOptions(id, getOptions, expectedErrorCode);
        }
      }
    }
    // try to get BlobReadOption for a non existent key
    MockId nonExistentId = state.getUniqueId();
    verifyBlobReadOptions(nonExistentId, EnumSet.allOf(StoreGetOptions.class), StoreErrorCodes.ID_Not_Found);
  }