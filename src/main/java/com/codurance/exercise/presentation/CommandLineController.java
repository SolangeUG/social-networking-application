package com.codurance.exercise.presentation;

import com.codurance.exercise.application.SocialNetwork;
import com.codurance.exercise.application.SocialNetworkImpl;

import java.time.LocalDateTime;

/**
 * Command-Line Controller
 * @author Solange U. Gasengayire
 */
public class CommandLineController {
    private final SocialNetwork network;

    /**
     * Default constructor
     */
    public CommandLineController() {
        super();
        this.network = new SocialNetworkImpl();
    }

    /**
     * Execute input command from the user
     * @param instruction user command (as astring)
     * @return result of the command
     */
    public String executeInstruction(String instruction) {
        Command command = new Command(instruction);
        String operator = command.getOperator();
        String operand = command.getOperand();

        String result = "";

        switch (command.getOperation()) {
            case POST:
                network.createContent(operator, operand, LocalDateTime.now());
                break;

            case READ:
                result = network.getContent(operator);
                break;

            case FOLLOW:
                network.createSubscription(operator, operand, LocalDateTime.now());
                break;

            case WALL:
                result = network.getAllContent(operator);
                break;

            case QUIT:
                result = "QUIT";
                break;

            default:
                break;
        }

        return result;
    }
}
