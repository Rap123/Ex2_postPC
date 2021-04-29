package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculator;
import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }


  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    String expected = "0-";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }


  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.deleteLast();
    String expected = "1+3";
    assertEquals(expected, calculatorUnderTest.output());

  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.clear();
    String expected = "0";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    firstCalculator.insertDigit(1);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(3);
    firstCalculator.insertDigit(5);
    Serializable saveFirst = firstCalculator.saveState();
    assertNotNull(saveFirst);
    secondCalculator.loadState(saveFirst);
    assertEquals("1+35", secondCalculator.output());

  }

  // TODO:
  //  the existing tests are not enough since they only test simple use-cases with small inputs.
  //  write at least 10 methods to test correct behavior with complicated inputs or use-cases.
  //  examples:
  //  - given input "5+7-13<DeleteLast>25", expected output is "5+17-125"
  //  - given input "9<Clear>12<Clear>8-7=", expected output is "1"
  //  - given input "8-7=+4=-1=", expected output is "4"
  //  - given input "999-888-222=-333", expected output is "-111-333"
  //  - with 2 calculators, give them different inputs, then save state on first calculator and load the state into second calculator, make sure state loaded well
  //  etc etc.
  //  feel free to be creative in your tests!

  @Test
  public void when_insertingMoreThanOneDigitInArow_should_calculateAsAfullNumber()
  {
    SimpleCalculatorImpl simpleCalculator = new SimpleCalculatorImpl();
    simpleCalculator.insertDigit(1);
    simpleCalculator.insertDigit(2);
    simpleCalculator.insertPlus();
    simpleCalculator.insertDigit(1);
    simpleCalculator.insertDigit(5);
    simpleCalculator.insertEquals();
    String out = simpleCalculator.output();
    assertEquals("27", out);
  }

  @Test
  public void when_insertingEqualSignAndThanMoreDigits_should_calculateAndPrintBothInOutput()
  {
    SimpleCalculatorImpl simpleCalculator = new SimpleCalculatorImpl();
    simpleCalculator.insertDigit(1);
    simpleCalculator.insertDigit(2);
    simpleCalculator.insertMinus();
    simpleCalculator.insertDigit(1);
    simpleCalculator.insertDigit(5);
    simpleCalculator.insertEquals();
    simpleCalculator.insertMinus();
    simpleCalculator.insertDigit(3);
    simpleCalculator.insertDigit(3);
    String out = simpleCalculator.output();
    assertEquals("-3-33", out);
  }

  @Test
  public void when_clearAfterClear_should_justClearAndReturnOutput0()
  {
    SimpleCalculatorImpl simpleCalculator = new SimpleCalculatorImpl();
    simpleCalculator.insertDigit(1);
    simpleCalculator.insertDigit(2);
    simpleCalculator.insertMinus();
    simpleCalculator.insertDigit(1);
    simpleCalculator.insertDigit(5);
    simpleCalculator.insertEquals();
    simpleCalculator.insertMinus();
    simpleCalculator.insertDigit(3);
    simpleCalculator.insertDigit(3);
    simpleCalculator.clear();
    simpleCalculator.clear();
    simpleCalculator.clear();
    assertEquals("0", simpleCalculator.output());
  }

  @Test
  public void when_deleteMoreCharsThanExists_should_returnOutput0()
  {
    SimpleCalculatorImpl simpleCalculator = new SimpleCalculatorImpl();
    simpleCalculator.insertDigit(3);
    simpleCalculator.insertMinus();
    simpleCalculator.insertDigit(1);
    simpleCalculator.deleteLast();
    simpleCalculator.deleteLast();
    simpleCalculator.deleteLast();
    simpleCalculator.deleteLast();
    simpleCalculator.deleteLast();
    simpleCalculator.deleteLast();
    assertEquals("0", simpleCalculator.output());
  }

  @Test
  public void when_insertEqualsWithMinusAtStart_should_calculateWithNegative()
  {
    SimpleCalculatorImpl simpleCalculator = new SimpleCalculatorImpl();
    simpleCalculator.insertMinus();
    simpleCalculator.insertDigit(7);
    simpleCalculator.insertPlus();
    simpleCalculator.insertDigit(5);
    simpleCalculator.insertEquals();
    assertEquals("-2", simpleCalculator.output());
  }

  @Test
  public void when_insertFewPlusesInArow_should_insertJustOnePlus()
  {
    SimpleCalculatorImpl simpleCalculator = new SimpleCalculatorImpl();
    simpleCalculator.insertDigit(5);
    simpleCalculator.insertPlus();
    simpleCalculator.insertPlus();
    simpleCalculator.insertPlus();
    simpleCalculator.insertPlus();
    simpleCalculator.insertPlus();
    assertEquals("5+", simpleCalculator.output());
  }

  @Test
  public void when_insertFewMinusesInArow_should_insertJustOneMinus()
  {
    SimpleCalculatorImpl simpleCalculator = new SimpleCalculatorImpl();
    simpleCalculator.insertDigit(5);
    simpleCalculator.insertMinus();
    simpleCalculator.insertMinus();
    simpleCalculator.insertMinus();
    simpleCalculator.insertMinus();
    simpleCalculator.insertMinus();
    assertEquals("5-", simpleCalculator.output());
  }

  @Test
  public void when_insertMinusAntThanPlus_should_ignoreTheLast()
  {
    SimpleCalculatorImpl simpleCalculator = new SimpleCalculatorImpl();
    simpleCalculator.insertDigit(5);
    simpleCalculator.insertMinus();
    simpleCalculator.insertPlus();
    assertEquals("5-", simpleCalculator.output());
  }

  @Test
  public void when_insertPlusAntThanMinus_should_ignoreTheLast()
  {
    SimpleCalculatorImpl simpleCalculator = new SimpleCalculatorImpl();
    simpleCalculator.insertDigit(5);
    simpleCalculator.insertPlus();
    simpleCalculator.insertMinus();
    assertEquals("5+", simpleCalculator.output());
  }

  @Test
  public void when_insertFewEqualsInArow_should_printTheSameResultAgain()
  {
    SimpleCalculatorImpl simpleCalculator = new SimpleCalculatorImpl();
    simpleCalculator.insertDigit(5);
    simpleCalculator.insertPlus();
    simpleCalculator.insertDigit(7);
    simpleCalculator.insertEquals();
    simpleCalculator.insertEquals();
    simpleCalculator.insertEquals();
    simpleCalculator.insertEquals();
    simpleCalculator.insertEquals();

    assertEquals("12", simpleCalculator.output());
  }

  @Test
  public void when_insertMoreThanOneEquals_should_printAllCalculations()
  {
    SimpleCalculatorImpl simpleCalculator = new SimpleCalculatorImpl();
    simpleCalculator.insertDigit(5);
    simpleCalculator.insertPlus();
    simpleCalculator.insertDigit(7);
    simpleCalculator.insertEquals();
    simpleCalculator.insertDigit(2);
    simpleCalculator.insertPlus();
    simpleCalculator.insertDigit(4);
    simpleCalculator.insertEquals();
    simpleCalculator.insertDigit(5);
    simpleCalculator.insertPlus();
    assertEquals("1265+", simpleCalculator.output());
  }
}