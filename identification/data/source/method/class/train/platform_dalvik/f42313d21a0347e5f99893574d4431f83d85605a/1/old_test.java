@TestTargets({
        @TestTargetNew(
            level = TestLevel.COMPLETE,
            notes = "",
            method = "setDefaultUncaughtExceptionHandler",
            args = {java.lang.Thread.UncaughtExceptionHandler.class}
        ),
        @TestTargetNew(
            level = TestLevel.COMPLETE,
            notes = "",
            method = "getDefaultUncaughtExceptionHandler",
            args = {}
        )
    })
    public void test_get_setDefaultUncaughtExceptionHandler() {
        class Handler implements UncaughtExceptionHandler {
            public void uncaughtException(Thread thread, Throwable ex) {
            }
        }
        
        final Handler handler = new Handler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
        assertSame(handler, Thread.getDefaultUncaughtExceptionHandler());
        
        Thread.setDefaultUncaughtExceptionHandler(null);
        assertNull(Thread.getDefaultUncaughtExceptionHandler());
        //TODO add security-based tests
        
        SecurityManager sm = new SecurityManager() {

            public void checkPermission(Permission perm) {
                if(perm.getName().
                        equals("setDefaultUncaughtExceptionHandler")) {
                    throw new SecurityException();
                }
            }
        };

        SecurityManager oldSm = System.getSecurityManager();
        System.setSecurityManager(sm);
        try {
            st.setDefaultUncaughtExceptionHandler(handler);
            fail("Should throw SecurityException");
        } catch (SecurityException e) {
            // expected
        }  finally {
            System.setSecurityManager(oldSm);           
        }
    }