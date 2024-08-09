public void pushBlock(final BlockCapsule block)
      throws ValidateSignatureException, ContractValidateException, ContractExeException {
    this.khaosDb.push(block);
    //todo: check block's validity
    if (!block.generatedByMyself) {
      if (!block.validateSignature()) {
        logger.info("The siganature is not validated.");
        return;
      }

      if (!block.calcMerklerRoot().equals(block.getMerklerRoot())) {
        logger.info("The merkler root doesn't match, Calc result is " + block.calcMerklerRoot()
            + " , the headers is " + block.getMerklerRoot());
        return;
      }

      try (Dialog tmpDialog = revokingStore.buildDialog()) {
          this.processBlock(block);
          tmpDialog.commit();
      } catch (RevokingStoreIllegalStateException e) {
        e.printStackTrace();
      }
      //todo: In some case it need to switch the branch
    }
    this.getBlockStore().dbSource.putData(block.getBlockId().getBytes(), block.getData());
    logger.info("save block, Its ID is " + block.getBlockId() + ", Its num is " + block.getNum());
    this.numHashCache.putData(ByteArray.fromLong(block.getNum()), block.getBlockId().getBytes());
    this.head = this.khaosDb.getHead();
    // blockDbDataSource.putData(blockHash, blockData);
  }