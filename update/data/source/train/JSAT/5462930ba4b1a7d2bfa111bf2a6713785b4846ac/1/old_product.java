protected double cluster(DataSet data, boolean doInit, int[] medioids, int[] assignments, List<Double> cacheAccel, boolean parallel)
    {
        DoubleAdder totalDistance =new DoubleAdder();
        LongAdder changes = new LongAdder();
        Arrays.fill(assignments, -1);//-1, invalid category!
        
        int[] bestMedCand = new int[medioids.length];
        double[] bestMedCandDist = new double[medioids.length];
        List<Vec> X = data.getDataVectors();
        final List<Double> accel;
        
        if(doInit)
        {
            TrainableDistanceMetric.trainIfNeeded(dm, data);
            accel = dm.getAccelerationCache(X);
            selectIntialPoints(data, medioids, dm, accel, rand, seedSelection);
        }
        else
            accel = cacheAccel;

        int iter = 0;
        do
        {
            changes.reset();
            totalDistance.reset();
            
            ParallelUtils.run(parallel, data.getSampleSize(), (start, end)->
            {
                for(int i = start; i < end; i++)
                {
                    int assignment = 0;
                    double minDist = dm.dist(medioids[0], i, X, accel);

                    for (int k = 1; k < medioids.length; k++)
                    {
                        double dist = dm.dist(medioids[k], i, X, accel);
                        if (dist < minDist)
                        {
                            minDist = dist;
                            assignment = k;
                        }
                    }

                    //Update which cluster it is in
                    if (assignments[i] != assignment)
                    {
                        changes.increment();
                        assignments[i] = assignment;
                    }
                    totalDistance.add(minDist * minDist);
                }
            });
            
            //TODO this update may be faster by using more memory, and actually moiving people about in the assignment loop above
            //Update the medoids
            Arrays.fill(bestMedCandDist, Double.MAX_VALUE);
            for(int i = 0; i < data.getSampleSize(); i++)
            {
                double thisCandidateDistance;
                final int clusterID = assignments[i];
                final int medCandadate = i;
                
                final int ii = i;
                thisCandidateDistance = ParallelUtils.range(data.getSampleSize(), parallel)
                        .filter(j -> j != ii && assignments[j] == clusterID)
                        .mapToDouble(j -> Math.pow(dm.dist(medCandadate, j, X, accel), 2))
                        .sum();
                
                if(thisCandidateDistance < bestMedCandDist[clusterID])
                {
                    bestMedCand[clusterID] = i;
                    bestMedCandDist[clusterID] = thisCandidateDistance;
                }
            }
            System.arraycopy(bestMedCand, 0, medioids, 0, medioids.length);
        }
        while( changes.sum() > 0 && iter++ < iterLimit);
        
        return totalDistance.sum();
    }