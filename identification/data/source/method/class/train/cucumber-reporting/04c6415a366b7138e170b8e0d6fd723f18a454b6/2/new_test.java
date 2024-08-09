    @Test
    public void toValidFileName_RemovesInvalidChars() {

        // given
        final String[] ids = {"simpleFile", "file-dash", "東京", "żółć"};
        final String[] hashes = {"715485773", "784542018", "2148324698", "2159047995"};

        // when & then
        for (int i = 0; i < ids.length; i++) {
            assertThat(Util.toValidFileName(ids[i])).isEqualTo(hashes[i]);
        }
    }