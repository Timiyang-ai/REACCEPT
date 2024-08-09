    @Test(expected = IllegalArgumentException.class)
    public void validateAndGetArrayQuantifierFromCurrentToken_malformed() {
        PortableUtils.validateAndGetArrayQuantifierFromCurrentToken("legs[", "person.legs[0]");
    }