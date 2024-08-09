protected EnterResponse enter(Commit<? extends EnterRequest> commit) {
    try {
      PartitionId partitionId = commit.value().getPartitionId();
      PrimaryTerm oldTerm = term(partitionId);
      Registration registration = new Registration(
          commit.value().getMember(),
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
      return EnterResponse.newBuilder()
          .setTerm(newTerm)
          .build();
    } catch (Exception e) {
      getLogger().error("State machine operation failed", e);
      throwIfUnchecked(e);
      throw new RuntimeException(e);
    }
  }