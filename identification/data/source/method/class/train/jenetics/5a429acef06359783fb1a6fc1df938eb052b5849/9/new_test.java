	@Test
	public void newInstance() {
		final Genotype<DoubleGene> gt1 = Genotype.of(
			//Rotation
			DoubleChromosome.of(DoubleGene.of(-Math.PI, Math.PI)),

			//Translation
			DoubleChromosome.of(DoubleGene.of(-300, 300), DoubleGene.of(-300, 300)),

			//Shear
			DoubleChromosome.of(DoubleGene.of(-0.5, 0.5), DoubleGene.of(-0.5, 0.5))
		);

		final Genotype<DoubleGene> gt2 = gt1.newInstance();

		Assert.assertEquals(gt1.length(), gt2.length());
		for (int i = 0; i < gt1.length(); ++i) {
			Chromosome<DoubleGene> ch1 = gt1.getChromosome(i);
			Chromosome<DoubleGene> ch2 = gt2.getChromosome(i);
			Assert.assertEquals(ch1.length(), ch2.length());
		}
	}