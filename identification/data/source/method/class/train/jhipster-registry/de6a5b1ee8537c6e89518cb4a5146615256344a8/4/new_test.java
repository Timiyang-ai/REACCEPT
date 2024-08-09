    @Test
    public void eurekaTest() throws Exception {

        // without key
        doReturn(null).when(ssh).getPublicKey();
        mock.perform(get("/api/ssh/public_key"))
            .andExpect(status().isNotFound());

        // with key
        doReturn("key").when(ssh).getPublicKey();
        mock.perform(get("/api/ssh/public_key"))
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
            .andExpect(content().string("key"))
            .andExpect(status().isOk());
    }