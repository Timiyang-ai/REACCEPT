@Test
    public void testInitializeClientHelloExtension() {
        MaxFragmentLengthExtensionMessage initializedMessage;
        MaxFragmentLengthExtensionHandler maxFragmentHandlerInitialised = MaxFragmentLengthExtensionHandler
                .getInstance();
        initializedMessage = new MaxFragmentLengthExtensionMessage();
        initializedMessage.setMaxFragmentLengthConfig(MaxFragmentLength.TWO_12);
        maxFragmentHandlerInitialised.initializeClientHelloExtension(initializedMessage);

        Assert.assertArrayEquals(
                "Tests if the extension bytes are set correctly by the initializeClientHelloExtension method",
                extensionMessage, initializedMessage.getExtensionBytes().getValue());
        Assert.assertEquals(
                "Tests if the extension length is set correctly by the initializeClientHelloExtension method",
                new Integer(1), initializedMessage.getExtensionLength().getValue());
        Assert.assertArrayEquals(
                "Tests if the extension type method is set correctly by the initializeClientHelloExtension method",
                ExtensionType.MAX_FRAGMENT_LENGTH.getValue(), initializedMessage.getExtensionType().getValue());
        Assert.assertArrayEquals(
                "Tests if the max fragment length is set correctly by the initializeClientHelloExtension method",
                MaxFragmentLength.TWO_12.getArrayValue(), initializedMessage.getMaxFragmentLength().getValue());

    }