@TestTargetNew(
        level = TestLevel.SUFFICIENT,
        notes = "IOException is not checked.",
        method = "start",
        args = {}
    )
    @SuppressWarnings("nls")
    public void testStart() throws IOException {
        String cmd = "Dalvik".equals(System.getProperty("java.vm.name")) ?
                "dalvikvm" : "java";
        ProcessBuilder pb = new ProcessBuilder(cmd, "-version");

        // Call the test target
        Process process = pb.start();
        InputStream in = process.getInputStream();
        InputStream err = process.getErrorStream();

        while (true) {
            try {
                process.waitFor();
                break;
            } catch (InterruptedException e) {
                // Ignored
            }
        }

        byte[] buf = new byte[1024];
        if (in.available() > 0) {
            assertTrue(in.read(buf) > 0);
        } else {
            assertTrue(err.read(buf) > 0);
        }
        
        List<String> list = Arrays.asList(null, null, null);
        ProcessBuilder pbn = new ProcessBuilder(list);
        try {
            pbn.start();
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
        
        List<String> emptyList = Arrays.asList();
        ProcessBuilder pbe = new ProcessBuilder(emptyList);
        try {
            pbe.start();
            fail("IndexOutOfBoundsException  is not thrown.");
        } catch(IndexOutOfBoundsException npe) {
            //expected
        }
        
        SecurityManager sm = new SecurityManager() {

            public void checkPermission(Permission perm) {
            }
            
            public void checkExec(String cmd) {
                throw new SecurityException(); 
            }
        };

        SecurityManager oldSm = System.getSecurityManager();
        System.setSecurityManager(sm);
        try {
            pb.start();
            fail("SecurityException should be thrown.");
        } catch (SecurityException e) {
            // expected
        } finally {
            System.setSecurityManager(oldSm);
        }      
        
        pb.directory(new File(System.getProperty("java.class.path")));
    }