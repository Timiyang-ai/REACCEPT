@Test
    public void testRestart() {
	server.occupie();
	CertificateMutator mut = new FixedCertificateMutator();
	server.start("", mut.getServerCertificateKeypair().getCertificateFile(), mut.getServerCertificateKeypair()
		.getKeyFile());
	server.serverIsRunning();
    }