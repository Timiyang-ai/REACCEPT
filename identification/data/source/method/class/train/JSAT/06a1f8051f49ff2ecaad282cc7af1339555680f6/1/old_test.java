@Test
    public void testOptimize()
    {
        System.out.println("optimize");
        Random rand = RandomUtil.getRandom();
        Vec x0 = new DenseVector(3);//D=3 means one local minima for easy evaluation
        for(int i = 0; i < x0.length(); i++)
            x0.set(i, rand.nextDouble()+0.5);

        RosenbrockFunction f = new RosenbrockFunction();
        FunctionVec fp = f.getDerivative();
        ModifiedOWLQN instance = new ModifiedOWLQN();
        instance.setLambda(0.0); 
        instance.setMaximumIterations(500);

        Vec w = new DenseVector(x0.length());
        instance.optimize(1e-8, w, x0, f, fp, null);

        for (int i = 0; i < w.length(); i++)
            assertEquals(1.0, w.get(i), 1e-2);
        assertEquals(0.0, f.f(w), 1e-3);
    }