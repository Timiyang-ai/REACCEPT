    @Test
    public void matching_description() {
        JavaClasses classes = importClassesWithContext(Object.class);

        Slices.Transformer transformer = Slices.matching("java.(*)..");
        assertThat(transformer.getDescription()).isEqualTo("slices matching 'java.(*)..'");

        Slices slices = transformer.transform(classes);
        assertThat(slices.getDescription()).isEqualTo("slices matching 'java.(*)..'");

        slices = transformer.that(DescribedPredicate.<Slice>alwaysTrue().as("changed")).transform(classes);
        assertThat(slices.getDescription()).isEqualTo("slices matching 'java.(*)..' that changed");
    }