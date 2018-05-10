package com.codurance.exercise.presentation;

/**
 * A class that represents a user command from the console
 * @author Solange U. Gasengayire
 */
public class Command {

    /**
     * All possible operations associated
     * with a user instruction from the console
     */
    public enum Operation {
        POST,
        READ,
        FOLLOW,
        WALL,
        QUIT
    }

    private Operation operation;
    private String operator;
    private String operand;

    /**
     * Constructor
     * @param command user command
     */
    public Command(String command) {
       super();
       this.extractParameters(command);
    }

    /**
     * Return the operation associated with this command
     * @return operation
     */
    public Operation getOperation() {
        return this.operation;
    }

    /**
     * Return the operator associated with this command
     * It is either a user's name, or the 'QUIT' instruction
     * @return operator
     */
    public String getOperator() {
        return this.operator;
    }

    /**
     * Return the operand associated with this command
     * @return operand
     */
    public String getOperand() {
        return this.operand;
    }

    /**
     * Extract operator and parameters from
     * the input user command
     */
    private void extractParameters(String command) {

        // posting a message
        if (command.contains("->")) {
            int ndx = command.indexOf("->");
            int size = "->".length();
            operator = command.substring(0, ndx).trim();
            operand = command.substring(ndx + size).trim();
            operation = Operation.POST;
            return;
        }

        // following another user
        if (command.contains("follows")) {
            int ndx = command.indexOf("follows");
            int size = "follows".length();
            operator = command.substring(0, ndx).trim();
            operand = command.substring(ndx + size).trim();
            operation = Operation.FOLLOW;
            return;
        }

        // displaying user aggregated wall
        if (command.contains("wall")) {
            int ndx = command.indexOf("wall");
            operator = command.substring(0, ndx).trim();
            operation = Operation.WALL;
            return;
        }

        // exiting the application
        if ("QUIT".equals(command)) {
            operation = Operation.QUIT;
        } else {
            // displaying user wall
            operator = command.trim();
            operation = Operation.READ;
        }
    }
}
