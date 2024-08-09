public void setCovariance(Matrix covMatrix)
    {
        if(!covMatrix.isSquare())
            throw new ArithmeticException("Covariance matrix must be square");
        else if(covMatrix.rows() != this.mean.length())
            throw new ArithmeticException("Covariance matrix does not agree with the mean");
        
        CholeskyDecomposition cd = new CholeskyDecomposition(covMatrix.clone());
        L = cd.getLT();
        L.mutableTranspose();
        
        int k = mean.length();
        if(Double.isNaN(log_det) || log_det < log(1e-10))
        {
            //Numerical unstable or sub rank matrix. Use the SVD to work with the more stable pesudo matrix
            SingularValueDecomposition svd = new SingularValueDecomposition(covMatrix.clone());
            //We need the rank deficient PDF and pesude inverse
            this.logPDFConst = 0.5*log(svd.getPseudoDet()) + svd.getRank()*0.5*log(2*PI);
            this.invCovariance = svd.getPseudoInverse();
        }
        else
        {
            this.logPDFConst = (-k*log(2*PI)-log_det)*0.5;
            this.invCovariance = cd.solve(Matrix.eye(k));
        }
    }