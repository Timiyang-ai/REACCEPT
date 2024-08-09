    @Test
    public void sealSegment() {
        MockConnectionFactory factory = new MockConnectionFactory();
        SegmentHelper helper = new SegmentHelper(factory, new MockHostControllerStore());
        CompletableFuture<Void> retVal = helper.sealSegment("", "", 0L,
                "", System.nanoTime());
        long requestId = ((MockConnection) (factory.connection)).getRequestId();
        factory.rp.process(new WireCommands.AuthTokenCheckFailed(requestId, "SomeException"));
        AssertExtensions.assertThrows("",
                () -> retVal.join(),
                ex -> Exceptions.unwrap(ex) instanceof WireCommandFailedException
                        && ((WireCommandFailedException) ex).getReason().equals(WireCommandFailedException.Reason.AuthFailed)
        );

        CompletableFuture<Void> result = helper.sealSegment("", "", 0L,
                "", System.nanoTime());
        requestId = ((MockConnection) (factory.connection)).getRequestId();
        factory.rp.process(new WireCommands.SegmentSealed(requestId, getQualifiedStreamSegmentName("", "", 0L)));
        result.join();

        result = helper.sealSegment("", "", 0L,
                "", System.nanoTime());
        requestId = ((MockConnection) (factory.connection)).getRequestId();
        factory.rp.process(new WireCommands.SegmentIsSealed(requestId, getQualifiedStreamSegmentName("", "", 0L), "", 0L));
        result.join();

        Supplier<CompletableFuture<?>> futureSupplier = () -> helper.sealSegment("", "", 0L,
                "", System.nanoTime());
        validateProcessingFailureCFE(factory, futureSupplier);

        testConnectionFailure(factory, futureSupplier);
    }