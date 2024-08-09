public static DiscreteExponentialFamilyMixture fit(int[] x, Component... components) {
        return fit(x, components, 0.0, 500, 1E-4);
    }