private static void checkContract(Contract contract) {
        if( !contract.isOk() ) {
            reporter.message("The capsule is not sealed");
            contract.getErrors().forEach(e->reporter.error(e.getError().toString(), e.getObjectName(), e.getMessage()));
        }
        contract.seal();
        contract.check();
        contract.getErrors().forEach(error -> {
            addError(error.getError().toString(), error.getObjectName(), error.getMessage());
        });
        if(contract.getErrors().size() == 0) {
            report("Contract is valid");
        }
    }