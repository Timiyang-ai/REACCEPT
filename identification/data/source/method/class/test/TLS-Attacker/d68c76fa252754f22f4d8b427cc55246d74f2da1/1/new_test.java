@Test
    public void testRestart() {
	server.occupie();
	CertificateMutator mut = new FixedCertificateMutator();
	ServerCertificateStructure cert = mut.getServerCertificateStructure();
	server.start("", cert.getCertificateFile(), cert.getKeyFile());
	server.serverIsRunning();
    }