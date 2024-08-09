synchronized void enqueue(Reference<? extends T> reference) {
        if (head == null) {
            reference.queueNext = SENTINEL;
        } else {
            reference.queueNext = head;
        }
        head = reference;
        notify();
    }