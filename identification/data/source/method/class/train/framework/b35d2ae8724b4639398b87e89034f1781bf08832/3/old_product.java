public void setReadOnly(boolean readOnly) {
        getBindings().stream()
                .forEach(binding -> binding.setReadOnly(readOnly));
    }