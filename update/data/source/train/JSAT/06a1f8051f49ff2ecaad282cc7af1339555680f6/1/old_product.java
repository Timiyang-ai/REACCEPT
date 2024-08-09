public Vec optimize(double eps, int iterationLimit, Function f, List<Vec> initalPoints)
    {
        if(initalPoints.isEmpty())
            throw new ArithmeticException("Empty Initial list. Can not determin dimension of problem");
        Vec init = initalPoints.get(0);
        int N = initalPoints.get(0).length();
        //The simplex verticies paired with their value from the objective function 
        List<ProbailityMatch<Vec>> simplex = new ArrayList<ProbailityMatch<Vec>>(N);
        for(Vec vars : initalPoints)
            simplex.add(new ProbailityMatch<Vec>(f.f(vars), vars.clone()));
        Random rand = new Random(initalPoints.hashCode());
        
        while(simplex.size() < N+1)
        {
            //Better simplex geneartion?
            DenseVector newSimplex = new DenseVector(N);
            for(int i = 0; i < newSimplex.length(); i++)
                if(init.get(i) != 0)
                    newSimplex.set(i, init.get(i)*rand.nextGaussian());
                else
                    newSimplex.set(i, rand.nextGaussian());
            
            simplex.add(new ProbailityMatch<Vec>(f.f(newSimplex), newSimplex));
        }
        
        Collections.sort(simplex);
        //Remove superfolusly given points
        while(simplex.size() > N+1)
            simplex.remove(simplex.size()-1);
        
        //Center of gravity point
        Vec x0 = new DenseVector(N);
        //reflection point
        Vec xr = new DenseVector(N);
        //Extension point, also used for contraction
        Vec xec = new DenseVector(N);
        //Temp space for compuations
        Vec tmp = new DenseVector(N);
        
        final int lastIndex = simplex.size()-1;
        for(int iterationCount = 0; iterationCount < iterationLimit; iterationCount++)
        {
            //Convergence check 
            if(Math.abs(simplex.get(lastIndex).getProbability() - simplex.get(0).getProbability()) < eps)
                break;
            //Step 2: valculate x0
            x0.zeroOut();
            for(ProbailityMatch<Vec> pm : simplex)
                x0.mutableAdd(pm.getMatch());
            x0.mutableDivide(simplex.size());
            
            //Step 3: Reflection
            x0.copyTo(xr);
            x0.copyTo(tmp);
            tmp.mutableSubtract(simplex.get(lastIndex).getMatch());
            xr.mutableAdd(reflection, tmp);
            double fxr = f.f(xr);
            if(simplex.get(0).getProbability() <= fxr && fxr < simplex.get(lastIndex-1).getProbability())
            {
                insertIntoSimplex(simplex, xr, fxr);
                continue;
            }
            
            //Step 4: Expansion
            if(fxr < simplex.get(0).getProbability())//Best so far
            {
                x0.copyTo(xec);
                xec.mutableAdd(expansion, tmp);//tmp still contains (x0-xWorst)
                double fxec = f.f(xec);
                if(fxec < fxr)
                    insertIntoSimplex(simplex, xec, fxec);//Even better! Use this one
                else
                    insertIntoSimplex(simplex, xr, fxr);//Ehh, wasnt as good as we thought
                continue;
            }
            
            //Step 5: Contraction
            x0.copyTo(xec);
            xec.mutableAdd(contraction, tmp);
            double fxec = f.f(xec);
            if(fxec < simplex.get(lastIndex).getProbability())
            {
                insertIntoSimplex(simplex, xec, fxec);
                continue;
            }
            //Step 6: Reduction
            Vec xBest = simplex.get(0).getMatch();
            for(int i = 1; i < simplex.size(); i++)
            {
                ProbailityMatch<Vec> pm = simplex.get(i);
                Vec xi = pm.getMatch();
                xi.mutableSubtract(xBest);
                xi.mutableMultiply(shrink);
                xi.mutableAdd(xBest);
                pm.setProbability(f.f(xi));
            }
            Collections.sort(simplex);
        }
        
        return simplex.get(0).getMatch();
    }