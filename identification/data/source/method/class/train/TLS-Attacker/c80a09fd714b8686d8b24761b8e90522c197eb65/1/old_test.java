@Test
    public void testStart() {
	server.occupie();
	server.start("AFL/afl-showmap -m none -o [output]/[id] ");
	server.serverIsRunning();
    }