public FindToken copy(FindToken startToken) throws IOException, StoreException {
    FindToken lastToken = null;
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
          int size = (int) messageInfo.getSize();
          StoreInfo storeInfo =
              src.get(Collections.singletonList(messageInfo.getStoreKey()), EnumSet.noneOf(StoreGetOptions.class));
          MessageReadSet readSet = storeInfo.getMessageReadSet();
          byte[] buf = new byte[size];
          readSet.writeTo(0, new ByteBufferChannel(ByteBuffer.wrap(buf)), 0, size);
          Message message = new Message(messageInfo, new ByteArrayInputStream(buf));
          for (Transformer transformer : transformers) {
            message = transformer.transform(message);
          }
          MessageFormatWriteSet writeSet =
              new MessageFormatWriteSet(message.getStream(), Collections.singletonList(message.getMessageInfo()),
                  false);
          tgt.put(writeSet);
          logger.trace("Copied {} as {}", messageInfo.getStoreKey(), message.getMessageInfo().getStoreKey());
        }
      }
      token = findInfo.getFindToken();
      logger.info("[{}] [{}] {}% copied", Thread.currentThread().getName(), storeId,
          token.getBytesRead() * 100 / src.getSizeInBytes());
    } while (!token.equals(lastToken));
    return token;
  }