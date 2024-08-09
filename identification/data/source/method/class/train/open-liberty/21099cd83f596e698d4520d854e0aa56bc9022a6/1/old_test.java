@Test
    public void handleTask_passwordAndDays() throws Exception {
        String[] args = new String[] { task.getTaskName(),
                                       "--server=" + SERVER_NAME,
                                       "--password=" + PLAINTEXT,
                                       "--validity=" + VALIDITY,
                                       "--passwordEncoding=xor" };

        createSuccessfulStdoutExpectations(GEN_SUBJECT_DN);
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
                                                         new DefaultSubjectDN(null, SERVER_NAME).getSubjectDN(),
                                                         DefaultSSLCertificateCreator.DEFAULT_SIZE,
                                                         DefaultSSLCertificateCreator.SIGALG);
            }
        });

        task.handleTask(stdin, stdout, stderr, args);
    }