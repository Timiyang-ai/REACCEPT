@SuppressWarnings("unchecked")
    @Override public StepFunction<GiniImpurityMeasure>[] calculate(DecisionTreeData data, TreeFilter filter, int depth) {
        TreeDataIndex index = null;
        boolean canCalculate = false;

        if (useIndex) {
            index = data.createIndexByFilter(depth, filter);
            canCalculate = index.rowsCount() > 0;
        }
        else {
            data = data.filter(filter);
            canCalculate = data.getFeatures().length > 0;
        }

        if (canCalculate) {
            int rowsCnt = rowsCount(data, index);
            int colsCnt = columnsCount(data, index);

            StepFunction<GiniImpurityMeasure>[] res = new StepFunction[colsCnt];

            long right[] = new long[lbEncoder.size()];
            for (int i = 0; i < rowsCnt; i++) {
                double lb = getLabelValue(data, index, 0, i);
                right[getLabelCode(lb)]++;
            }

            for (int col = 0; col < res.length; col++) {
                if(!useIndex)
                    data.sort(col);

                double[] x = new double[rowsCnt + 1];
                GiniImpurityMeasure[] y = new GiniImpurityMeasure[rowsCnt + 1];

                long[] left = new long[lbEncoder.size()];
                long[] rightCopy = Arrays.copyOf(right, right.length);

                int xPtr = 0, yPtr = 0;
                x[xPtr++] = Double.NEGATIVE_INFINITY;
                y[yPtr++] = new GiniImpurityMeasure(
                    Arrays.copyOf(left, left.length),
                    Arrays.copyOf(rightCopy, rightCopy.length)
                );

                for (int i = 0; i < rowsCnt; i++) {
                    double lb = getLabelValue(data, index, col, i);
                    left[getLabelCode(lb)]++;
                    rightCopy[getLabelCode(lb)]--;

                    double featureVal = getFeatureValue(data, index, col, i);
                    if (i < (rowsCnt - 1) && getFeatureValue(data, index, col, i + 1) == featureVal)
                        continue;

                    x[xPtr++] = featureVal;
                    y[yPtr++] = new GiniImpurityMeasure(
                        Arrays.copyOf(left, left.length),
                        Arrays.copyOf(rightCopy, rightCopy.length)
                    );
                }

                res[col] = new StepFunction<>(Arrays.copyOf(x, xPtr), Arrays.copyOf(y, yPtr));
            }

            return res;
        }

        return null;
    }