public Integer apply(Vector vec) {
        int res = -1;
        double minDist = Double.POSITIVE_INFINITY;

        for (int i = 0; i < centers.length; i++) {
            double curDist = distanceMeasure.compute(centers[i], vec);
            if (curDist < minDist) {
                minDist = curDist;
                res = i;
            }
        }

        return res;
    }