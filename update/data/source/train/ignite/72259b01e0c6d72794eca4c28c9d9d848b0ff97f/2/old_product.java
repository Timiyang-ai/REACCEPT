public <K, V> MultilayerPerceptron fit(DatasetBuilder<K, V> datasetBuilder,
        IgniteBiFunction<K, V, double[]> featureExtractor, IgniteBiFunction<K, V, double[]> lbExtractor) {

        MultilayerPerceptron mdl = new MultilayerPerceptron(arch, new RandomInitializer(seed));
        ParameterUpdateCalculator<? super MultilayerPerceptron, P> updater = updatesStgy.getUpdatesCalculator();

        try (Dataset<EmptyContext, SimpleLabeledDatasetData> dataset = datasetBuilder.build(
            new EmptyContextBuilder<>(),
            new SimpleLabeledDatasetDataBuilder<>(featureExtractor, lbExtractor)
        )) {
            for (int i = 0; i < maxIterations; i += locIterations) {

                MultilayerPerceptron finalMdl = mdl;
                int finalI = i;

                List<P> totUp = dataset.compute(
                    data -> {
                        P update = updater.init(finalMdl, loss);

                        MultilayerPerceptron mlp = Utils.copy(finalMdl);

                        if (data.getFeatures() != null) {
                            List<P> updates = new ArrayList<>();

                            for (int locStep = 0; locStep < locIterations; locStep++) {
                                int[] rows = Utils.selectKDistinct(
                                    data.getRows(),
                                    Math.min(batchSize, data.getRows()),
                                    new Random(seed ^ (finalI * locStep))
                                );

                                double[] inputsBatch = batch(data.getFeatures(), rows, data.getRows());
                                double[] groundTruthBatch = batch(data.getLabels(), rows, data.getRows());

                                Matrix inputs = new DenseLocalOnHeapMatrix(inputsBatch, rows.length, 0);
                                Matrix groundTruth = new DenseLocalOnHeapMatrix(groundTruthBatch, rows.length, 0);

                                update = updater.calculateNewUpdate(
                                    mlp,
                                    update,
                                    locStep,
                                    inputs.transpose(),
                                    groundTruth.transpose()
                                );

                                mlp = updater.update(mlp, update);
                                updates.add(update);
                            }

                            List<P> res = new ArrayList<>();
                            res.add(updatesStgy.locStepUpdatesReducer().apply(updates));

                            return res;
                        }

                        return null;
                    },
                    (a, b) -> {
                        if (a == null)
                            return b;
                        else if (b == null)
                            return a;
                        else {
                            a.addAll(b);
                            return a;
                        }
                    }
                );

                P update = updatesStgy.allUpdatesReducer().apply(totUp);
                mdl = updater.update(mdl, update);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return mdl;
    }