public void init(BlockCapsule blockCap) throws VMIllegalException, ContractValidateException {
    txStartTimeInMs = System.currentTimeMillis();
    DepositImpl deposit = DepositImpl.createRoot(dbManager);
    runtime = new Runtime(this, blockCap, deposit, new ProgramInvokeFactoryImpl());
    if (runtime.isCallConstant()) {
      throw new VMIllegalException("cannot call constant method ");
    }
    // switch (trxType) {
    //   case TRX_PRECOMPILED_TYPE:
    //     break;
    //   case TRX_CONTRACT_CREATION_TYPE:
    //   case TRX_CONTRACT_CALL_TYPE:
    //     // checkForSmartContract();
    //     break;
    //   default:
    //     break;
    // }

  }