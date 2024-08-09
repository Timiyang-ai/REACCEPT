  private MessageInfo updateTtl(MockId idToUpdate) throws StoreException {
    MessageInfo putMsgInfo = allKeys.get(idToUpdate).getFirst();
    MessageInfo info =
        new MessageInfo(idToUpdate, TTL_UPDATE_RECORD_SIZE, false, true, Utils.Infinite_Time, putMsgInfo.getAccountId(),
            putMsgInfo.getContainerId(), time.milliseconds());
    ByteBuffer buffer = ByteBuffer.wrap(TTL_UPDATE_BUF);
    store.updateTtl(new MockMessageWriteSet(Collections.singletonList(info), Collections.singletonList(buffer)));
    ttlUpdatedKeys.add(idToUpdate);
    return info;
  }