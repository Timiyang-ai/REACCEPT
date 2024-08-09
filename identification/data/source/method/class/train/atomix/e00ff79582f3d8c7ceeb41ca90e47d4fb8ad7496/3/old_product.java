protected PrimaryTerm enter(Commit<? extends PrimaryElectorOperations.Enter> commit) {
    try {
      PartitionId partitionId = commit.value().partitionId();
      PrimaryTerm oldTerm = term(partitionId);
      Registration registration = new Registration(
          commit.value().member(),
          commit.session().sessionId().id());
      PrimaryTerm newTerm = elections.compute(partitionId, (k, v) -> {
        if (v == null) {
          return new ElectionState(partitionId, registration);
        } else {
          if (!v.isDuplicate(registration)) {
            return new ElectionState(v).addRegistration(registration);
          } else {
            return v;
          }
        }
      })
          .term();

      if (!Objects.equals(oldTerm, newTerm)) {
        notifyTermChange(partitionId, newTerm);
        scheduleRebalance();
      }
      return newTerm;
    } catch (Exception e) {
      getLogger().error("State machine operation failed", e);
      throwIfUnchecked(e);
      throw new RuntimeException(e);
    }
  }