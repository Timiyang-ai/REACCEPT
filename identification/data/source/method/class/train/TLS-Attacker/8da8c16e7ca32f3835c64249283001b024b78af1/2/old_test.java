@Test
    public void testParseExtension() {
        MaxFragmentLengthExtensionHandler extensionHandler = MaxFragmentLengthExtensionHandler.getInstance();
        int returnedPointer = extensionHandler.parseExtension(extensionMessage, 0);
        MaxFragmentLengthExtensionMessage parsedMessage = (MaxFragmentLengthExtensionMessage) extensionHandler
                .getExtensionMessage();

        Assert.assertEquals("Tests the returned pointer of the parseExtension method", (int) 5, returnedPointer);
        Assert.assertArrayEquals("Tests if the parseExtension method creates the correct extension bytes",
                extensionMessage, parsedMessage.getExtensionBytes().getValue());
        Assert.assertEquals("Tests the extensionLength of the parseExtension method", new Integer(1), parsedMessage
                .getExtensionLength().getValue());
        Assert.assertArrayEquals("Tests if the extensionType is set correctly",
                ExtensionType.MAX_FRAGMENT_LENGTH.getValue(), parsedMessage.getExtensionType().getValue());
        Assert.assertArrayEquals("Tests if the MaxFragmentLength is set correctly",
                MaxFragmentLength.TWO_12.getArrayValue(), parsedMessage.getMaxFragmentLength().getValue());
    }