    @Test
    public void revokeContract() throws Exception {
        String contractFileName = basePath + "contract_for_revoke3.unicon";

        String uContract = getApprovedUContract();
        callMain2("--register", contractFileName, "--verbose",
                "--u", uContract,
                "--k", rootPath + "keys/stepan_mamontov.private.unikey");

        Contract c = CLIMain.loadContract(contractFileName);
        System.out.println("contract: " + c.getId().toBase64String());

        Thread.sleep(1500);
        System.out.println("probe before revoke");
        callMain2("--probe", c.getId().toBase64String(), "--verbose");
        Thread.sleep(500);

        uContract = getApprovedUContract();
        callMain2("-revoke", contractFileName,
                "-k", PRIVATE_KEY_PATH, "-v",
                "--u", uContract,
                "-k", rootPath + "keys/stepan_mamontov.private.unikey");
        Thread.sleep(2500);
        System.out.println("probe after revoke");
        callMain("--probe", c.getId().toBase64String(), "--verbose");

        System.out.println(output);
        assertEquals(0, errors.size());

        assertTrue (output.indexOf(ItemState.REVOKED.name()) >= 0);
    }