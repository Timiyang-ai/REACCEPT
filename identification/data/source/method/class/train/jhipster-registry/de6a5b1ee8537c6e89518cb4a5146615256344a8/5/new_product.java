@GetMapping(value = "/ssh/public_key", produces = MediaType.TEXT_PLAIN_VALUE)
    @Timed
    public ResponseEntity<String> eureka() {
        try {
            String publicKey = new String(Files.readAllBytes(
                Paths.get(System.getProperty("user.home") + "/.ssh/id_rsa.pub")));

            return new ResponseEntity<>(publicKey, HttpStatus.OK);
        } catch (IOException e) {
            log.warn("SSH public key could not be loaded: {}", e.getMessage());
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
    }