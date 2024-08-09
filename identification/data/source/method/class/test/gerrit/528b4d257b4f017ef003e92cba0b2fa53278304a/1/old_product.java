@VisibleForTesting
  static void checkResults(BatchRefUpdate bru) throws IOException {
    int lockFailure = 0;
    int aborted = 0;
    int failure = 0;

    for (ReceiveCommand cmd : bru.getCommands()) {
      if (cmd.getResult() != ReceiveCommand.Result.OK) {
        failure++;
      }
      if (cmd.getResult() == ReceiveCommand.Result.LOCK_FAILURE) {
        lockFailure++;
      } else if (cmd.getResult() == ReceiveCommand.Result.REJECTED_OTHER_REASON
          && JGitText.get().transactionAborted.equals(cmd.getMessage())) {
        aborted++;
      }
    }

    if (lockFailure + aborted == bru.getCommands().size()) {
      throw new LockFailureException("Update aborted with one or more lock failures: " + bru);
    } else if (failure > 0) {
      throw new IOException("Update failed: " + bru);
    }
  }