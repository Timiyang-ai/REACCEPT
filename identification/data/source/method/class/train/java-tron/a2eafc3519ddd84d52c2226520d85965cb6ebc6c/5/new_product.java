public void pushBlock(final BlockCapsule block)
      throws ValidateSignatureException, ContractValidateException,
      ContractExeException, UnLinkedBlockException {

    //todo: check block's validity
    if (!block.generatedByMyself) {
      if (!block.validateSignature()) {
        logger.info("The siganature is not validated.");
        //TODO: throw exception here.
        return;
      }

      if (!block.calcMerkleRoot().equals(block.getMerkleRoot())) {
        logger.info("The merkler root doesn't match, Calc result is " + block.calcMerkleRoot()
            + " , the headers is " + block.getMerkleRoot());
        //TODO: throw exception here.
        return;
      }
    }

    BlockCapsule newBlock = this.khaosDb.push(block);
    //DB don't need lower block
    if (head == null) {
      if (newBlock.getNum() != 0) {
        return;
      }
    } else {
      if (newBlock.getNum() <= head.getNum()) {
        return;
      }
      //switch fork
      if (!newBlock.getParentHash().equals(head.getBlockId())) {
        switchFork(newBlock);
      }

      try (Dialog tmpDialog = revokingStore.buildDialog()) {
        this.processBlock(newBlock);
        tmpDialog.commit();
      } catch (RevokingStoreIllegalStateException e) {
        e.printStackTrace();
      }
    }

    this.getBlockStore().dbSource.putData(block.getBlockId().getBytes(), block.getData());
    this.numHashCache.putData(ByteArray.fromLong(block.getNum()), block.getBlockId().getBytes());
    refreshHead(newBlock);
    logger.info("save block: " + newBlock);
  }