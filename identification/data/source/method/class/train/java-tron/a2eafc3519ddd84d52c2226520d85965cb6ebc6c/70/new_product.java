public void pushBlock(final BlockCapsule block)
      throws ValidateSignatureException, ContractValidateException,
      ContractExeException, UnLinkedBlockException, ValidateScheduleException {

    try (PendingManager pm = new PendingManager(this)) {

      if (!block.generatedByMyself) {
        if (!block.validateSignature()) {
          logger.info("The siganature is not validated.");
          //TODO: throw exception here.
          return;
        }

        if (!block.calcMerkleRoot().equals(block.getMerkleRoot())) {
          logger.info("The merkler root doesn't match, Calc result is " + block.calcMerkleRoot()
              + " , the headers is " + block.getMerkleRoot());
          // TODO:throw exception here.
          return;
        }
      }

      // checkWitness
      if (!witnessController.validateWitnessSchedule(block)) {
        throw new ValidateScheduleException("validateWitnessSchedule error");
      }

      BlockCapsule newBlock = this.khaosDb.push(block);
      //DB don't need lower block
      if (getDynamicPropertiesStore().getLatestBlockHeaderHash() == null) {
        if (newBlock.getNum() != 0) {
          return;
        }
      } else {
        if (newBlock.getNum() <= getDynamicPropertiesStore().getLatestBlockHeaderNumber()) {
          return;
        }
        //switch fork
        if (!newBlock.getParentHash()
            .equals(getDynamicPropertiesStore().getLatestBlockHeaderHash())) {
          logger.warn("switch fork! new head num = {}, blockid = {}", newBlock.getNum(),
              newBlock.getBlockId());
          switchFork(newBlock);
          logger.info("save block: " + newBlock);
          return;
        }
        try (Dialog tmpDialog = revokingStore.buildDialog()) {
          this.processBlock(newBlock);
          tmpDialog.commit();
          blockStore.put(block.getBlockId().getBytes(), block);
          this.blockIndexStore
              .put(ByteArray.fromLong(block.getNum()),
                  new BytesCapsule(block.getBlockId().getBytes()));
        } catch (RevokingStoreIllegalStateException e) {
          logger.debug(e.getMessage(), e);
        }
      }
      logger.info("save block: " + newBlock);
    }
  }