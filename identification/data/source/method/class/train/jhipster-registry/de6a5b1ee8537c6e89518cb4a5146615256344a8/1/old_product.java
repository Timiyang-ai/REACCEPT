@RequestMapping(value = "/ssh/public_key",
        method = RequestMethod.GET,
        produces = MediaType.TEXT_PLAIN_VALUE)
    @Timed
    public ResponseEntity<String> eureka() throws IOException {
        String publicKey =
            new String(Files.readAllBytes(
                Paths.get(System.getProperty("user.home") +"/.ssh/id_rsa.pub")));

        return new ResponseEntity<>(publicKey, HttpStatus.OK);
    }