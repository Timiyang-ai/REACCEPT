    @Test
    public void setSortingMethod_SetsSortingMethod() {

        // given
        Configuration configuration = new Configuration(outputDirectory, projectName);
        SortingMethod sortingMethod = SortingMethod.NATURAL;

        // then
        configuration.setSortingMethod(sortingMethod);

        // then
        assertThat(configuration.getSortingMethod()).isEqualTo(sortingMethod);
    }