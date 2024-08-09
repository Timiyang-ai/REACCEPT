    @Test
    public void pidCheck() throws ConfigurationException, IOException {
        Assume.assumeTrue(SystemUtils.IS_OS_LINUX);
        FileUtils.writeStringToFile(pidFile, "123456\n");
        ProcessUtil.pidCheck(pidFile.getParent(), pidFile.getName());
        String pidStr = FileUtils.readFileToString(pidFile);
        Assert.assertFalse("pid can not be blank", pidStr.isEmpty());
        int pid = Integer.parseInt(pidStr.trim());
        int maxPid = Integer.parseInt(FileUtils.readFileToString(new File("/proc/sys/kernel/pid_max")).trim());
        Assert.assertTrue(pid <= maxPid);
    }