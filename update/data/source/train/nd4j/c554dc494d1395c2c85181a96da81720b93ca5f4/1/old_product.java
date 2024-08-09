public static INDArray pca(INDArray in, double variance) {
        // lets calculate the covariance and the mean
        INDArray[] covmean = covarianceMatrix(in);
        // use the covariance matrix to find "force constants" and then break into orthonormal
        // unit vector components
        INDArray[] pce = principalComponents(covmean[0]);
        // calculate the variance of each component
        INDArray vars = Transforms.pow(Transforms.sqrt(pce[1], false), -1, false);
        double res = vars.sumNumber().doubleValue();
        double total = 0.0;
        int ndims = 0;
        for (int i = 0; i < vars.columns(); i++) {
            ndims++;
            total += vars.getDouble(i);
            if (total/res > variance) break;
        }
        INDArray result = Nd4j.create(in.columns(), ndims);
        for (int i = 0; i < ndims; i++) result.putColumn(i, pce[0].getColumn(i));
        return result;
    }