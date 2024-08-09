    @Test
    public void suspend() {
        System.out.println("suspend");
        instance.suspend();
        assertEquals(Quantum.SUSPENDED, instance.getInterval());        
    }