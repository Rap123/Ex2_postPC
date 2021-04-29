package android.exercise.mini.calculator.app;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleCalculatorImpl implements SimpleCalculator {

  private ArrayList<String> history = new ArrayList<>();
  private int historyLastIdx;
  private int res;

  public SimpleCalculatorImpl()
  {
    history.add("0");
    historyLastIdx = 0;
    res = 0;
  }

  @Override
  public String output() {
    if(historyLastIdx == 0 && history.get(0).equals("0"))
    {
      return Integer.toString(res);
    }
    else
    {
      StringBuilder str = new StringBuilder();
      for(int i = 0; i <= historyLastIdx; i++)
      {
        str.append(history.get(i));
      }
      return str.toString();
    }
  }

  @Override
  public void insertDigit(int digit) {
    if(history.get(0).equals("0") && historyLastIdx == 0)
    {
      history.remove(0);
      historyLastIdx--;
    }
    if (digit >= 0 && digit <=9)
    {
      history.add(Integer.toString(digit));
      historyLastIdx++;
    }
    else
    {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void insertPlus() {
    if(history.get(historyLastIdx).equals("+") || history.get(historyLastIdx).equals("-"))
    {
      return;
    }
    history.add("+");
    historyLastIdx++;
  }

  @Override
  public void insertMinus() {
    if(history.get(historyLastIdx).equals("+") || history.get(historyLastIdx).equals("-"))
    {
      return;
    }
    history.add("-");
    historyLastIdx++;
  }

  @Override
  public void insertEquals() {
    ArrayList<String> calc = new ArrayList<>();
    StringBuilder temp = new StringBuilder("0");
    for (int i = 0; i <= historyLastIdx; i++) {
      if (history.get(i).equals("+"))
      {
        calc.add(temp.toString());
        temp = new StringBuilder();
      }
      else if (history.get(i).equals("-"))
      {
        calc.add(temp.toString());
        temp = new StringBuilder();
        temp.append(history.get(i));
      }
      else
        {
        temp.append(history.get(i));
        }
    }
    calc.add(temp.toString());

    for (int j = 0; j < calc.size(); j++)
      {
        res += Integer.parseInt(calc.get(j));
      }

    history.clear();
    history.add(Integer.toString(res));
    historyLastIdx = 0;
    res = 0;
  }

  @Override
  public void deleteLast() {
    if(history.isEmpty())
    {
      history.add("0");
      historyLastIdx++;
      return;
    }

    if(history.get(0).equals("0") && historyLastIdx == 0)
    {
      return;
    }
    history.remove(historyLastIdx);
    historyLastIdx--;

  }

  @Override
  public void clear() {
    history.clear();
    history.add("0");
    res = 0;
    historyLastIdx = 0;
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    state.set(history, historyLastIdx, res);
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    history = casted.getHistory();
    historyLastIdx = casted.getHistoryLastIdx();
    res = casted.getRes();
  }

  private static class CalculatorState implements Serializable {
    private ArrayList<String> historyS = new ArrayList<>();
    private int historyLastIdxS;
    private int resS;

    public void set(ArrayList<String> historyArr, int historyLastIdx, int res)
    {
      historyS = new ArrayList<>(historyArr);
      historyLastIdxS = historyLastIdx;
      resS = res;
    }

    public ArrayList<String> getHistory()
    {
      return historyS;
    }
    public int getHistoryLastIdx()
    {
      return historyLastIdxS;
    }
    public int getRes()
    {
      return resS;
    }
  }
}
