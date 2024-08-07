    @Test
    public void test_connectToDevice() throws Exception {
        SecurityTokenConnection securityTokenConnection =
                new SecurityTokenConnection(transport, new Passphrase("123456"), new OpenPgpCommandApduFactory());
        expect("00a4040006d27600012401", "9000"); // select openpgp applet
        expect("00ca006e00",
                "6e81de4f10d27600012401020000060364311500005f520f0073000080000000000000000000007381b7c00af" +
                        "00000ff04c000ff00ffc106010800001103c206010800001103c306010800001103c407007f7f7f03030" +
                        "3c53c4ec5fee25c4e89654d58cad8492510a89d3c3d8468da7b24e15bfc624c6a792794f15b7599915f7" +
                        "03aab55ed25424d60b17026b7b06c6ad4b9be30a3c63c000000000000000000000000000000000000000" +
                        "000000000000000000000000000000000000000000000000000000000000000000000000000000000cd0" +
                        "c59cd0f2a59cd0af059cd0c959000"); // get application related data


        securityTokenConnection.connectToDevice(RuntimeEnvironment.application);


        verify(transport).connect();
        verifyDialog();
    }