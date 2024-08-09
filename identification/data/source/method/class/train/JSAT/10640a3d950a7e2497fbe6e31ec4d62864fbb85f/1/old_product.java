public static int medoid(boolean parallel, Collection<Integer> indecies, double tol, List<? extends Vec> X, DistanceMetric dm, List<Double> accel)
    {
        final int N = indecies.size();
        
        if(tol <= 0 || N < SystemInfo.LogicalCores)//Really just not enough points, lets simplify
            return PAM.medoid(parallel, indecies, X, dm, accel);
        
        
        final double log2d = Math.log(1)-Math.log(tol);
        
        /**
         * Online estimate of the standard deviation that will be used
         */
        final OnLineStatistics distanceStats;
        /**
         * This array contains the current sum of all distance computations done
         * for each index. Corresponds to mu in the paper.
         */
        AtomicDoubleArray totalDistSum = new AtomicDoubleArray(N);
        /**
         * This array contains the current number of distance computations that
         * have been done for each feature index. Corresponds to T_i in the
         * paper.
         */
        AtomicIntegerArray totalDistCount = new AtomicIntegerArray(N);
        final int[] indx_map = indecies.stream().mapToInt(i->i).toArray();
        final boolean symetric = dm.isSymmetric();
        final double[] lower_bound_est = new double[N];
        final double[] upper_bound_est = new double[N];

        ThreadLocal<Random> localRand = ThreadLocal.withInitial(RandomUtil::getRandom);

        //First pass, lets pull every "arm" (compute a dsitance) for each datumn at least once, so that we have estiamtes to work with. 
        distanceStats = ParallelUtils.run(parallel, N, (start, end)->
        {
            Random rand = localRand.get();
            OnLineStatistics localStats = new OnLineStatistics();
            for(int i = start; i < end; i++)
            {
                int j = rand.nextInt(N);
                while(j == i)
                    j = rand.nextInt(N);
                

                double d_ij = dm.dist(indx_map[i], indx_map[j], X, accel);
                localStats.add(d_ij);
                totalDistSum.addAndGet(i, d_ij);
                totalDistCount.incrementAndGet(i);
                if(symetric)
                {
                    totalDistSum.addAndGet(j, d_ij);
                    totalDistCount.incrementAndGet(j);
                }
            }
            
            return localStats;
        }, (a,b)-> OnLineStatistics.add(a, b));
        
        //Now lets prepare the lower and upper bound estimates
        ConcurrentSkipListSet<Integer> lowerQ = new ConcurrentSkipListSet<>((Integer o1, Integer o2) -> 
        {
            int cmp = Double.compare(lower_bound_est[o1], lower_bound_est[o2]);
            if(cmp == 0)//same bounds, but sort by identity to avoid issues
                cmp = o1.compareTo(o2);
            return cmp;
        });
        
        ConcurrentSkipListSet<Integer> upperQ = new ConcurrentSkipListSet<>((Integer o1, Integer o2) -> 
        {
            int cmp = Double.compare(upper_bound_est[o1], upper_bound_est[o2]);
            if(cmp == 0)//same bounds, but sort by identity to avoid issues
                cmp = o1.compareTo(o2);
            return cmp;
        });

        ParallelUtils.run(parallel, N, (start, end)->
        {
            double v = distanceStats.getVarance();
            for(int i = start; i < end; i++)
            {
                int T_i = totalDistCount.get(i);
                double c_i = Math.sqrt(2*v*log2d/T_i);
                lower_bound_est[i] = totalDistSum.get(i)/T_i - c_i;
                upper_bound_est[i] = totalDistSum.get(i)/T_i + c_i;
                lowerQ.add(i);
                upperQ.add(i);
            }
        });
        
        
        //Now lets start sampling! 
        
        //how many points should we pick and sample? Not really discussed in paper- but a good idea for efficency (dont want to pay that Q cost as much as possible)
        /**
         * to-pull is how many arms we will select per iteration
         */
        int num_to_pull;
        /**
         * to sample is how many random pairs we will pick for each pulled arm
         */
        int samples;
        
        if(parallel)
        {
            num_to_pull = Math.max(SystemInfo.LogicalCores, 32);
            samples = Math.min(32, N-1);
        }
        else
        {
            num_to_pull = Math.min(32, N);
            samples = Math.min(32, N-1);
        }
        
        /**
         * The levers we will pull this iteration, and then add back in
         */
        IntList to_pull = new IntList();
        /**
         * the levers we must add back in but not update b/c they hit max evaluations and the confidence bound is tight
         */
        IntList toAddBack = new IntList();
        boolean[] isExact = new boolean[N];
        Arrays.fill(isExact, false);
        int numExact = 0;
        
        
        while(numExact < N)//loop should break out before this ever happens
        {
            to_pull.clear();
            toAddBack.clear();

            //CONVERGENCE CEHCK
            if(upper_bound_est[upperQ.first()] < lower_bound_est[lowerQ.first()])
            {
                //WE are done!
                return indx_map[upperQ.first()];
            }
            

            while(to_pull.size() < num_to_pull)
            {
                
                if(lowerQ.isEmpty())
                    break;//we've basically evaluated everyone
                int i = lowerQ.pollFirst();
                
                
                if(totalDistCount.get(i) >= N-1 && !isExact[i])//Lets just replace with exact value
                {
                    double avg_d_i = ParallelUtils.run(parallel, N, (start, end)->
                    {
                        double d = 0;
                        for (int j = start; j < end; j++)
                            if (i != j)
                                d += dm.dist(indx_map[i], indx_map[j], X, accel);
                        return d;
                    }, (a, b)->a+b);
                    avg_d_i /= N-1;
                    
                    upperQ.remove(i);
                    lower_bound_est[i] = upper_bound_est[i] = avg_d_i;
                    totalDistSum.set(i, avg_d_i);
                    totalDistCount.set(i, N);
                    isExact[i] = true;
                    numExact++;
//                    System.out.println("Num Exact: " + numExact);
                    //OK, exavt value for datumn I is set. 
                    toAddBack.add(i);
                }
                

                if(!isExact[i])
                    to_pull.add(i);
            }
            
            //OK, lets now pull a bunch of levers / measure distances
            
            OnLineStatistics changeInStats = ParallelUtils.run(parallel, to_pull.size(), (start, end)->
            {
                Random rand = localRand.get();
                OnLineStatistics localStats = new OnLineStatistics();
                for(int i_count = start; i_count < end; i_count++)
                {
                    int i = to_pull.get(i_count);
                    for(int j_count = 0; j_count < samples; j_count++)
                    {
                        int j = rand.nextInt(N);
                        while(j == i)
                            j = rand.nextInt(N);
                        
                        double d_ij = dm.dist(indx_map[i], indx_map[j], X, accel);
                        localStats.add(d_ij);
                        totalDistSum.addAndGet(i, d_ij);
                        totalDistCount.incrementAndGet(i);
                        if(symetric && !isExact[j])
                        {
                            totalDistSum.addAndGet(j, d_ij);
                            totalDistCount.incrementAndGet(j);
                        }
                    }
                }
                
                return localStats;
            }, (a,b) -> OnLineStatistics.add(a, b));
            
            distanceStats.add(changeInStats);
            
            //update bounds and re-insert
            double v = distanceStats.getVarance();
            //we are only updating the bounds on the levers we pulled
            //that may mean some old bounds are stale
            //these values are exact
            lowerQ.addAll(toAddBack);
            upperQ.addAll(toAddBack);
            upperQ.removeAll(to_pull);
            for(int i : to_pull)
            {
                int T_i = totalDistCount.get(i);
                double c_i = Math.sqrt(2*v*log2d/T_i);
                lower_bound_est[i] = totalDistSum.get(i)/T_i - c_i;
                upper_bound_est[i] = totalDistSum.get(i)/T_i + c_i;
                lowerQ.add(i);
                upperQ.add(i);
            }
        }
        
        //We can reach this point on small N or low D datasets. Iterate and return the correct value
        int bestIndex = 0;
        for(int i = 1; i < N; i++)
            if(lower_bound_est[i] < lower_bound_est[bestIndex])
                bestIndex = i;
        
        return bestIndex;
    }