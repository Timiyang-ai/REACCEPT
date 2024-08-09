@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "stop",
        args = {}
    )
    @AndroidOnly("Android throws UnsupportedOperationException for Thread.stop()")
    @SuppressWarnings("deprecation")
    public void test_stop() {
        // Test for method void java.lang.Thread.stop()
        try {
            Runnable r = new ResSupThread(null);
            synchronized (r) {
                st = new Thread(r, "Interupt Test5");
                st.start();
                r.wait();
            }

        } catch (InterruptedException e) {
            fail("Unexpected interrupt received");
        }
        try {
            st.stop();
            fail("Expected UnsupportedOperationException because" +
                    "Thread.stop is not supported.");
        } catch (UnsupportedOperationException e) {
            // expected
        }

        // Security checks are made even though stop() is not supported.
        SecurityManager sm = new SecurityManager() {
            public void checkPermission(Permission perm) {
                if(perm.getName().equals("stopThread")) {
                    throw new SecurityException(); 
                }
            }
        };
        st = new Thread();
        SecurityManager oldSm = System.getSecurityManager();
        System.setSecurityManager(sm);
        try {
            st.stop();
            fail("Should throw SecurityException");
        } catch (SecurityException e) {
            // expected
        } finally {
            System.setSecurityManager(oldSm);
        } 
    }