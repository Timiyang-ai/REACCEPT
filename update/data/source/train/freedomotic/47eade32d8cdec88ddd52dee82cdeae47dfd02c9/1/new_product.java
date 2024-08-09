@Override
    public boolean equals(Object obj) {
        boolean payloadConsistence = true;

        if (obj instanceof Payload) {
            Payload eventPayload = (Payload) obj;
            Iterator<Statement> it = payload.iterator();

            //check all statement for consistency
            while (it.hasNext()) {
                Statement triggerStatement = it.next();
                
                // at this stage the trigger has already all the event.* properties embedded (shoud be skipped)
                if (triggerStatement.getAttribute().startsWith("event.")) {
                    //skip this iteration, and continue with the next statement
                    continue;
                }

                List<Statement> filteredEventStatements = eventPayload.getStatements(triggerStatement.attribute);

                if (filteredEventStatements.isEmpty()) {
                    //if the trigger has a property which is not in the event
                    if (!triggerStatement.logical.equalsIgnoreCase(Statement.SET)) {
                        //if it is AND/OR/...
                        return false;
                    }
                    //if the trigger has a property which is not in the event, BUT allowed value is ANY
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