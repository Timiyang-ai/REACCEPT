public static void OuterProductUpdate(Matrix A, Vec a, Vec b, double c)
    {
        if(a.length() != A.rows() || b.length() != A.cols())
            throw new ArithmeticException("Matrix dimensions do not agree with outer product");
        if(a.isSparse())
            for(IndexValue iv : a)
                A.updateRow(iv.getIndex(), iv.getValue()*c, b);
        else
            for (int i = 0; i < a.length(); i++)
            {
                double rowCosnt = c * a.get(i);
                A.updateRow(i, rowCosnt, b);
            }
    }