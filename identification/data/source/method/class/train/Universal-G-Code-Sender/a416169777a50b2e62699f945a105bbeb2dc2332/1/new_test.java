@Test
    @Ignore
    public void testExecuteCustomGcode() {
        System.out.println("executeCustomGcode");
        String str = "";
        BackendAPI backend = null;
        try {
        	MacroHelper.executeCustomGcode(str, backend);
        } catch (Exception ex) {
        	// guaranteed NullPointerException because of null backend passed into MacroHelper.substituteValues()
        }
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }