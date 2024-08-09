public static void OuterProductUpdate(Matrix A, Vec x, Vec y, double c)
    {
        if (x.length() != A.rows() || y.length() != A.cols())
            throw new ArithmeticException("Matrix dimensions do not agree with outer product");
        if (x.isSparse())
            for (IndexValue iv : x)
                A.updateRow(iv.getIndex(), iv.getValue() * c, y);
        else
            for (int i = 0; i < x.length(); i++)
            {
                double rowCosnt = c * x.get(i);
                A.updateRow(i, rowCosnt, y);
            }
    }