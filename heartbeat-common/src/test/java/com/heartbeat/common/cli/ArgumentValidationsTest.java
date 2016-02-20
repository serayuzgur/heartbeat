package com.heartbeat.common.cli;

/**
 * Created by serayuzgur on 20/02/16.
 */
public class ArgumentValidationsTest {

    @org.junit.Test
    public void testCheckLengthAndValue() throws Exception {
        testCheckLength();
        testCheckValues();


    }

    @org.junit.Test
    public void testCheckLength() throws Exception {
        String[] args = new String[]{"a", "b", "c"};
        try {
            ArgumentValidations.checkLengthAndValue(args, 3);
        } catch (ArgumentException e) {
            e.printStackTrace();
            assert false;
        }
        try {
            ArgumentValidations.checkLengthAndValue(args, 4);
            assert false;
        } catch (ArgumentException e) {
            System.out.println(e.getMessage());
            assert true;
        }

    }

    @org.junit.Test
    public void testCheckValues() throws Exception {
        String[] args = new String[]{"a", "", "c"};
        try {
            ArgumentValidations.checkLengthAndValue(args, 3);
        } catch (ArgumentException e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals("Missing argument at Index: 1 Value: EMPTY");
        }
        args = new String[]{"a", null, "c"};
        try {
            ArgumentValidations.checkLengthAndValue(args, 3);
        } catch (ArgumentException e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals("Missing argument at Index: 1 Value: NULL");
        }
        args = new String[]{"", null, "c"};
        try {
            ArgumentValidations.checkLengthAndValue(args, 3);
        } catch (ArgumentException e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals("Missing argument at Index: 0 Value: EMPTY");
        }
        args = new String[]{null, null, "c"};
        try {
            ArgumentValidations.checkLengthAndValue(args, 3);
        } catch (ArgumentException e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals("Missing argument at Index: 0 Value: NULL");
        }
        args = new String[]{"a", "b", ""};
        try {
            ArgumentValidations.checkLengthAndValue(args, 3);
        } catch (ArgumentException e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals("Missing argument at Index: 2 Value: EMPTY");
        }
        args = new String[]{"a", "b", null};
        try {
            ArgumentValidations.checkLengthAndValue(args, 3);
        } catch (ArgumentException e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals("Missing argument at Index: 2 Value: NULL");
        }
    }
}