@Test
    public void testQueueStreamForComm() throws Exception {
        System.out.println("queueStream");

        String command = "command";
        Collection<String> commands = Arrays.asList(command, command);
        String port = "/some/port";
        int rate = 1234;

        File f = new File(tempDir,"gcodeFile");
        try {
            try (GcodeStreamWriter gsw = new GcodeStreamWriter(f)) {
                for (String i : commands) {
                    gsw.addLine("blah", command, null, -1);
                }
            }

            try (IGcodeStreamReader gsr = new GcodeStreamReader(f)) {
                openInstanceExpectUtility(port, rate, false);
                streamInstanceExpectUtility();

                // TODO Fix this
                // Making sure the commands get queued.
                mockCommunicator.queueStreamForComm(gsr);

                expect(expectLastCall()).times(1);

                replay(instance, mockCommunicator);

                // Open port, send some commands, make sure they are streamed.
                instance.openCommPort(getSettings().getConnectionDriver(), port, rate);
                instance.queueStream(gsr);
                instance.beginStreaming();
            }

            verify(mockCommunicator, instance);
        } finally {
            FileUtils.forceDelete(f);
        }
    }