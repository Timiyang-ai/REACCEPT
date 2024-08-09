public Vec multiply(Vec b)
    {
        DenseVector result = new  DenseVector(rows());
        multiply(b, 1.0, result);
        return result;
    }