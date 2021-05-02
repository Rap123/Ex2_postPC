package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }

    //finding all views
    TextView equalsView = findViewById(R.id.buttonEquals);
    TextView plusView = findViewById(R.id.buttonPlus);
    TextView minusView = findViewById(R.id.buttonMinus);
    TextView clearView = findViewById(R.id.buttonClear);
    TextView button0View = findViewById(R.id.button0);
    TextView button1View = findViewById(R.id.button1);
    TextView button2View = findViewById(R.id.button2);
    TextView button3View = findViewById(R.id.button3);
    TextView button4View = findViewById(R.id.button4);
    TextView button5View = findViewById(R.id.button5);
    TextView button6View = findViewById(R.id.button6);
    TextView button7View = findViewById(R.id.button7);
    TextView button8View = findViewById(R.id.button8);
    TextView button9View = findViewById(R.id.button9);
    TextView outputView = findViewById(R.id.textViewCalculatorOutput);
    View spaceBelowButton1View = findViewById(R.id.spaceBelowButton1);
    View backSpaceView = findViewById(R.id.buttonBackSpace);
    ImageView backSpaceImageView = findViewById(R.id.backSpaceImage);

    //Initialized output from calculator's logic
    outputView.setText(calculator.output());

    //set click listener for all buttons
    equalsView.setOnClickListener(v ->{
      calculator.insertEquals();
      outputView.setText(calculator.output());
    });

    plusView.setOnClickListener(v ->{
      calculator.insertPlus();
      outputView.setText(calculator.output());
    });

    plusView.setOnClickListener(v ->{
      calculator.insertPlus();
      outputView.setText(calculator.output());
    });

    minusView.setOnClickListener(v ->{
      calculator.insertMinus();
      outputView.setText(calculator.output());
    });

    clearView.setOnClickListener(v ->{
      calculator.clear();
      outputView.setText(calculator.output());
    });

    button0View.setOnClickListener(v ->{
      calculator.insertDigit(0);
      outputView.setText(calculator.output());
    });

    button1View.setOnClickListener(v ->{
      calculator.insertDigit(1);
      outputView.setText(calculator.output());
    });

    button2View.setOnClickListener(v ->{
      calculator.insertDigit(2);
      outputView.setText(calculator.output());
    });

    button3View.setOnClickListener(v ->{
      calculator.insertDigit(3);
      outputView.setText(calculator.output());
    });

    button4View.setOnClickListener(v ->{
      calculator.insertDigit(4);
      outputView.setText(calculator.output());
    });

    button5View.setOnClickListener(v ->{
      calculator.insertDigit(5);
      outputView.setText(calculator.output());
    });

    button6View.setOnClickListener(v ->{
      calculator.insertDigit(6);
      outputView.setText(calculator.output());
    });

    button7View.setOnClickListener(v ->{
      calculator.insertDigit(7);
      outputView.setText(calculator.output());
    });

    button8View.setOnClickListener(v ->{
      calculator.insertDigit(8);
      outputView.setText(calculator.output());
    });

    button9View.setOnClickListener(v ->{
      calculator.insertDigit(9);
      outputView.setText(calculator.output());
    });

    backSpaceView.setOnClickListener(v ->{
      calculator.deleteLast();
      outputView.setText(calculator.output());
    });
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("savedOutput", calculator.saveState());
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    // todo: restore calculator state from the bundle, refresh main text-view from calculator's output
    calculator.loadState(savedInstanceState.getSerializable("savedOutput"));
    TextView outputView = findViewById(R.id.textViewCalculatorOutput);
    outputView.setText(calculator.output());



  }
}