private static void checkContract(Contract c) {
        c.check();
        c.getErrors().forEach(error -> {
            addError(error.getError().toString(), error.getObjectName(), error.getMessage());
        });
    }