@Test
    public void createApplication() throws Exception {
        final Application app = new Application.Builder("spark", "genieUser", "1.5.1", ApplicationStatus.ACTIVE)
            .withDependencies(Sets.newHashSet("s3://mybucket/spark/spark-1.5.1.tar.gz"))
            .withSetupFile("s3://mybucket/spark/setup-spark.sh")
            .withConfigs(Sets.newHashSet("s3://mybucket/spark/spark-env.sh"))
            .withDescription("Spark 1.5.1 for Genie")
            .withTags(Sets.newHashSet("type:spark", "ver:1.5.1"))
            .build();

        this.document.snippets(
            PayloadDocumentation.requestFields(this.getApplicationFieldDescriptors(false))
            //TODO: Add header documentation once RESTDocs supports it (in snapshot right now 10/5/15)
        );

        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(APPLICATION_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(app))
            )
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers
                .header()
                .string(HttpHeaders.LOCATION, Matchers.is(Matchers.notNullValue()))
            )
            .andDo(this.document);
    }