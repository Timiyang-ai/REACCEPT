@Test
    public void testCopy()
    {
        Matrix ACopy = A.copy();
        
        assertEquals(A, ACopy);
        assertEquals(A.multiply(B), ACopy.multiply(B));
    }