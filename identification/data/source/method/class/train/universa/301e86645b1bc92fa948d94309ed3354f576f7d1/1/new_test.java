    @Test
    public void exportPublicKeys() throws Exception {
        String role = "owner";
        callMain(
                "-e", basePath + "contract_to_export.unicon", "--extract-key", role);
        System.out.println(output);
        assertTrue (output.indexOf(role + " export public keys ok") >= 0);
        assertEquals(0, errors.size());
    }