@Test
    public void testMm() {
        System.out.println("mm");
        double[][] A = {
                {0.7220180, 0.07121225, 0.6881997},
                {-0.2648886, -0.89044952, 0.3700456},
                {-0.6391588, 0.44947578, 0.6240573}
        };
        double[][] B = {
                {0.6881997, -0.07121225, 0.7220180},
                {0.3700456, 0.89044952, -0.2648886},
                {0.6240573, -0.44947578, -0.6391588}
        };
        double[][] C = {
                {0.9527204, -0.2973347, 0.06257778},
                {-0.2808735, -0.9403636, -0.19190231},
                {0.1159052, 0.1652528, -0.97941688}
        };

        ColumnMajorMatrix a = new ColumnMajorMatrix(A);
        ColumnMajorMatrix b = new ColumnMajorMatrix(B);
        assertTrue(Math.equals(a.abmm(b).array(), C, 1E-7));
        Math.abtmm(A, B, C);
        assertTrue(Math.equals(a.abtmm(b).array(), C, 1E-7));
        Math.atbmm(A, B, C);
        assertTrue(Math.equals(a.atbmm(b).array(), C, 1E-7));
    }