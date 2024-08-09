@Test
    public void handleTask_anyOrder() throws Exception {
        String[] args = new String[] { task.getTaskName(),
                                       "--validity=" + VALIDITY,
                                       "--password=" + PLAINTEXT,
                                       "--subject=" + SUBJECT_DN,
                                       "--server=" + SERVER_NAME,
                                       "--passwordEncoding=xor" };

        createSuccessfulStdoutExpectations(SUBJECT_DN);
        mock.checking(new Expectations() {
            {
                one(fileUtil).exists(EXPECTED_SERVER_DIR);
                will(returnValue(true));
                one(fileUtil).resolvePath(EXPECTED_KEYSTORE_FILE);
                will(returnValue(EXPECTED_KEYSTORE_PATH));
                one(fileUtil).createParentDirectory(with(stdout), with(matchingFile(EXPECTED_KEYSTORE_FILE)));
                will(returnValue(true));
                one(creator).createDefaultSSLCertificate(EXPECTED_KEYSTORE_PATH,
                                                         PLAINTEXT,
                                                         Integer.valueOf(VALIDITY),
                                                         SUBJECT_DN,
                                                         DefaultSSLCertificateCreator.DEFAULT_SIZE,
                                                         DefaultSSLCertificateCreator.SIGALG,
                                                         null);
            }
        });

        task.handleTask(stdin, stdout, stderr, args);
    }