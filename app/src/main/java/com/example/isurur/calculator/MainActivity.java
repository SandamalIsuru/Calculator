package com.example.isurur.calculator;

import static android.text.Selection.*;
import javax.script.*;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String MULTIPLICATION = "×";
    private static final String SUBTRACTION = "-";
    private static final String DIVISION = "÷";
    private static final String ADDITION = "+";
    private static final String PRECENTAGE = "%";
    private static final String SYMBOLS = "+-×÷%";
    private static final String REGEX1 = "([0-9%!eπ)]{1,1})";
    private static final String REGEX2 = "([\\(+×÷\\-]{1,1})";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";
    private static final String PI = "π";
    private static final String E = "e";
    private static final String EMPTY = "";
    private static final String NUMBERS = "0123456789";
    private static DecimalFormat df2 = new DecimalFormat(".#########");
    private int isSwitch = 1;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnSub;
    private Button btnPlus;
    private Button btnDiv;
    private Button btnMul;
    private Button btnEqual;
    private Button btnDot;
    private Button btnBracket;
    private Button btnClear;
    private Button btnBack;
    private Button btnMod;
    private Button btnPlusMinus;
    private EditText displayView;
    private TextView resultView;

    private Button btn2nd;
    private Button btnRad;
    private Button btnSquareRoot;
    private Button btnSin;
    private Button btnCos;
    private Button btnTan;
    private Button btnLn;
    private Button btnLog;
    private Button btnArbitrage;
    private Button btnEx;
    private Button btnSquare;
    private Button btnXy;
    private Button btnModuli;
    private Button btnPi;
    private Button btnE;
    //private FontFitTextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        setupUIViews();

        displayView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 17) {
                    if (s.length() > 25)
                        displayView.setTextSize(20);
                    else
                        displayView.setTextSize(25);
                }else
                    displayView.setTextSize(35);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCursorWithNumbers("0");
                setResultViewValue();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCursorWithNumbers("1");
                setResultViewValue();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCursorWithNumbers("2");
                setResultViewValue();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCursorWithNumbers("3");
                setResultViewValue();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCursorWithNumbers("4");
                setResultViewValue();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCursorWithNumbers("5");
                setResultViewValue();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCursorWithNumbers("6");
                setResultViewValue();
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCursorWithNumbers("7");
                setResultViewValue();
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCursorWithNumbers("8");
                setResultViewValue();
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCursorWithNumbers("9");
                setResultViewValue();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView.setText("");
                resultView.setText("");
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cursor = displayView.getSelectionStart();
                if (displayView.getText().toString().substring(0,cursor).endsWith(PRECENTAGE)) {
                    changrDisplayTextWithValue(SUBTRACTION, cursor, 1);
                }
                else
                    handleSymbolsInDisplayText(cursor,SUBTRACTION);
                setResultViewValue();
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cursor = displayView.getSelectionStart();
                if (displayView.getText().toString().substring(0,cursor).endsWith(PRECENTAGE)){
                    changrDisplayTextWithValue(ADDITION, cursor, 1);
                }
                else
                    handleSymbolsInDisplayText(cursor,ADDITION);
                setResultViewValue();
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cursor = displayView.getSelectionStart();
                String preString = displayView.getText().toString();
                /*if (!preString.substring(0,cursor).endsWith(CLOSE_BRACKET)) {
                    if (preString.substring(0, cursor).endsWith(PRECENTAGE)) {
                        changrDisplayTextWithValue(DIVISION, cursor, 1);
                    } else
                        handleSymbolsInDisplayText(cursor, DIVISION);
                    setResultViewValue();
                }*/
                if (preString.substring(0, cursor).endsWith(PRECENTAGE)) {
                    changrDisplayTextWithValue(DIVISION, cursor, 1);
                } else
                    handleSymbolsInDisplayText(cursor, DIVISION);
                setResultViewValue();
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cursor = displayView.getSelectionStart();
                String preString = displayView.getText().toString();
                /*if (!preString.substring(0,cursor).endsWith(CLOSE_BRACKET)) {
                    if (preString.substring(0, cursor).endsWith(PRECENTAGE)) {
                        changrDisplayTextWithValue(MULTIPLICATION, cursor, 1);
                    } else
                        handleSymbolsInDisplayText(cursor, MULTIPLICATION);
                    setResultViewValue();
                }*/
                if (preString.substring(0, cursor).endsWith(PRECENTAGE)) {
                    changrDisplayTextWithValue(MULTIPLICATION, cursor, 1);
                } else
                    handleSymbolsInDisplayText(cursor, MULTIPLICATION);
                setResultViewValue();
            }
        });
        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cursor = displayView.getSelectionStart();
                String preString = displayView.getText().toString().substring(0,cursor);
                if (!preString.endsWith(OPEN_BRACKET) && !preString.endsWith(PRECENTAGE))
                    handleSymbolsInDisplayText(cursor,PRECENTAGE);
                setResultViewValue();
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cursor = displayView.getSelectionStart();
                handleDotInDisplayText(cursor);
                setResultViewValue();
            }
        });
        btnPlusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cursor = displayView.getSelectionStart();
                //handleBracketsInDisplayText(cursor);
                setResultViewValue();
            }
        });
        btnBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cursor = displayView.getSelectionStart();
                handleBracketsInDisplayText(cursor);
                setResultViewValue();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cursor = displayView.getSelectionStart();
                clearByBackButton(cursor);
                setResultViewValue();
            }
        });
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayString = displayView.getText().toString();
                if (!resultView.getText().toString().equals("")){
                    displayView.setText(resultView.getText().toString());
                    Editable etext = (Editable) displayView.getText();
                    setSelection(etext, displayView.length());
                    resultView.setText("");
                }else{
                    if (!displayString.matches("-?\\d+(\\.\\d+)?")){
                        showMessage("Invalid format used.");
                    }
                }

            }
        });
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            btn2nd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchAdvanceButtons();
                }
            });
            btnSquareRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cursor = displayView.getSelectionStart();
                    handleAdvancedButInDisplayView(cursor, "cbrt(", "√(");
                }
            });
            btnSin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cursor = displayView.getSelectionStart();
                    handleAdvancedButInDisplayView(cursor, "asin(", "sin(");
                }
            });
            btnCos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cursor = displayView.getSelectionStart();
                    handleAdvancedButInDisplayView(cursor, "acos(", "cos(");
                }
            });
            btnTan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cursor = displayView.getSelectionStart();
                    handleAdvancedButInDisplayView(cursor, "atan(", "tan(");
                }
            });
            btnLn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cursor = displayView.getSelectionStart();
                    handleAdvancedButInDisplayView(cursor, "sinh(", "ln(");
                }
            });
            btnLog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cursor = displayView.getSelectionStart();
                    handleAdvancedButInDisplayView(cursor, "cosh(", "log(");
                }
            });
            btnArbitrage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cursor = displayView.getSelectionStart();
                    handleAdvancedButInDisplayView(cursor, "tanh(", "1÷");
                }
            });
            btnEx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cursor = displayView.getSelectionStart();
                    handleAdvancedButInDisplayView(cursor, "asinh(", "e^(");
                }
            });
            btnModuli.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cursor = displayView.getSelectionStart();
                    handleAdvancedButInDisplayView(cursor, "2^(", "abs(");
                }
            });
            btnE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cursor = displayView.getSelectionStart();
                    handleEInDisplayView(cursor, "", "e");
                    setResultViewValue();
                }
            });
            btnPi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cursor = displayView.getSelectionStart();
                    handlePiInDisplayView(cursor, "", "π");
                    setResultViewValue();
                }
            });

        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("======== Start " + displayView.getSelectionStart());
        System.out.println("======== End " + displayView.getSelectionEnd());
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupUIViews(){
        btn0 = (Button)findViewById(R.id.btn0);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btnSub = (Button)findViewById(R.id.btnSub);
        btnPlus = (Button)findViewById(R.id.btnPlus);
        btnDiv = (Button)findViewById(R.id.btnDiv);
        btnMul = (Button)findViewById(R.id.btnMulti);
        btnEqual = (Button)findViewById(R.id.btnEqual);
        btnDot = (Button)findViewById(R.id.btnDot);
        btnBracket = (Button)findViewById(R.id.btnBracket);
        btnClear = (Button)findViewById(R.id.btnClear);
        btnBack = (Button)findViewById(R.id.btnBack);
        btnMod = (Button)findViewById(R.id.btnMod);
        btnPlusMinus = (Button)findViewById(R.id.btnPlusMinus);

        btn2nd = (Button)findViewById(R.id.btn2nd);
        btnRad = (Button)findViewById(R.id.btnRad);
        btnSquareRoot = (Button)findViewById(R.id.btnSquareRoot);
        btnSin = (Button)findViewById(R.id.btnSin);
        btnCos = (Button)findViewById(R.id.btnCos);
        btnTan = (Button)findViewById(R.id.btnTan);
        btnLn = (Button)findViewById(R.id.btnLn);
        btnLog = (Button)findViewById(R.id.btnLog);
        btnArbitrage = (Button)findViewById(R.id.btnArbitrage);
        btnEx = (Button)findViewById(R.id.btnEx);
        btnSquare = (Button)findViewById(R.id.btnSquare);
        btnXy = (Button)findViewById(R.id.btnXy);
        btnModuli = (Button)findViewById(R.id.btnModuli);
        btnPi = (Button)findViewById(R.id.btnPi);
        btnE = (Button)findViewById(R.id.btnE);

        displayView = (EditText)findViewById(R.id.displayView);
        resultView = (TextView)findViewById(R.id.resultView);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            btnEx.setText(textToSuperscipt(1, 2,"ex"));
            btnSquare.setText(textToSuperscipt(1, 2,"x2"));
            btnXy.setText(textToSuperscipt(1, 2,"xy"));
            switchAdvanceButtons();
        }

        displayView.setCursorVisible(true);
        displayView.setFocusableInTouchMode(true);
        displayView.requestFocus();
        displayView.setEnabled(true);

        System.out.println("========" + displayView.getSelectionStart());
        System.out.println("========" + displayView.getSelectionEnd());

        displayView.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.onTouchEvent(event);
                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return true;
            }
        });
    }

    private void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void changrDisplayTextWithValue(String value, int cursorPosition, int cursorMovement){
        if (!checkDisplayViewMaxLengthReached()) {
            String preString = displayView.getText().toString().equals("") ? "" : displayView.getText().toString().substring(0, cursorPosition);
            preString += value;
            System.out.println("========preString " + preString);
            cursorPosition += cursorMovement;
            System.out.println("========cursorPosition " + cursorPosition);
            System.out.println("========displayView.length() " + displayView.length());
            String postString = cursorPosition > displayView.length() ? "" : displayView.getText().toString().substring(cursorPosition - 1, displayView.length());
            System.out.println("========postString " + postString);
            displayView.setText(preString + postString);
            System.out.println("========preString + postString " + preString + postString);
            //int position = displayView.length();
            Editable etext = (Editable) displayView.getText();
            setSelection(etext, cursorPosition);
        }else {
            showMessage("Maximum number of characters (100) reached.");
        }
    }

    private void moveCursorWithNumbers(String value){
        int cursor = displayView.getSelectionStart();
        String displayString = displayView.getText().toString();
        String preString = EMPTY.equals(displayString)? EMPTY : displayString.substring(0,cursor);
        if (preString.endsWith(CLOSE_BRACKET) || preString.endsWith(PRECENTAGE) || preString.endsWith(PI) || preString.endsWith(E)){
            changrDisplayTextWithValue(MULTIPLICATION + value, cursor, 2);
        }else{
            changrDisplayTextWithValue(value, cursor, 1);
        }
    }

    private void handleSymbolsInDisplayText(int cursorPosition, String symbol){
        String displayString = displayView.getText().toString();
        String preString = displayString.substring(0,cursorPosition);
        String postString = cursorPosition == displayView.length()? "" : displayString.substring(cursorPosition,displayView.length());
        if (preString.equals("")){
            return;
        }
        if (postString.equals("")){
            if (SYMBOLS.contains(String.valueOf(preString.charAt(preString.length()-1)))){
                preString = preString.substring(0, preString.length()-1) + symbol;
                displayView.setText(preString + postString);
                Editable etext = (Editable) displayView.getText();
                setSelection(etext, cursorPosition);
                return;
            }else {
                changrDisplayTextWithValue(symbol, cursorPosition, 1);
                return;
            }
        }
        if(!SYMBOLS.contains(String.valueOf(preString.charAt(preString.length()-1))) &&
                !SYMBOLS.contains(String.valueOf(postString.charAt(0)))){
            changrDisplayTextWithValue(symbol, cursorPosition, 1);
            return;
        }
        if(SYMBOLS.contains(String.valueOf(preString.charAt(preString.length()-1)))){
            preString = preString.substring(0, preString.length()-1) + symbol;
            displayView.setText(preString + postString);
            Editable etext = (Editable) displayView.getText();
            setSelection(etext, cursorPosition);
        }
        else {
            postString = symbol + postString.substring(1, postString.length());
            displayView.setText(preString + postString);
            Editable etext = (Editable) displayView.getText();
            setSelection(etext, cursorPosition);
        }
    }

    private void clearByBackButton(int cursorPosition){
        String displayString = displayView.getText().toString();
        String preString = displayString.substring(0,cursorPosition);
        String postString = cursorPosition == displayView.length()? "" : displayString.substring(cursorPosition,displayView.length());

        if (!preString.equals("")) {
            preString = preString.substring(0, preString.length() - 1);
            displayView.setText(preString + postString);
            Editable etext = (Editable) displayView.getText();
            setSelection(etext, --cursorPosition);
        }
    }

    private void handleBracketsInDisplayText(int cursorPosition){
        int openBracketsCount = 0;
        int closeBracketsCount = 0;
        String displayString = displayView.getText().toString();
        System.out.println("========-----=======cursorPosition :" + cursorPosition);
        String preString = displayString.substring(0,cursorPosition);
        System.out.println("========-----=======cursorPosition :" + preString.length());
        String postString = cursorPosition == displayView.length()? "" : displayString.substring(cursorPosition,displayView.length());
        String lastLetterOfPreString = preString.equals("")? "" : String.valueOf(preString.charAt(preString.length()-1));
        char ch[] =preString.toCharArray();
        for(int i=0;i<preString.length();i++) {
            if(ch[i] == '(') {
                openBracketsCount++;
            }else if(ch[i] == ')'){
                closeBracketsCount++;
            }
        }
        System.out.println("========-----=======openBracketCount :" + preString);
        if (preString.equals("") || lastLetterOfPreString.equals(OPEN_BRACKET)){
            changrDisplayTextWithValue(OPEN_BRACKET, cursorPosition, 1);
            return;
        }
        if (NUMBERS.contains(lastLetterOfPreString)){
            if (openBracketsCount >= 0){
                if (closeBracketsCount < openBracketsCount) {
                    changrDisplayTextWithValue(CLOSE_BRACKET, cursorPosition, 1);
                }else {
                    changrDisplayTextWithValue("×(", cursorPosition, 2);
                }
            }
            return;
        }else {
            if (preString.endsWith(ADDITION) || preString.endsWith(SUBTRACTION) || preString.endsWith(MULTIPLICATION) ||
                    preString.endsWith(DIVISION)){
                changrDisplayTextWithValue(OPEN_BRACKET, cursorPosition, 1);
            }
            else{
                if (closeBracketsCount < openBracketsCount) {
                    changrDisplayTextWithValue(CLOSE_BRACKET, cursorPosition, 1);
                }else {
                    changrDisplayTextWithValue("×(", cursorPosition, 2);
                }
            }
            return;
        }

    }

    private void handlePlusMinusInDisplayView(int cursorPosition){
        String displayString = displayView.getText().toString();
        String preString = displayString.substring(0, cursorPosition);
        String postString = cursorPosition == displayView.length() ? "" : displayString.substring(cursorPosition, displayView.length());
        if (!preString.equals("")) {
            changrDisplayTextWithValue("(-", cursorPosition, 2);
        }
    }

    private void handlePiInDisplayView(int cursorPosition, String firstSymbol, String secondSymbol){
        String displayString = displayView.getText().toString();
        String preString = displayString.substring(0, cursorPosition);
        String postString = cursorPosition == displayView.length() ? "" : displayString.substring(cursorPosition, displayView.length());
        Pattern pattern = Pattern.compile(REGEX2);

        Matcher matcher = !preString.equals("") ? pattern.matcher(preString.substring(preString.length() - 1)) :
                pattern.matcher("");
        boolean matches = matcher.matches();
        if (isSwitch == 2) {
            if (preString.equals("") || matches) {
                changrDisplayTextWithValue(secondSymbol, cursorPosition, secondSymbol.length());
            } else {
                changrDisplayTextWithValue("×" + secondSymbol, cursorPosition, secondSymbol.length() + 1);
            }
        }
    }

    private void handleEInDisplayView(int cursorPosition, String firstSymbol, String secondSymbol){
        String displayString = displayView.getText().toString();
        String preString = displayString.substring(0, cursorPosition);
        String postString = cursorPosition == displayView.length() ? "" : displayString.substring(cursorPosition, displayView.length());
        Pattern pattern = Pattern.compile(REGEX2);

        Matcher matcher = !preString.equals("") ? pattern.matcher(preString.substring(preString.length() - 1)) :
                pattern.matcher("");
        boolean matches = matcher.matches();
        System.out.println("[[[[[[[[[[[[[[[[[[[[" + matches);
        if (isSwitch == 2) {
            if (preString.equals("") || matches) {
                changrDisplayTextWithValue(secondSymbol, cursorPosition, secondSymbol.length());
            } else {
                changrDisplayTextWithValue("×" + secondSymbol, cursorPosition, secondSymbol.length() + 1);
            }
        }
    }

    /*private void handleAdvancedButInDisplayView(int cursorPosition){
        String displayString = displayView.getText().toString();
        String preString = displayString.substring(0, cursorPosition);
        String postString = cursorPosition == displayView.length() ? "" : displayString.substring(cursorPosition, displayView.length());
        Pattern pattern = Pattern.compile(REGEX1);

        Matcher matcher = !preString.equals("") ? pattern.matcher(preString.substring(preString.length() - 1)) :
                pattern.matcher("");
        boolean matches = matcher.matches();
        if (isSwitch == 2) {
            if (preString.equals("")) {
                changrDisplayTextWithValue("√(", cursorPosition, 2);
                return;
            }
            if (matches) {
                changrDisplayTextWithValue("×√(", cursorPosition, 3);
            } else {
                changrDisplayTextWithValue("√(", cursorPosition, 2);
            }
        }else {
            if (preString.equals("")) {
                changrDisplayTextWithValue("cbrt(", cursorPosition, 5);
                return;
            }
            if (matches) {
                changrDisplayTextWithValue("×cbrt(", cursorPosition, 6);
            } else {
                changrDisplayTextWithValue("cbrt(", cursorPosition, 5);
            }
        }
    }
*/
    private void handleAdvancedButInDisplayView(int cursorPosition, String firstSymbol, String secondSymbol){
        String displayString = displayView.getText().toString();
        String preString = displayString.substring(0, cursorPosition);
        String postString = cursorPosition == displayView.length() ? "" : displayString.substring(cursorPosition, displayView.length());
        Pattern pattern = Pattern.compile(REGEX1);

        Matcher matcher = !preString.equals("") ? pattern.matcher(preString.substring(preString.length() - 1)) :
                pattern.matcher("");
        boolean matches = matcher.matches();
        if (isSwitch == 2) {
            if (preString.equals("")) {
                changrDisplayTextWithValue(secondSymbol, cursorPosition, secondSymbol.length());
                return;
            }
            if (matches) {
                changrDisplayTextWithValue("×" + secondSymbol, cursorPosition, secondSymbol.length() + 1);
            } else {
                changrDisplayTextWithValue(secondSymbol, cursorPosition, secondSymbol.length());
            }
        }else {
            if (preString.equals("")) {
                changrDisplayTextWithValue(firstSymbol, cursorPosition, firstSymbol.length());
                return;
            }
            if (matches) {
                changrDisplayTextWithValue("×" + firstSymbol, cursorPosition, firstSymbol.length() + 1);
            } else {
                changrDisplayTextWithValue(firstSymbol, cursorPosition, firstSymbol.length());
            }
        }
    }

    private void handleDotInDisplayText(int cursorPosition) {
        String displayString = displayView.getText().toString();
        String preString = displayString.substring(0, cursorPosition);
        String lastLetterOfPreString = displayView.getText().toString().equals("") ? "" : String.valueOf(preString.charAt(preString.length() - 1));
        if (!preString.equals("")) {
            System.out.println("========--WOW1");
            char ch[] = preString.toCharArray();
            boolean allAreNumbers = true;
            boolean dotFound = false;
            for (int i = preString.length() - 1; i >= 0; i--) {
                String letter = String.valueOf(ch[i]);
                System.out.println("========--letter: " + letter);
                if (letter.equals(".")) {
                    dotFound = true;
                    break;
                } else if (!NUMBERS.contains(letter)) {
                    System.out.println("========--WOW2");
                    allAreNumbers = false;
                    break;
                }
            }
            if (allAreNumbers && dotFound) {
                System.out.println("========--WOW3");
                return;
            }
        }
        if (preString.equals("") || preString.endsWith(OPEN_BRACKET)) {
            changrDisplayTextWithValue("0.", cursorPosition, 2);
            return;
        }
        if (preString.endsWith(")") || lastLetterOfPreString.equals(PRECENTAGE)) {
            changrDisplayTextWithValue("×0.", cursorPosition, 3);
            return;
        }
        if (NUMBERS.contains(lastLetterOfPreString)) {
            System.out.println("========--WOW4");
            changrDisplayTextWithValue(".", cursorPosition, 1);
            return;
        }
        if (SYMBOLS.contains(lastLetterOfPreString)) {
            changrDisplayTextWithValue("0.", cursorPosition, 2);
            return;
        }
    }

    private boolean checkDisplayViewMaxLengthReached(){
        return displayView.length() >= getResources().getInteger(R.integer.edit_text_max_length);
    }

    private void setResultViewValue(){
        if (!compute().equals("invalid")){
            String result = compute();
            System.out.println("========------------result: " + result);
//            int mod = (int) (Double.parseDouble(result) % 10);
            if (result.endsWith(".0")){
                String[] splitString= result.split("\\.");
                resultView.setText(splitString[0]);
            }else {
                resultView.setText(compute());
            }
        }else{
            resultView.setText("");
        }
    }

    private void switchAdvanceButtons(){
        if (isSwitch == 2) {
            isSwitch = 1;
            btn2nd.setText("⇆1st");
            btnSquareRoot.setText(textToSuperscipt(0, 1,"3√x"));
            btnSin.setText(textToSuperscipt(3, 5,"sin-1"));
            btnCos.setText(textToSuperscipt(3, 5,"cos-1"));
            btnTan.setText(textToSuperscipt(3, 5,"tan-1"));
            btnLn.setText("sinh");
            btnLog.setText("cosh");
            btnArbitrage.setText("tanh");
           /* btnEx.setText(textToSuperscipt(4, 6,"sinh-1"));
            btnSquare.setText(textToSuperscipt(4, 6,"cosh-1"));
            btnXy.setText(textToSuperscipt(4, 6,"tanh-1"));*/
            btnModuli.setText(textToSuperscipt(1, 2, "2x"));
            btnPi.setText(textToSuperscipt(1, 2,"x3"));
            btnE.setText("x!");
        }
        else {
            isSwitch = 2;
            btn2nd.setText("⇆2nd");
            btnSquareRoot.setText("√");
            btnSin.setText("sin");
            btnCos.setText("cos");
            btnTan.setText("tan");
            btnLn.setText("ln");
            btnLog.setText("log");
            btnArbitrage.setText("1/x");
            /*btnEx.setText(textToSuperscipt(1, 2,"ex"));
            btnSquare.setText(textToSuperscipt(1, 2,"x2"));
            btnXy.setText(textToSuperscipt(1, 2,"xy"));*/
            btnModuli.setText("|x|");
            btnPi.setText("π");
            btnE.setText("e");
        }
    }

    private SpannableStringBuilder textToSuperscipt(int start, int end, String text){

        SpannableStringBuilder cs = new SpannableStringBuilder(text);
        cs.setSpan(new SuperscriptSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cs.setSpan(new RelativeSizeSpan(0.6f), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return cs;
    }

    private String generateExpression(String displayValue){
        //String expression = "";
        displayValue = displayValue.replace("×","*").replace("÷","/")
                .replace("%","/100").replace("√", "Math.sqrt").replace("asin", "1*180/Math.PI*Math.asin")
                .replace("acos", "1*180/Math.PI*Math.acos").replace("atan", "1*180/Math.PI*Math.atan")
                .replace("sinh", "java.lang.Math.sinh").replace("cosh", "java.lang.Math.cosh")
                .replace("tanh", "java.lang.Math.tanh").replace("cbrt", "Math.cbrt")
                .replace("e^", "Math.exp").replace("abs", "Math.abs").replace("2^(", "Math.pow(2,")
                .replace("π", "Math.PI").replaceAll("\\be\\(\\b", "Math.E")
                .replaceAll("\\bsin\\(\\b", "Math.sin(Math.PI/180*").replaceAll("\\bcos\\(\\b", "Math.cos(Math.PI/180*")
                .replaceAll("\\btan\\(\\b", "Math.tan(Math.PI/180*").replaceAll("\\blog\\b", "java.lang.Math.log10")
                .replaceAll("\\bln\\b", "java.lang.Math.log");
        return displayValue;
    }

    private String compute(){
        String expression = generateExpression(displayView.getText().toString());
        System.out.println("========------------expression: " + expression);
        String regex = "[0-9]+";
        if (expression.matches(regex)){
            return "invalid";
        }
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try{
            Object result = engine.eval(expression);
            System.out.println(result);
            return result.toString();
        }catch(Exception e) {
            e.printStackTrace();
            return "invalid";
        }
    }
}
