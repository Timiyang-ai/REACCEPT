public static void OuterProductUpdate(Matrix C, Vec a, Vec b, double c)
    {
        for(int i = 0; i < a.length(); i++)
        {
            double rowCosnt = c*a.get(i);
            for(int j = 0; j < b.length(); j++)
                C.set(i, j, C.get(i, j) + rowCosnt * b.get(j) ); 
        }
    }