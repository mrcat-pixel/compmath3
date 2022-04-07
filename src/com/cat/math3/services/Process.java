package com.cat.math3.services;

import com.cat.math3.methods.RectangleMethod;
import com.cat.math3.objects.Func;
import com.cat.math3.objects.MethodResult;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Process {

    public Process() {
        startMessage();
        process();
        exitMessage();
    }

    private void process() {
        while (true) {
            String temp = prompt("complab3>");
            if (temp.equals("q") || temp.equals("quit")) break;

            switch (temp) {
                case "calc"         -> calculate();
                case "h", "help"    -> help();
                default             -> err();
            }
        }
    }

    private void startMessage() {
        System.out.println("Welcome to the solver. To see the list of commands, type \"h\". " +
                "To quit, type \"q\".");
        printLine();
    }

    private void exitMessage() {
        printLine();
        System.out.println("Exiting...");
    }

    private void printLine() {
        System.out.println("----------------------------------------");
    }

    private void help() {
        printLine();
        System.out.println("""
            Command list:
            calc  -- calculate;
            q     -- quit the program;
            h     -- display this message;""");
        printLine();
    }

    private void calculate() {
        printLine();
        System.out.println("""
            Choose a function to calculate the integral for:
            1: y = x + cos(x) - 0.67x^3 - 1
            2: y = x^3 + 2x^2 - 5x - 5
            3: y = sin(1/x)""");
        printLine();
        int eq_id = getId();

        Func function = switch (eq_id) {
            case 1 -> new Func() {
                @Override
                public double f(double x) {
                    return x + Math.cos(x) - 0.67 * Math.pow(x, 3) - 1;
                }
            };
            case 2 -> new Func() {
                @Override
                public double f(double x) {
                    return Math.pow(x, 3) + 2 * Math.pow(x, 2) - 5 * x - 5;
                }
            };
            default -> new Func() {
                @Override
                public double f(double x) {
                    return Math.sin(1/x);
                }
            };
        };

        System.out.println("Input precision:");
        double precision = getFloat();

        System.out.println("Input the beginning of the range:");
        double rangeBegin = getFloat();
        System.out.println("Input the end of the range:");
        double rangeEnd = getFloat();

        printLine();

        MethodResult right = RectangleMethod.rectangleMethod(function, precision, rangeBegin, rangeEnd, 1);
        MethodResult mid = RectangleMethod.rectangleMethod(function, precision, rangeBegin, rangeEnd, 0.5);
        MethodResult left = RectangleMethod.rectangleMethod(function, precision, rangeBegin, rangeEnd, 0);

        if (Double.isNaN(right.getValue() + mid.getValue() + left.getValue())
                || Double.isInfinite(right.getValue() + mid.getValue() + left.getValue())) {
            System.out.println("Couldn't find the solution.");
        }
        else {
            System.out.println("Using the right rule:\n" + right);
            System.out.println("Using the mid rule:\n" + mid);
            System.out.println("Using the left rule:\n" + left);
        }

        printLine();
    }


    private int getId() {
        int ret = 0;
        while (true) {
            String temp = prompt(">");

            try { ret = Integer.parseInt(temp); }
            catch (NumberFormatException ignore) {}

            if (ret >= 1 && ret <= 3) return ret;
            System.out.println("Incorrect value, try again");
        }
    }

    private double getFloat() {
        while (true) {
            String temp = prompt(">");

            try { return Double.parseDouble(temp); }
            catch (NumberFormatException e) { System.out.println("Incorrect value, try again"); }
        }
    }

    private void err() {
        System.out.println("Incorrect command. To see the list of commands, type \"h\".");
    }

    private String prompt(String prompt) {
        System.out.print(prompt);
        return readFromScanner(new Scanner(System.in));
    }

    private String readFromScanner(Scanner scanner) {
        try { return scanner.nextLine(); }
        catch (NoSuchElementException e) { return "q"; }
    }
}
