static List<MeanWithClusterProbAggregator> map(GmmPartitionData data, int countOfComponents) {
        List<MeanWithClusterProbAggregator> aggregators = new ArrayList<>();
        for (int i = 0; i < countOfComponents; i++)
            aggregators.add(new MeanWithClusterProbAggregator());

        for (int i = 0; i < data.size(); i++) {
            for (int c = 0; c < countOfComponents; c++)
                aggregators.get(c).add(data.getX(i), data.pcxi(c, i));
        }

        return aggregators;
    }