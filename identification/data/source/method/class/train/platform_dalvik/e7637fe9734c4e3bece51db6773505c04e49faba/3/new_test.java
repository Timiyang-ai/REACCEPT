@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "toString",
        args = {}
    )
    public final void testToString() throws NoSuchAlgorithmException {
        for (int k=0; k<algorithmName.length; k++) {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream(MY_MESSAGE_LEN);
                MessageDigest md = MessageDigest.getInstance(algorithmName[k]);
                DigestOutputStream dos = new DigestOutputStream(bos, md);

                assertNotNull(dos.toString());
                return;
            } catch (NoSuchAlgorithmException e) {
                // allowed failure
            }
        }
        fail(getName() + ": no MessageDigest algorithms available - test not performed");
    }