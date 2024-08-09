@Test(groups = { "deferredEvent", "broken" })
   public void testDeferEvent()
   {
      // Setup a transaction manager for this test and inject into the event bus
      // TransactionManager tm = new TransactionManager() {
      // public void begin() throws NotSupportedException, SystemException
      // {
      // }
      //
      // public void commit() throws RollbackException,
      // HeuristicMixedException, HeuristicRollbackException,
      // SecurityException, IllegalStateException, SystemException
      // {
      // }
      //
      // public int getStatus() throws SystemException
      // {
      // return 0;
      // }
      //
      // public Transaction getTransaction() throws SystemException
      // {
      // return new Transaction() {
      //               
      // public void commit() throws RollbackException,
      // HeuristicMixedException, HeuristicRollbackException,
      // SecurityException, IllegalStateException, SystemException
      // {
      // }
      //
      // public boolean delistResource(XAResource arg0, int arg1)
      // throws IllegalStateException, SystemException
      // {
      // return false;
      // }
      //
      // public boolean enlistResource(XAResource arg0)
      // throws RollbackException, IllegalStateException,
      // SystemException
      // {
      // return false;
      // }
      //
      // public int getStatus() throws SystemException
      // {
      // return 0;
      // }
      //
      // public void registerSynchronization(Synchronization synchronization)
      // throws RollbackException, IllegalStateException,
      // SystemException
      // {
      // registeredSynch = synchronization;
      // }
      //
      // public void rollback() throws IllegalStateException,
      // SystemException
      // {
      // }
      //
      // public void setRollbackOnly() throws IllegalStateException,
      // SystemException
      // {
      // }
      //               
      // };
      // }
      //
      // public void resume(Transaction arg0)
      // throws InvalidTransactionException, IllegalStateException,
      // SystemException
      // {
      // }
      //
      // public void rollback() throws IllegalStateException,
      // SecurityException, SystemException
      // {
      // }
      //
      // public void setRollbackOnly() throws IllegalStateException,
      // SystemException
      // {
      // }
      //
      // public void setTransactionTimeout(int arg0) throws SystemException
      // {
      // }
      //
      // public Transaction suspend() throws SystemException
      // {
      // return null;
      // }
      //         
      // };
//      EventManager eventManager = new EventManager(manager);
//      Observer<DangerCall> observer = new AnObserver<DangerCall>();
//      try
//      {
//         eventManager.deferEvent(new DangerCall(), observer);
//      }
//      catch (Exception e)
//      {
//      }
//
//      assert this.registeredSynch != null;
//      assert ((DeferredEventNotification) this.registeredSynch).getObserver().equals(observer);
   }