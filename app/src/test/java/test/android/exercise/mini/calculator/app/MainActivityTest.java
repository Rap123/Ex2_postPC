package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.MainActivity;
import android.exercise.mini.calculator.app.R;
import android.exercise.mini.calculator.app.SimpleCalculator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.io.Serializable;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28})
public class MainActivityTest {

  private static final String DEFAULT_CALCULATOR_OUTPUT = "~~~ ready to start ~~~";

  private ActivityController<MainActivity> activityController;
  private MainActivity activityUnderTest;
  private SimpleCalculator mockCalculator;

  /** initialize main activity with a mock calculator */
  @Before
  public void setup(){
    mockCalculator = Mockito.mock(SimpleCalculator.class);
    Mockito.when(mockCalculator.output()).thenReturn(DEFAULT_CALCULATOR_OUTPUT);

    activityController = Robolectric.buildActivity(MainActivity.class);
    activityUnderTest = activityController.get();
    activityUnderTest.calculator = mockCalculator;
    activityController.create().start().resume();
  }

  @Test
  public void when_settingUpTheActivity_then_itShouldShowTheExpectedCalculatorOutputRightAway() {
    // setup
    String expectedText = DEFAULT_CALCULATOR_OUTPUT;
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    // verify
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_userClicksButtonPlus_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    // setup
    String expectedText = "button PLUS clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);

    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View buttonPlus = activityUnderTest.findViewById(R.id.buttonPlus);

    // test
    buttonPlus.performClick();

    // verify
    Mockito.verify(mockCalculator).insertPlus(); // make sure that the activity called this method
    assertEquals(expectedText, activityMainTextView.getText().toString()); // make sure that the activity shows text from calculator.output()
  }

  @Test
  public void when_userClicksButtonequals_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    String expectedText = "button EQUALS clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View buttonEquals = activityUnderTest.findViewById(R.id.buttonEquals);
    buttonEquals.performClick();
    Mockito.verify(mockCalculator).insertEquals();
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_userClicksButtonClear_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    String expectedText = "button CLEAR clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View buttonClear = activityUnderTest.findViewById(R.id.buttonClear);
    buttonClear.performClick();
    Mockito.verify(mockCalculator).clear();
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_userClicksButtonBackSpace_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    String expectedText = "button BACKSPACE clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View buttonBackSpace = activityUnderTest.findViewById(R.id.buttonBackSpace);
    buttonBackSpace.performClick();
    Mockito.verify(mockCalculator).deleteLast();
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_userClicksButton0_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    String expectedText = "button 0 clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View button0 = activityUnderTest.findViewById(R.id.button0);
    button0.performClick();
    Mockito.verify(mockCalculator).insertDigit(0);
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_userClicksButton1_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    String expectedText = "button 1 clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View button1 = activityUnderTest.findViewById(R.id.button1);
    button1.performClick();
    Mockito.verify(mockCalculator).insertDigit(1);
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_userClicksButton2_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    String expectedText = "button 2 clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View button2 = activityUnderTest.findViewById(R.id.button2);
    button2.performClick();
    Mockito.verify(mockCalculator).insertDigit(2);
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_userClicksButton3_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    String expectedText = "button 3 clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View button3 = activityUnderTest.findViewById(R.id.button3);
    button3.performClick();
    Mockito.verify(mockCalculator).insertDigit(3);
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_userClicksButton4_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    String expectedText = "button 4 clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View button4 = activityUnderTest.findViewById(R.id.button4);
    button4.performClick();
    Mockito.verify(mockCalculator).insertDigit(4);
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_userClicksButton5_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    String expectedText = "button 5 clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View button5 = activityUnderTest.findViewById(R.id.button5);
    button5.performClick();
    Mockito.verify(mockCalculator).insertDigit(5);
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_userClicksButton6_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    String expectedText = "button 6 clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View button6 = activityUnderTest.findViewById(R.id.button6);
    button6.performClick();
    Mockito.verify(mockCalculator).insertDigit(6);
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_userClicksButton7_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    String expectedText = "button 7 clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View button7 = activityUnderTest.findViewById(R.id.button7);
    button7.performClick();
    Mockito.verify(mockCalculator).insertDigit(7);
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_userClicksButton8_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    String expectedText = "button 8 clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View button8 = activityUnderTest.findViewById(R.id.button8);
    button8.performClick();
    Mockito.verify(mockCalculator).insertDigit(8);
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_userClicksButton9_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    String expectedText = "button 9 clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View button9 = activityUnderTest.findViewById(R.id.button9);
    button9.performClick();
    Mockito.verify(mockCalculator).insertDigit(9);
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_activityGetsStateSaved_then_shouldAlsoSaveCalculatorState() {
    // setup
    Serializable dummyState = new Serializable() {};
    Mockito.when(mockCalculator.saveState()).thenReturn(dummyState);

    Bundle spyBundle = Mockito.spy(new Bundle());

    // test
    activityController.saveInstanceState(spyBundle);

    // verify
    Mockito.verify(spyBundle).putSerializable(anyString(), eq(dummyState)); // make sure that the activity stored the calculator state into the spy bundle
  }


  @Test
  public void when_activityGetsStateRestored_then_shouldAlsoSaveCalculatorState() {
    // setup
    Serializable dummyState = new Serializable() {};
    Mockito.when(mockCalculator.saveState()).thenReturn(dummyState);

    // let the activity store the calculator state into the bundle
    Bundle spyBundle = Mockito.spy(new Bundle());
    activityController.saveInstanceState(spyBundle);

    // test
    activityController.restoreInstanceState(spyBundle);

    // verify
    Mockito.verify(mockCalculator).loadState(eq(dummyState)); // make sure that the activity passed the previously-stored state to the calculator to load
  }
}