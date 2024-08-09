@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "resume",
        args = {}
    )
    @AndroidOnly("Android throws UnsupportedOperationException for Thread.resume()")
    @SuppressWarnings("deprecation")
    public void test_resume() {
        // Test for method void java.lang.Thread.resume()
        int orgval;
        ResSupThread t;
        try {
            t = new ResSupThread(Thread.currentThread());
            synchronized (t) {
                ct = new Thread(t, "Interrupt Test2");
                ct.start();
                t.wait();
            }
            try {
                ct.resume();
            } catch (UnsupportedOperationException e) {
                // expected
            }
        } catch (InterruptedException e) {
            fail("Unexpected interrupt occurred : " + e.getMessage());
        }

        // Security checks are made even though resume() is not supported.
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
            st.resume();
            fail("Should throw SecurityException");
        } catch (SecurityException e) {
            // expected
        } finally {
            System.setSecurityManager(oldSm);
        }        
    }