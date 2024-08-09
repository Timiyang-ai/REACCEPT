@Test
    public void testRestart() {
	server.occupie();
	server.restart("AFL/afl-showmap -m none -o [output]/[id] ");
	server.serverIsRunning();
    }