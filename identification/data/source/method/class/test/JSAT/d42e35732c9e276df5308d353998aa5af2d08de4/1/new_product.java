public CategoricalResults clone()
    {
        CategoricalResults copy = new CategoricalResults(n);
        copy.probabilities = Arrays.copyOf(probabilities, probabilities.length);
        return copy;
    }