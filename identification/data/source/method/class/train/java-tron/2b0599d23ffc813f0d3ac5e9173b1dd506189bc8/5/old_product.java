public void init() throws TransactionTraceException {

    switch (trxType) {
      case TRX_PRECOMPILED_TYPE:
        break;
      case TRX_CONTRACT_CREATION_TYPE:
      case TRX_CONTRACT_CALL_TYPE:
        checkForSmartContract();
        break;
      default:
        break;
    }

  }