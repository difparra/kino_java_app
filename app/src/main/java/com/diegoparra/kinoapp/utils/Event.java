package com.diegoparra.kinoapp.utils;

public class Event<T> {

    private T content;
    private boolean hasBeenHandled;

    public Event(T content) {
        this.content = content;
        hasBeenHandled = false;
    }

    public boolean hasBeenHandled() {
        return hasBeenHandled;
    }

    public T getContentIfNotHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }

    public T peekContent() {
        return content;
    }
}
