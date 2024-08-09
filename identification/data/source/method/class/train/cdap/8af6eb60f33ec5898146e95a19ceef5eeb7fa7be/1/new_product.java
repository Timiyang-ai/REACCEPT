public synchronized void delete(final ProgramId programId, final SchedulableProgramType programType,
                                  final String scheduleName) throws InterruptedException, TransactionFailureException {
    factory.createExecutor(ImmutableList.of((TransactionAware) table))
      .execute(new TransactionExecutor.Subroutine() {
        @Override
        public void apply() throws Exception {
          String rowKey = getRowKey(programId, programType, scheduleName);

          String versionLessRowKey = removeAppVersion(rowKey);
          if (versionLessRowKey != null) {
            table.delete(Bytes.toBytes(versionLessRowKey));
          }
          table.delete(Bytes.toBytes(rowKey));
        }
      });
  }