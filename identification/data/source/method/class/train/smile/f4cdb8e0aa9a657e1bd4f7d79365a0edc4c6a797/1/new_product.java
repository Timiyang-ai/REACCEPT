public static double apply(int[] truth, double[] probability) {
        if (truth.length != probability.length) {
            throw new IllegalArgumentException(String.format("The vector sizes don't match: %d != %d.", truth.length, probability.length));
        }

        // for large sample size, overflow may happen for pos * neg.
        // switch to double to prevent it.
        double pos = 0;
        double neg = 0;

        for (int i = 0; i < truth.length; i++) {
            if (truth[i] == 0) {
                neg++;
            } else if (truth[i] == 1) {
                pos++;
            } else {
                throw new IllegalArgumentException("AUC is only for binary classification. Invalid label: " + truth[i]);
            }
        }

        int[] label = truth.clone();
        double[] prediction = probability.clone();

        QuickSort.sort(prediction, label);

        double[] rank = new double[label.length];
        for (int i = 0; i < prediction.length; i++) {
            if (i == prediction.length - 1 || prediction[i] != prediction[i+1]) {
                rank[i] = i + 1;
            } else {
                int j = i + 1;
                for (; j < prediction.length && prediction[j] == prediction[i]; j++);
                double r = (i + 1 + j) / 2.0;
                for (int k = i; k < j; k++) rank[k] = r;
                i = j - 1;
            }
        }

        double auc = 0.0;
        for (int i = 0; i < label.length; i++) {
            if (label[i] == 1)
                auc += rank[i];
        }

        auc = (auc - (pos * (pos+1) / 2.0)) / (pos * neg);
        return auc;
    }