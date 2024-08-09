@GetMapping(value = "/ssh/public_key", produces = MediaType.TEXT_PLAIN_VALUE)
    @Timed
    public ResponseEntity<String> eureka() {
        try {
            String publicKey = getPublicKey();
            if(publicKey != null) return new ResponseEntity<>(publicKey, HttpStatus.OK);
        } catch (IOException e) {
            log.warn("SSH public key could not be loaded: {}", e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }