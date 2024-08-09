	@Test
	public void crossover() {
		final CharSeq chars = CharSeq.of("a-zA-Z");

		final ISeq<CharacterGene> g1 = CharacterChromosome.of(chars, 20).toSeq();
		final ISeq<CharacterGene> g2 = CharacterChromosome.of(chars, 20).toSeq();

		final int rv1 = 12;
		using(new ConstRandom(rv1), r -> {
			final SinglePointCrossover<CharacterGene, Double>
			crossover = new SinglePointCrossover<>();

			MSeq<CharacterGene> g1c = g1.copy();
			MSeq<CharacterGene> g2c = g2.copy();
			crossover.crossover(g1c, g2c);

			Assert.assertEquals(g1c.subSeq(0, rv1), g1.subSeq(0, rv1));
			Assert.assertEquals(g1c.subSeq(rv1), g2.subSeq(rv1));
			Assert.assertNotEquals(g1c, g2);
			Assert.assertNotEquals(g2c, g1);

			final int rv2 = 0;
			using(new ConstRandom(rv2), r2 -> {
				MSeq<CharacterGene> g1c2 = g1.copy();
				MSeq<CharacterGene> g2c2 = g2.copy();
				crossover.crossover(g1c2, g2c2);
				Assert.assertEquals(g1c2, g2);
				Assert.assertEquals(g2c2, g1);
				Assert.assertEquals(g1c2.subSeq(0, rv2), g1.subSeq(0, rv2));
				Assert.assertEquals(g1c2.subSeq(rv2), g2.subSeq(rv2));

				final int rv3 = 1;
				using(new ConstRandom(rv3), r3 -> {
					MSeq<CharacterGene> g1c3 = g1.copy();
					MSeq<CharacterGene> g2c3 = g2.copy();
					crossover.crossover(g1c3, g2c3);
					Assert.assertEquals(g1c3.subSeq(0, rv3), g1.subSeq(0, rv3));
					Assert.assertEquals(g1c3.subSeq(rv3), g2.subSeq(rv3));

					final int rv4 = g1.length();
					using(new ConstRandom(rv4), r4 -> {
						MSeq<CharacterGene> g1c4 = g1.copy();
						MSeq<CharacterGene> g2c4 = g2.copy();
						crossover.crossover(g1c4, g2c);
						Assert.assertEquals(g1c4, g1);
						Assert.assertEquals(g2c4, g2);
						Assert.assertEquals(g1c4.subSeq(0, rv4), g1.subSeq(0, rv4));
						Assert.assertEquals(g1c4.subSeq(rv4), g2.subSeq(rv4));
					});
				});
			});
		});
	}