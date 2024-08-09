	@Test
	public void error() {
		final Codec<Tree<Op<Double>, ?>, ProgramGene<Double>> codec =
			Regression.codecOf(OPS, TMS, 5, t -> t.getGene().size() < 30);

		final Regression<Double> regression = Regression.of(
			codec,
			Error.of(LossFunction::mse),
			Sample.ofDouble(-1.0, -8.0000),
			Sample.ofDouble(-0.9, -6.2460),
			Sample.ofDouble(-0.8, -4.7680),
			Sample.ofDouble(-0.7, -3.5420),
			Sample.ofDouble(-0.6, -2.5440),
			Sample.ofDouble(-0.5, -1.7500),
			Sample.ofDouble(-0.4, -1.1360),
			Sample.ofDouble(-0.3, -0.6780),
			Sample.ofDouble(-0.2, -0.3520),
			Sample.ofDouble(-0.1, -0.1340),
			Sample.ofDouble(0.0, 0.0000),
			Sample.ofDouble(0.1, 0.0740),
			Sample.ofDouble(0.2, 0.1120),
			Sample.ofDouble(0.3, 0.1380),
			Sample.ofDouble(0.4, 0.1760),
			Sample.ofDouble(0.5, 0.2500),
			Sample.ofDouble(0.6, 0.3840),
			Sample.ofDouble(0.7, 0.6020),
			Sample.ofDouble(0.8, 0.9280),
			Sample.ofDouble(0.9, 1.3860),
			Sample.ofDouble(1.0, 2.0000)
		);

		final Tree<Op<Double>, ?> tree = codec.encoding().newInstance().getGene();
		regression.error(tree);
	}