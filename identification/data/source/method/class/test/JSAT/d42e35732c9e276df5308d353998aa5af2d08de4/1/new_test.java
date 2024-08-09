@Test
    public void testCopy()
    {
        Matrix ACopy = A.clone();
        
        assertEquals(A, ACopy);
        assertEquals(A.multiply(B), ACopy.multiply(B));
    }