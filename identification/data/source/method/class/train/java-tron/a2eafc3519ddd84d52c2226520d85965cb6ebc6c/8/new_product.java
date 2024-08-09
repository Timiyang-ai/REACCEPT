public void pushBlock(final BlockCapsule block)
      throws ValidateSignatureException, ContractValidateException, ContractExeException, UnLinkedBlockException {
    boolean useKhaoDB = true;
    BlockCapsule newBlock = this.khaosDb.push(block);
    //todo: check block's validity
    if (!block.generatedByMyself) {
      if (!block.validateSignature()) {
        logger.info("The siganature is not validated.");
        return;
      }

      if (!block.calcMerkleRoot().equals(block.getMerkleRoot())) {
        logger.info("The merkler root doesn't match, Calc result is " + block.calcMerkleRoot()
            + " , the headers is " + block.getMerkleRoot());
        return;
      }

      //todo: In some case it need to switch the branch
    }
    if (useKhaoDB && block.getNum() != 0 && head == null) {
      if (!Objects.equals(newBlock.getParentHash(), head.getBlockId())) {
        if (newBlock.getNum() > head.getNum()) {
          Pair<LinkedList<BlockCapsule>, LinkedList<BlockCapsule>> binaryTree = khaosDb
              .getBranch(newBlock.getBlockId(), head.getBlockId());

          while (!head.getBlockId().equals(binaryTree.getValue().peekLast().getParentHash())) {
            eraseBlock();
          }

          LinkedList<BlockCapsule> branch = binaryTree.getKey();
          Collections.reverse(branch);
          branch.forEach(item -> {
            // todo  process the exception carefully later
            try (Dialog tmpDialog = revokingStore.buildDialog()) {
              processBlock(item);
              tmpDialog.commit();
              head = item;
              getDynamicPropertiesStore()
                  .saveLatestBlockHeaderHash(head.getBlockId().getByteString());
              getDynamicPropertiesStore().saveLatestBlockHeaderNumber(head.getNum());
              getDynamicPropertiesStore().saveLatestBlockHeaderTimestamp(head.getTimeStamp());
            } catch (ValidateSignatureException e) {
              e.printStackTrace();
            } catch (ContractValidateException e) {
              e.printStackTrace();
            } catch (ContractExeException e) {
              e.printStackTrace();
            } catch (RevokingStoreIllegalStateException e) {
              e.printStackTrace();
            }
          });
          return;
        } else {
          // todo the error status
          return;
        }
      }
    }

    if (block.getNum() != 0) {
      try (Dialog tmpDialog = revokingStore.buildDialog()) {
        this.processBlock(block);

        tmpDialog.commit();
      } catch (RevokingStoreIllegalStateException e) {
        e.printStackTrace();
      }
    }

    //refresh
    refreshHead(block);

    this.getBlockStore().dbSource.putData(block.getBlockId().getBytes(), block.getData());
    logger.info("save block, Its ID is " + block.getBlockId() + ", Its num is " + block.getNum());
    this.numHashCache.putData(ByteArray.fromLong(block.getNum()), block.getBlockId().getBytes());
    // todo modify the dynamic head
    //this.head = this.khaosDb.getHead();
    // blockDbDataSource.putData(blockHash, blockData);
  }