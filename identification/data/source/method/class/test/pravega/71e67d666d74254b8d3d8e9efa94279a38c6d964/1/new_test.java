    @Test
    public void checkPassword() throws InvalidKeySpecException, NoSuchAlgorithmException {
        StrongPasswordProcessor processor = StrongPasswordProcessor.builder().iterations(5000).build();
        String encrypted = processor.encryptPassword("1111_aaaa");
        StrongPasswordProcessor secondProcessor = StrongPasswordProcessor.builder().iterations(5000).build();

        secondProcessor.checkPassword("1111_aaaa".toCharArray(), encrypted);

        StrongPasswordProcessor failingProcessor = StrongPasswordProcessor.builder().iterations(1000).build();
        Assert.assertTrue("Passwords with different iterations should not match",
                !encrypted.equals(failingProcessor.encryptPassword("1111_aaaa")));
    }