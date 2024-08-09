private void train(Vec[] featureVals, double[] target)
    {
        final int d = featureVals.length;
        final int m = target.length;
        w = new DenseVector(d);
        final double[] z = new double[m];
        final double beta = loss.beta();

        Random rand = new Random();
        for (int t = 1; t <= epochs; t++)
        {
            int j = rand.nextInt(d + 1);//+1 for the bias term

            double g = 0.0;
            if (j < d)
            {
                Vec xj = featureVals[j];
                for (IndexValue iv : xj)
                {
                    int i = iv.getIndex();
                    g += loss.deriv(z[i], target[i]) * iv.getValue();
                }
            }
            else//Bias term update, all x[i]_j = 1
            {
                for (int i = 0; i < target.length; i++)
                    g += loss.deriv(z[i], target[i]);
            }
            g /= m;

            double eta;
            double w_j = j == d ? bias : w.get(j);
            if (w_j - g / beta > lambda / beta)
                eta = -g / beta - lambda / beta;
            else if (w_j - g / beta < -lambda / beta)
                eta = -g / beta + lambda / beta;
            else
                eta = -w_j;

            if (j < d)
                w.increment(j, eta);
            else
                bias += eta;

            if (j < d)
                for (IndexValue iv : featureVals[j])
                    z[iv.getIndex()] += eta * iv.getValue();
            else//Bias update, all x[i]_j = 1
                for (int i = 0; i < target.length; i++)
                    z[i] += eta;
        }
    }