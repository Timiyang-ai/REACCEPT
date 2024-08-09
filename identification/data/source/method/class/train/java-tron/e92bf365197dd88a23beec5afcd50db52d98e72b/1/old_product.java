public AccountType getAccountType() {
    AccountType accountType;
    switch (this.accountType) {
      case "Normal":
        accountType = AccountType.Normal;
        break;
      case "AssetIssue":
        accountType = AccountType.AssetIssue;
        break;
      case "Contract":
        accountType = AccountType.Contract;
        break;
      default:
        throw new IllegalArgumentException("Account type error: Not Normal/AssetIssue/Contract");
    }

    return accountType;
  }