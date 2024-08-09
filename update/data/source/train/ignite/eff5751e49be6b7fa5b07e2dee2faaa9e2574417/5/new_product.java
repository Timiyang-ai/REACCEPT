@SuppressWarnings("unchecked")
    @Override public StepFunction<GiniImpurityMeasure>[] calculate(DecisionTreeData data, TreeFilter filter, int depth) {
        TreeDataIndex idx = null;
        boolean canCalculate = false;

        if (useIdx) {
            idx = data.createIndexByFilter(depth, filter);
            canCalculate = idx.rowsCount() > 0;
        }
        else {
            data = data.filter(filter);
            canCalculate = data.getFeatures().length > 0;
        }

        if (canCalculate) {
            int rowsCnt = rowsCount(data, idx);
            int colsCnt = columnsCount(data, idx);

            StepFunction<GiniImpurityMeasure>[] res = new StepFunction[colsCnt];

            long right[] = new long[lbEncoder.size()];
            for (int i = 0; i < rowsCnt; i++) {
                double lb = getLabelValue(data, idx, 0, i);
                right[getLabelCode(lb)]++;
            }

            for (int col = 0; col < res.length; col++) {
                if (!useIdx)
                    data.sort(col);

                double[] x = new double[rowsCnt + 1];
                GiniImpurityMeasure[] y = new GiniImpurityMeasure[rowsCnt + 1];

                long[] left = new long[lbEncoder.size()];
                long[] rightCp = Arrays.copyOf(right, right.length);

                int xPtr = 0, yPtr = 0;
                x[xPtr++] = Double.NEGATIVE_INFINITY;
                y[yPtr++] = new GiniImpurityMeasure(
                    Arrays.copyOf(left, left.length),
                    Arrays.copyOf(rightCp, rightCp.length)
                );

                for (int i = 0; i < rowsCnt; i++) {
                    double lb = getLabelValue(data, idx, col, i);
                    left[getLabelCode(lb)]++;
                    rightCp[getLabelCode(lb)]--;

                    double featureVal = getFeatureValue(data, idx, col, i);
                    if (i < (rowsCnt - 1) && getFeatureValue(data, idx, col, i + 1) == featureVal)
                        continue;

                    x[xPtr++] = featureVal;
                    y[yPtr++] = new GiniImpurityMeasure(
                        Arrays.copyOf(left, left.length),
                        Arrays.copyOf(rightCp, rightCp.length)
                    );
                }

                res[col] = new StepFunction<>(Arrays.copyOf(x, xPtr), Arrays.copyOf(y, yPtr));
            }

            return res;
        }

        return null;
    }