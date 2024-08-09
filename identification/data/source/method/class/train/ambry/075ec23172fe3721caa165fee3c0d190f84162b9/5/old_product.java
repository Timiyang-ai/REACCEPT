public Pair<FindToken, Boolean> copy(FindToken startToken) throws Exception {
    boolean sourceHasProblems = false;
    FindToken lastToken;
    FindToken token = startToken;
    do {
      lastToken = token;
      FindInfo findInfo = src.findEntriesSince(lastToken, fetchSizeInBytes);
      List<MessageInfo> messageInfos = findInfo.getMessageEntries();
      for (MessageInfo messageInfo : messageInfos) {
        logger.trace("Processing {} - isDeleted: {}, isExpired {}", messageInfo.getStoreKey(), messageInfo.isDeleted(),
            messageInfo.isExpired());
        if (!messageInfo.isExpired() && !messageInfo.isDeleted()) {
          if (messageInfo.getSize() > Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot copy blobs whose size > Integer.MAX_VALUE");
          }
          if (tgt.findMissingKeys(Collections.singletonList(messageInfo.getStoreKey())).size() == 1) {
            int size = (int) messageInfo.getSize();
            StoreInfo storeInfo =
                src.get(Collections.singletonList(messageInfo.getStoreKey()), EnumSet.allOf(StoreGetOptions.class));
            MessageReadSet readSet = storeInfo.getMessageReadSet();
            byte[] buf = new byte[size];
            readSet.writeTo(0, new ByteBufferChannel(ByteBuffer.wrap(buf)), 0, size);
            Message message = new Message(messageInfo, new ByteArrayInputStream(buf));
            for (Transformer transformer : transformers) {
              TransformationOutput tfmOutput = transformer.transform(message);
              if (tfmOutput.getException() != null) {
                throw tfmOutput.getException();
              } else {
                message = tfmOutput.getMsg();
              }
              if (message == null) {
                break;
              }
            }
            if (message == null) {
              logger.trace("Dropping {} because the transformers did not return a message", messageInfo.getStoreKey());
              continue;
            }
            MessageFormatWriteSet writeSet =
                new MessageFormatWriteSet(message.getStream(), Collections.singletonList(message.getMessageInfo()),
                    false);
            tgt.put(writeSet);
            MessageInfo tgtMsgInfo = message.getMessageInfo();
            if (tgtMsgInfo.isTtlUpdated()) {
              TtlUpdateMessageFormatInputStream stream =
                  new TtlUpdateMessageFormatInputStream(tgtMsgInfo.getStoreKey(), tgtMsgInfo.getAccountId(),
                      tgtMsgInfo.getContainerId(), tgtMsgInfo.getExpirationTimeInMs(), tgtMsgInfo.getOperationTimeMs());
              MessageInfo updateMsgInfo = new MessageInfo(tgtMsgInfo.getStoreKey(), stream.getSize(), false, true,
                  tgtMsgInfo.getExpirationTimeInMs(), tgtMsgInfo.getAccountId(), tgtMsgInfo.getContainerId(),
                  tgtMsgInfo.getOperationTimeMs());
              writeSet = new MessageFormatWriteSet(stream, Collections.singletonList(updateMsgInfo), false);
              tgt.updateTtl(writeSet);
            }
            logger.trace("Copied {} as {}", messageInfo.getStoreKey(), tgtMsgInfo.getStoreKey());
          } else if (!messageInfo.isTtlUpdated()) {
            logger.warn("Found a duplicate entry for {} while copying data", messageInfo.getStoreKey());
            sourceHasProblems = true;
          }
        }
      }
      token = findInfo.getFindToken();
      double percentBytesRead = src.isEmpty() ? 100.0 : token.getBytesRead() * 100.0 / src.getSizeInBytes();
      logger.info("[{}] [{}] {}% copied", Thread.currentThread().getName(), storeId, df.format(percentBytesRead));
    } while (!token.equals(lastToken));
    return new Pair<>(token, sourceHasProblems);
  }