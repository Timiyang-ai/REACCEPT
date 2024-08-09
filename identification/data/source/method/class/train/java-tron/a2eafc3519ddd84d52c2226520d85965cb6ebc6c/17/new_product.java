public synchronized void pushBlock(final BlockCapsule block)
      throws ValidateSignatureException, ContractValidateException, ContractExeException,
      UnLinkedBlockException, ValidateScheduleException, ValidateBandwidthException {

    try (PendingManager pm = new PendingManager(this)) {

      if (!block.generatedByMyself) {
        if (!block.validateSignature()) {
          logger.info("The siganature is not validated.");
          // TODO: throw exception here.
          return;
        }

        if (!block.calcMerkleRoot().equals(block.getMerkleRoot())) {
          logger.info(
              "The merkler root doesn't match, Calc result is "
                  + block.calcMerkleRoot()
                  + " , the headers is "
                  + block.getMerkleRoot());
          // TODO:throw exception here.
          return;
        }
      }

      // checkWitness
      if (!witnessController.validateWitnessSchedule(block)) {
        throw new ValidateScheduleException("validateWitnessSchedule error");
      }

      BlockCapsule newBlock = this.khaosDb.push(block);

      // DB don't need lower block
      if (getDynamicPropertiesStore().getLatestBlockHeaderHash() == null) {
        if (newBlock.getNum() != 0) {
          return;
        }
      } else {
        if (newBlock.getNum() <= getDynamicPropertiesStore().getLatestBlockHeaderNumber()) {
          return;
        }
        // switch fork
        if (!newBlock
            .getParentHash()
            .equals(getDynamicPropertiesStore().getLatestBlockHeaderHash())) {
          logger.warn(
              "switch fork! new head num = {}, blockid = {}",
              newBlock.getNum(),
              newBlock.getBlockId());

          logger.warn(
              "******** before switchFork ******* push block: "
                  + block.getShortString()
                  + ", new block:"
                  + newBlock.getShortString()
                  + ", dynamic head num: "
                  + dynamicPropertiesStore.getLatestBlockHeaderNumber()
                  + ", dynamic head hash: "
                  + dynamicPropertiesStore.getLatestBlockHeaderHash()
                  + ", dynamic head timestamp: "
                  + dynamicPropertiesStore.getLatestBlockHeaderTimestamp()
                  + ", khaosDb head: "
                  + khaosDb.getHead()
                  + ", khaosDb miniStore size: "
                  + khaosDb.getMiniStore().size()
                  + ", khaosDb unlinkMiniStore size: "
                  + khaosDb.getMiniUnlinkedStore().size());

          switchFork(newBlock);
          logger.info("save block: " + newBlock);

          logger.warn(
              "******** after switchFork ******* push block: "
                  + block.getShortString()
                  + ", new block:"
                  + newBlock.getShortString()
                  + ", dynamic head num: "
                  + dynamicPropertiesStore.getLatestBlockHeaderNumber()
                  + ", dynamic head hash: "
                  + dynamicPropertiesStore.getLatestBlockHeaderHash()
                  + ", dynamic head timestamp: "
                  + dynamicPropertiesStore.getLatestBlockHeaderTimestamp()
                  + ", khaosDb head: "
                  + khaosDb.getHead()
                  + ", khaosDb miniStore size: "
                  + khaosDb.getMiniStore().size()
                  + ", khaosDb unlinkMiniStore size: "
                  + khaosDb.getMiniUnlinkedStore().size());

          return;
        }
        try (Dialog tmpDialog = revokingStore.buildDialog()) {
          applyBlock(newBlock);
          tmpDialog.commit();
        } catch (RevokingStoreIllegalStateException e) {
          logger.debug(e.getMessage(), e);
        } catch (TaposException e) {
          logger.debug(e.getMessage(), e);
        } catch (DupTransactionException e) {
          logger.debug(e.getMessage(), e);
        } catch (TooBigTransactionException e) {
          logger.debug(e.getMessage(), e);
        } catch (TransactionExpirationException e) {
          logger.debug(e.getMessage(), e);
        }
      }
      logger.info("save block: " + newBlock);
    }
  }