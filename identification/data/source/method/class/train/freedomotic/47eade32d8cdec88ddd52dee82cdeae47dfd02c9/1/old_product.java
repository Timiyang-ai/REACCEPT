@Override
    public boolean equals(Object obj) {
        boolean payloadConsistence = true;

        if (obj instanceof Payload) {
            Payload eventPayload = (Payload) obj;
            Iterator<Statement> it = payload.iterator();

            //check all statement for consistency
            while (it.hasNext()) {
                Statement triggerStatement = it.next();

                List<Statement> filteredEventStatements = eventPayload.getStatements(triggerStatement.attribute);

                if (filteredEventStatements.isEmpty()) {
                    // no statement present into event, corresponding filtered trigger statement
                    if (triggerStatement.value.equalsIgnoreCase(Statement.ANY)) {
                        // if trigger value = ANY, we expectected at least one matching statement, so test fails.
                        if (triggerStatement.getLogical().equalsIgnoreCase(Statement.AND)) {
                            payloadConsistence = false; // that is = payloadConsistence && false;
                        } else {
                            if (triggerStatement.getLogical().equalsIgnoreCase(Statement.OR)) {
                                payloadConsistence = payloadConsistence || false;
                            }
                        }
                    }
                } else {
                    for (Statement eventStatement : filteredEventStatements) {
                        /*
                         * TODO: waring, supports only operand equal in event
                         * compared to equal, morethen, lessthen in triggers.
                         * Refacor with a strategy pattern.
                         */
                        if (eventStatement != null) {
                            //is setting a value must be not used to filter
                            if (triggerStatement.logical.equalsIgnoreCase("SET")) {
                                return true;
                            } else {
                                boolean isStatementConsistent
                                        = isStatementConsistent(triggerStatement.operand, triggerStatement.value,
                                                eventStatement.value);

                                if (triggerStatement.getLogical().equalsIgnoreCase(Statement.AND)) {
                                    payloadConsistence = payloadConsistence && isStatementConsistent; //true AND true; false AND true; false AND false; true AND false
                                } else {
                                    if (triggerStatement.getLogical().equalsIgnoreCase(Statement.OR)) {
                                        payloadConsistence = payloadConsistence || isStatementConsistent;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            payloadConsistence = false;
        }

        return payloadConsistence;
    }