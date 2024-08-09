@Test
        public void testUpdate() {
            UpdatesStrategy<SmoothParametrized, SimpleGDParameterUpdate> updatesStgy = new UpdatesStrategy<>(
                new SimpleGDUpdateCalculator(0.2),
                SimpleGDParameterUpdate::sumLocal,
                SimpleGDParameterUpdate::avg
            );

            Map<Integer, double[][]> xorData = new HashMap<>();
            xorData.put(0, new double[][]{{0.0, 0.0}, {0.0}});
            xorData.put(1, new double[][]{{0.0, 1.0}, {1.0}});
            xorData.put(2, new double[][]{{1.0, 0.0}, {1.0}});
            xorData.put(3, new double[][]{{1.0, 1.0}, {0.0}});

            MLPArchitecture arch = new MLPArchitecture(2).
                withAddedLayer(10, true, Activators.RELU).
                withAddedLayer(1, false, Activators.SIGMOID);

            MLPTrainer<SimpleGDParameterUpdate> trainer = new MLPTrainer<>(
                arch,
                LossFunctions.MSE,
                updatesStgy,
                3000,
                batchSize,
                50,
                123L
            );

            MultilayerPerceptron originalMdl = trainer.fit(
                xorData,
                parts,
                (k, v) -> VectorUtils.of(v[0]),
                (k, v) -> v[1]
            );

            MultilayerPerceptron updatedOnSameDS = trainer.update(
                originalMdl,
                xorData,
                parts,
                (k, v) -> VectorUtils.of(v[0]),
                (k, v) -> v[1]
            );

            MultilayerPerceptron updatedOnEmptyDS = trainer.update(
                originalMdl,
                new HashMap<Integer, double[][]>(),
                parts,
                (k, v) -> VectorUtils.of(v[0]),
                (k, v) -> v[1]
            );

            DenseMatrix matrix = new DenseMatrix(new double[][] {
                {0.0, 0.0},
                {0.0, 1.0},
                {1.0, 0.0},
                {1.0, 1.0}
            });

            TestUtils.checkIsInEpsilonNeighbourhood(originalMdl.predict(matrix).getRow(0), updatedOnSameDS.predict(matrix).getRow(0), 1E-1);
            TestUtils.checkIsInEpsilonNeighbourhood(originalMdl.predict(matrix).getRow(0), updatedOnEmptyDS.predict(matrix).getRow(0), 1E-1);
        }