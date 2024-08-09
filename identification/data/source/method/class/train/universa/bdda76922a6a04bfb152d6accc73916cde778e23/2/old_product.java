private static void checkContract(Contract c) {
        c.seal();
        c.check();
        c.getErrors().forEach(error -> {
            addError(error.getError().toString(), error.getObjectName(), error.getMessage());
        });
        if(c.getErrors().size() == 0) {
            report("Contract is valid");
        }
    }