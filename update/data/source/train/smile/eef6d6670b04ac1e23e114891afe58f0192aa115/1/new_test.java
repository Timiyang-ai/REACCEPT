@Test
    public void testWeather() {
        System.out.println("Weather");

        int[] prediction = LOOCV.classification(WeatherNominal.x, WeatherNominal.y, (x, y) -> KNN.fit(x, y));
        int error = Error.apply(WeatherNominal.y, prediction);
        System.out.println("1-NN Error = " + error);
        assertEquals(7, error);

        prediction = LOOCV.classification(WeatherNominal.x, WeatherNominal.y, (x, y) -> KNN.fit(x, y, 3));
        error = Error.apply(WeatherNominal.y, prediction);
        System.out.println("3-NN Error = " + error);
        assertEquals(4, error);

        prediction = LOOCV.classification(WeatherNominal.x, WeatherNominal.y, (x, y) -> KNN.fit(x, y, 5));
        error = Error.apply(WeatherNominal.y, prediction);
        System.out.println("5-NN Error = " + error);
        assertEquals(5, error);

        prediction = LOOCV.classification(WeatherNominal.x, WeatherNominal.y, (x, y) -> KNN.fit(x, y,7));
        error = Error.apply(WeatherNominal.y, prediction);
        System.out.println("7-NN Error = " + error);
        assertEquals(5, error);
    }