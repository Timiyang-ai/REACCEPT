public synchronized void delete(final Id.Program programId, final SchedulableProgramType programType,
                                  final String scheduleName) throws InterruptedException, TransactionFailureException {
    factory.createExecutor(ImmutableList.of((TransactionAware) table))
      .execute(new TransactionExecutor.Subroutine() {
        @Override
        public void apply() throws Exception {
          byte[] rowKey = Bytes.toBytes(String.format("%s:%s", KEY_PREFIX,
                                                      AbstractSchedulerService.scheduleIdFor(programId, programType,
                                                                                             scheduleName)));
          table.delete(rowKey);
        }
      });
  }