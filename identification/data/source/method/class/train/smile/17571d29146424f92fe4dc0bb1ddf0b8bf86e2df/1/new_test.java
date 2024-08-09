@Test
    public void testMinus_doubleArr_doubleArr() {
        System.out.println("sub");
        double[] x = {-2.1968219, -0.9559913, -0.0431738, 1.0567679, 0.3853515};
        double[] y = {-1.7781325, -0.6659839, 0.9526148, -0.9460919, -0.3925300};
        double[] z = {-0.4186894, -0.2900074, -0.9957886, 2.0028598, 0.7778815};
        MathEx.sub(x, y);
        assertTrue(MathEx.equals(x, z));
    }