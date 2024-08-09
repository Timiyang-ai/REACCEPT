public void pushBlock(final BlockCapsule block)
      throws ValidateSignatureException, ContractValidateException, ContractExeException, UnLinkedBlockException {
    boolean useKhaoDB = false;
    BlockCapsule newBlock = this.khaosDb.push(block);

    //DB don't need lower block
    if (newBlock.getNum() <= head.getNum()) {
      return;
    }

    try (Dialog tmpDialog = revokingStore.buildDialog()) {
      this.processBlock(newBlock);
      tmpDialog.commit();
    } catch (RevokingStoreIllegalStateException e) {
      e.printStackTrace();
    }

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
    if (useKhaoDB) {
      if (!newBlock.getParentHash().equals(head.getBlockId())) {
        if (newBlock.getNum() > head.getNum()) {
          Pair<LinkedList<BlockCapsule>, LinkedList<BlockCapsule>> binaryTree = khaosDb
              .getBranch(newBlock.getBlockId(), head.getBlockId());

          while (!head.getBlockId().equals(binaryTree.getValue().pollLast().getBlockId())) {
            eraseBlock();
          }
          LinkedList<BlockCapsule> branch = binaryTree.getValue();
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

    this.getBlockStore().dbSource.putData(block.getBlockId().getBytes(), block.getData());
    this.numHashCache.putData(ByteArray.fromLong(block.getNum()), block.getBlockId().getBytes());
    logger.info("save block: " + newBlock);
  }