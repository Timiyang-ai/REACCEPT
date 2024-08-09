@Test
    public void testRestart() {
	// TODO Test if really started
	server.occupie();
	server.restart("AFL/afl-showmap -m none -o [output]/[id] ");
    }