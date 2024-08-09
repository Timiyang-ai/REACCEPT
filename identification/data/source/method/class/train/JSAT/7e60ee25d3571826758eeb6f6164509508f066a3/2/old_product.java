private void setUp(Random rand)
    {
        Ws = new ArrayList<Matrix>(npl.length);
        bs = new ArrayList<Vec>(npl.length);
        
        //First Hiden layer takes input raw
        DenseMatrix W = new DenseMatrix(npl[0], inputSize);
        Vec b = new DenseVector(W.rows());
        initializeWeights(W, rand);
        initializeWeights(b, W.cols(), rand);
        Ws.add(W);
        bs.add(b);
        
        //Other Hiden Layers Layers 
        for(int i = 1; i < npl.length; i++)
        {
            W = new DenseMatrix(npl[i], npl[i-1]);
            b = new DenseVector(W.rows());
            initializeWeights(W, rand);
            initializeWeights(b, W.cols(), rand);
            Ws.add(W);
            bs.add(b);
        }
        
        //Output layer
        W = new DenseMatrix(outputSize, npl[npl.length-1]);
        b = new DenseVector(W.rows());
        initializeWeights(W, rand);
        initializeWeights(b, W.cols(), rand);
        Ws.add(W);
        bs.add(b);
        
    }