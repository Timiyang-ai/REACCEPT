    @Test
    void deframe_noRequests() throws Exception {
        deframer.deframe(HttpData.wrap(GrpcTestUtil.uncompressedFrame(GrpcTestUtil.requestByteBuf())), false);
        assertThat(deframer.isStalled()).isFalse();
        verifyNoMoreInteractions(listener);

        deframer.request(1);
        verifyAndReleaseMessage(new DeframedMessage(GrpcTestUtil.requestByteBuf(), 0));
        assertThat(deframer.isStalled()).isTrue();
        verifyNoMoreInteractions(listener);
    }