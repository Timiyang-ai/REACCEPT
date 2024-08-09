@Override public <K, V> KMeansModel fit(DatasetBuilder<K, V> datasetBuilder,
        IgniteBiFunction<K, V, Vector> featureExtractor, IgniteBiFunction<K, V, Double> lbExtractor) {
        assert datasetBuilder != null;

        PartitionDataBuilder<K, V, EmptyContext, LabeledDataset<Double, LabeledVector>> partDataBuilder = new LabeledDatasetPartitionDataBuilderOnHeap<>(
            featureExtractor,
            lbExtractor
        );

        Vector[] centers;

        try (Dataset<EmptyContext, LabeledDataset<Double, LabeledVector>> dataset = datasetBuilder.build(
            (upstream, upstreamSize) -> new EmptyContext(),
            partDataBuilder
        )) {
            final int cols = dataset.compute(org.apache.ignite.ml.structures.Dataset::colSize, (a, b) -> a == null ? b : a);
            centers = initClusterCentersRandomly(dataset, k);

            boolean converged = false;
            int iteration = 0;

            while (iteration < maxIterations && !converged) {
                Vector[] newCentroids = new DenseLocalOnHeapVector[k];

                TotalCostAndCounts totalRes = calcDataForNewCentroids(centers, dataset, cols);

                converged = true;

                for (Integer ind : totalRes.sums.keySet()) {
                    Vector massCenter = totalRes.sums.get(ind).times(1.0 / totalRes.counts.get(ind));

                    if (converged && distance.compute(massCenter, centers[ind]) > epsilon * epsilon)
                        converged = false;

                    newCentroids[ind] = massCenter;
                }

                iteration++;
                centers = newCentroids;
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new KMeansModel(centers, distance);
    }