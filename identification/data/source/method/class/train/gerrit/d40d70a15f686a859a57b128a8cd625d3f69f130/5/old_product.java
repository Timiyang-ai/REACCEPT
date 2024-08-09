public RefUpdate.Result save(CommitBuilder cb)
      throws PGPException, IOException {
    if (pending.isEmpty()) {
      return RefUpdate.Result.NO_CHANGE;
    }
    if (reader == null) {
      load();
    }
    if (notes == null) {
      notes = NoteMap.newEmptyMap();
    }
    ObjectId newTip;
    try (ObjectInserter ins = repo.newObjectInserter()) {
      for (PGPPublicKeyRing keyRing : pending) {
        saveToNotes(ins, keyRing);
      }
      if (tip != null) {
        cb.setParentId(tip);
      }
      cb.setTreeId(notes.writeTree(ins));
      if (cb.getMessage() == null) {
        cb.setMessage(String.format("Update %d public key%s",
            pending.size(), pending.size() != 1 ? "s" : ""));
      }
      newTip = ins.insert(cb);
      ins.flush();
    }

    RefUpdate ru = repo.updateRef(RefNames.REFS_GPG_KEYS);
    ru.setExpectedOldObjectId(tip);
    ru.setNewObjectId(newTip);
    ru.setRefLogIdent(cb.getCommitter());
    ru.setRefLogMessage("Store public keys", true);
    RefUpdate.Result result = ru.update();
    close();
    switch (result) {
      case FAST_FORWARD:
      case NEW:
      case NO_CHANGE:
        pending.clear();
        break;
      default:
        break;
    }
    return result;
  }