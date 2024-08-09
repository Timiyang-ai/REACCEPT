    @Test
    void toInputStream() throws Exception {
        assertThat(HttpData.EMPTY_DATA.toInputStream().read()).isEqualTo(-1);

        final InputStream in1 = HttpData.wrap(new byte[] { 1, 2, 3, 4 }).toInputStream();
        assertThat(in1.read()).isOne();
        assertThat(in1.read()).isEqualTo(2);
        assertThat(in1.read()).isEqualTo(3);
        assertThat(in1.read()).isEqualTo(4);
        assertThat(in1.read()).isEqualTo(-1);

        final InputStream in2 = HttpData.wrap(new byte[] { 1, 2, 3, 4 }, 1, 2).toInputStream();
        assertThat(in2.read()).isEqualTo(2);
        assertThat(in2.read()).isEqualTo(3);
        assertThat(in2.read()).isEqualTo(-1);

        final ByteBuf buf = Unpooled.copiedBuffer(new byte[] { 1, 2, 3, 4 });
        try {
            assertThat(buf.refCnt()).isOne();
            final HttpData data = HttpData.wrap(buf);
            try (InputStream in3 = data.toInputStream()) {
                assertThat(buf.refCnt()).isEqualTo(2);
                assertThat(in3.read()).isOne();
                assertThat(in3.read()).isEqualTo(2);
                assertThat(in3.read()).isEqualTo(3);
                assertThat(in3.read()).isEqualTo(4);
                assertThat(in3.read()).isEqualTo(-1);
            }
            assertThat(buf.refCnt()).isOne();
            // Can call toInputstream again
            try (InputStream in3 = data.toInputStream()) {
                assertThat(buf.refCnt()).isEqualTo(2);
                assertThat(in3.read()).isOne();
                assertThat(in3.read()).isEqualTo(2);
                assertThat(in3.read()).isEqualTo(3);
                assertThat(in3.read()).isEqualTo(4);
                assertThat(in3.read()).isEqualTo(-1);
            }
        } finally {
            buf.release();
        }
    }