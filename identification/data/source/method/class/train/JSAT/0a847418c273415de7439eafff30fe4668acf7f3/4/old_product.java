public static void OuterProductUpdate(Matrix A, Vec a, Vec b, double c)
    {
        for (int i = 0; i < a.length(); i++)
        {
            double rowCosnt = c * a.get(i);
            A.updateRow(i, rowCosnt, b);
        }
    }