@Test
    public void testGetIndex() throws Exception {
        final String indexContent;

        try (final InputStream is = UIController.class.getResourceAsStream("/templates/index.html")) {
            Assert.assertNotNull(is);
            Assert.assertTrue(is.available() > 0);
            indexContent = IOUtils.toString(is, StandardCharsets.UTF_8);
        }

        final List<String> validPaths = Arrays.asList(
            "/",
            "/applications",
            "/clusters",
            "/commands",
            "/jobs",
            "/output/12345"
        );

        for (String validPath : validPaths) {
            this.mvc
                .perform(MockMvcRequestBuilders.get(validPath))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.content().encoding(StandardCharsets.UTF_8.name()))
                .andExpect(MockMvcResultMatchers.content().string(indexContent))
                .andReturn();
        }
    }