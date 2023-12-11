package com.scalar.splitwise.Commands;

public interface Command {
    public void execute(String input);

    public boolean matches(String input);
}
