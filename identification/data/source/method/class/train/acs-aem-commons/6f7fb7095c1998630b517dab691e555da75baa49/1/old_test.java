    @Test
    public void toString_Double() {
        String expResult = "1000.0";
        Double doubleValue = new Double(1000);
        try {
            assertEquals(expResult, TypeUtil.toString(doubleValue, Double.class));
        } catch (IllegalAccessException e) {
            assertTrue(false);
        } catch (NoSuchMethodException e) {
            assertTrue(false);
        } catch (InvocationTargetException e) {
            assertTrue(false);
        }
    }