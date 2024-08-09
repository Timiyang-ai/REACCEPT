@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "suspend",
        args = {}
    )
    @AndroidOnly("Android throws UnsupportedOperationException for Thread.suspend()")     
    @SuppressWarnings("deprecation")
    public void test_suspend() {
        // Test for method void java.lang.Thread.suspend()
        int orgval;
        ResSupThread t = new ResSupThread(Thread.currentThread());
        try {
            synchronized (t) {
                ct = new Thread(t, "Interupt Test6");
                ct.start();
                t.wait();
            }
            ct.suspend();
            fail("Expected UnsupportedOperationException because" +
                    "Thread.suspend is not supported.");
        } catch (UnsupportedOperationException e) {
            // expected
        } catch (InterruptedException e) {
            fail("Unexpected InterruptedException was thrown");
        }

        // Security checks are made even though suspend() is not supported.
        SecurityManager sm = new SecurityManager() {
            public void checkPermission(Permission perm) {
            }
            
            public void checkAccess(Thread t) {
                throw new SecurityException();
            }
        };
        st = new Thread();
        SecurityManager oldSm = System.getSecurityManager();
        System.setSecurityManager(sm);
        try {
            st.suspend();
            fail("Should throw SecurityException");
        } catch (SecurityException e) {
            // expected
        } finally {
            System.setSecurityManager(oldSm);
        }          
    }