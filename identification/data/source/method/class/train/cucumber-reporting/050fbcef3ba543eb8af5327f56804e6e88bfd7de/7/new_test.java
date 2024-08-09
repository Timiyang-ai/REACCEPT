    @Test
    public void generateReports_GeneratesPages() {

        // given
        List<String> jsonReports = Arrays.asList(ReportGenerator.reportFromResource(ReportGenerator.SAMPLE_JSON));

        Configuration configuration = new Configuration(reportDirectory, "myProject");
        ReportBuilder builder = new ReportBuilder(jsonReports, configuration);

        // when
        Reportable result = builder.generateReports();

        // then
        assertThat(countHtmlFiles()).hasSize(9);
        assertThat(result).isNotNull();
    }