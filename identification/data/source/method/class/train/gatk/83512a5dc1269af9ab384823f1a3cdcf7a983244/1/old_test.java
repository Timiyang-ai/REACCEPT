@Test
    public void testInferSexGenotypes() {
        final SexGenotypeDataCollection result = genotyper.inferSexGenotypes();
        try {
            final SexGenotypeDataCollection expected = new SexGenotypeDataCollection(TEST_SEX_GENOTYPE_FILE);
            Assert.assertEquals(result, expected);
        } catch (final IOException ex) {
            throw new UserException.CouldNotReadInputFile("Could not read test resource files");
        }
    }