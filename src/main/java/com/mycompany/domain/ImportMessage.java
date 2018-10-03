package com.mycompany.domain;

import java.util.Objects;

public class ImportMessage {

    int done;
    int fail;
    String message;

    public ImportMessage() {
    }

    public ImportMessage(String message) {

        this.message = message;
    }

    public ImportMessage(int done, int fail, String message) {
        this.done = done;
        this.fail = fail;
        this.message = message;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportMessage that = (ImportMessage) o;
        return done == that.done &&
                fail == that.fail &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(done, fail, message);
    }

    @Override
    public String toString() {
        return "ImportMessage{" +
                "done=" + done +
                ", fail=" + fail +
                ", message='" + message + '\'' +
                '}';
    }
}
