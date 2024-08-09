public void init() throws ContractValidateException {
    List<Contract> contractList = trx.getInstance().getRawData().getContractList();
    long castMaxTrx = 0;
    for (Contract contract : contractList) {
//      castMaxTrx = Math.addExact(castMaxTrx, TransactionCapsule.getCpuLimitInTrx(contract));
//      castMaxTrx = Math.addExact(castMaxTrx, TransactionCapsule.getStorageLimitInTrx(contract));
//      castMaxTrx = Math.addExact(castMaxTrx, TransactionCapsule.getCallValue(contract));
    }
    if (owner.getBalance() < castMaxTrx) {
      throw new ContractValidateException(
          "init trace error, balance is not sufficient.");
    }
//    receipt.payCpuBill();
    checkStorage();
  }