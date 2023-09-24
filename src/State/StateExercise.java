package State;

import java.util.Objects;

class CombinationLock {
    public int[] combination;
    private static final String locked = "LOCKED";
    private static final String open = "OPEN";
    public String status = locked;

    public CombinationLock(int[] combination) {
        this.combination = combination;

    }

    public static String arrayToString(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int num : array) {
            stringBuilder.append(num);
        }

        return stringBuilder.toString();
    }

    private final int combinationSize = combination.length;
    private final String combinationToString = arrayToString(combination);


    public void enterDigit(int digit) {
        if (Objects.equals(status, locked)) {
            status = String.valueOf(digit);
        } else if (status.length() < combinationSize - 1) {
            status += digit;
        } else if ((status + digit).equals(combinationToString)) {
            status = open;
        } else {
            status = locked;
        }
    }
}

public class StateExercise {
    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4};
        CombinationLock cl = new CombinationLock(ints);
        System.out.println(cl.status);
        cl.enterDigit(1);
        System.out.println(cl.status);
        cl.enterDigit(2);
        System.out.println(cl.status);
        cl.enterDigit(3);
        System.out.println(cl.status);
        cl.enterDigit(4);
        System.out.println(cl.status);
    }
}

/*
suggested solution

package com.activemesa.behavioral.state.exercise;

class CombinationLock
{
  private int [] combination;

  public String status;
  private int digitsEntered = 0;
  private boolean failed = false;

  public CombinationLock(int[] combination)
  {
    this.combination = combination;
    reset();
  }

  private void reset()
  {
    status = "LOCKED";
    digitsEntered = 0;
    failed = false;
  }

  public void enterDigit(int digit)
  {
    if (status.equals("LOCKED")) status = "";
    status += digit;
    if (combination[digitsEntered] != digit)
    {
      failed = true;
    }
    digitsEntered++;

    if (digitsEntered == combination.length)
      status = failed ? "ERROR" : "OPEN";
  }
}
 */
