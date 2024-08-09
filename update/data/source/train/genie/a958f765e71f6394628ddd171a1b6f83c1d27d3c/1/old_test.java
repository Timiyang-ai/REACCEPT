@Test
    public void testGetIndex() throws Exception {

        final byte[] indexResourceBytes;
        InputStream is = null;

        try {
            is = UIController.class.getResourceAsStream("/static/index.html");
            Assert.assertNotNull(is);
            Assert.assertTrue(is.available() > 0);
            indexResourceBytes = IOUtils.toByteArray(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }

        this.mvc
            .perform(MockMvcRequestBuilders.get("/index.html"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
            .andExpect(MockMvcResultMatchers.content().encoding(StandardCharsets.UTF_8.name()))
            .andExpect(MockMvcResultMatchers.content().bytes(indexResourceBytes))
            .andReturn();
    }