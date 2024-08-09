public synchronized void pushBlock(final BlockCapsule block)
      throws ValidateSignatureException, ContractValidateException, ContractExeException,
      UnLinkedBlockException, ValidateScheduleException, AccountResourceInsufficientException,
      TaposException, TooBigTransactionException, TooBigTransactionResultException, DupTransactionException, TransactionExpirationException,
      BadNumberBlockException, BadBlockException, NonCommonBlockException,
      ReceiptCheckErrException, VMIllegalException, ZksnarkException {
    long start = System.currentTimeMillis();
    try (PendingManager pm = new PendingManager(this)) {

      if (!block.generatedByMyself) {
        if (!block.validateSignature(this.dynamicPropertiesStore, this.accountStore)) {
          logger.warn("The signature is not validated.");
          throw new BadBlockException("The signature is not validated");
        }

        if (!block.calcMerkleRoot().equals(block.getMerkleRoot())) {
          logger.warn(
              "The merkle root doesn't match, Calc result is "
                  + block.calcMerkleRoot()
                  + " , the headers is "
                  + block.getMerkleRoot());
          throw new BadBlockException("The merkle hash is not validated");
        }
      }

      if (block.getTransactions().stream().filter(tran -> isShieldedTransaction(tran.getInstance()))
          .count() > SHIELDED_TRANS_IN_BLOCK_COUNTS) {
        throw new BadBlockException(
            "shielded transaction count > " + SHIELDED_TRANS_IN_BLOCK_COUNTS);
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
                  + block.toString()
                  + ", new block:"
                  + newBlock.toString()
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
                  + block.toString()
                  + ", new block:"
                  + newBlock.toString()
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
        try (ISession tmpSession = revokingStore.buildSession()) {

          applyBlock(newBlock);
          tmpSession.commit();
          // if event subscribe is enabled, post block trigger to queue
          postBlockTrigger(newBlock);
        } catch (Throwable throwable) {
          logger.error(throwable.getMessage(), throwable);
          khaosDb.removeBlk(block.getBlockId());
          throw throwable;
        }
      }
      logger.info("save block: " + newBlock);
    }
    //clear ownerAddressSet
    synchronized (pushTransactionQueue) {
      if (CollectionUtils.isNotEmpty(ownerAddressSet)) {
        Set<String> result = new HashSet<>();
        for (TransactionCapsule transactionCapsule : repushTransactions) {
          filterOwnerAddress(transactionCapsule, result);
        }
        for (TransactionCapsule transactionCapsule : pushTransactionQueue) {
          filterOwnerAddress(transactionCapsule, result);
        }
        ownerAddressSet.clear();
        ownerAddressSet.addAll(result);
      }
    }
    logger.info("pushBlock block number:{}, cost/txs:{}/{}",
        block.getNum(),
        System.currentTimeMillis() - start,
        block.getTransactions().size());
  }