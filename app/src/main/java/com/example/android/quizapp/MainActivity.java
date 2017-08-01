package com.example.android.quizapp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.android.quizapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    int score = 0;
    ActivityMainBinding myBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }

    public void submitQuiz(View view){
        checkBoxQuestion();
        radioGroupQuestions();
        String message = "";
        if (score == 5){
            message = getResources().getString(R.string.grade_all_correct, getUserName());
        }else{
            message = getResources().getString(R.string.grade_msg, getUserName(), score);
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        //reset values
        score = 0;
    }

    private String getUserName(){
        EditText nameText = myBinding.editTextUsername;
        String name = nameText.getText().toString();
        return name;
    }

    private void checkBoxQuestion(){
        CheckBox checkBoxAnswer_1_1 = myBinding.answer11;
        CheckBox checkBoxAnswer_1_2 = myBinding.answer12;
        CheckBox checkBoxAnswer_1_3 = myBinding.answer13;
        // Assign a point only if the user checked the correct answers and left unchecked the wrong one
        if (checkBoxAnswer_1_1.isChecked() && checkBoxAnswer_1_2.isChecked() && !checkBoxAnswer_1_3.isChecked()){
            score += 1;
        }
        // reset the checkBoxes
        checkBoxAnswer_1_1.setChecked(false);
        checkBoxAnswer_1_2.setChecked(false);
        checkBoxAnswer_1_3.setChecked(false);
    }

    private void radioGroupQuestions(){
        int index = 0;
        //Question 2
        RadioGroup radioGroupQuestion_2 = myBinding.radioGroupQuestion2;
        index = getSelectedAnswerIndex(radioGroupQuestion_2);
        if (index == 2){
            score += 1;
        }
        //Question 3
        RadioGroup radioGroupQuestion_3 = myBinding.radioGroupQuestion3;
        index = getSelectedAnswerIndex(radioGroupQuestion_3);
        if (index == 1){
            score += 1;
        }
        //Question 4
        RadioGroup radioGroupQuestion_4 = myBinding.radioGroupQuestion4;
        index = getSelectedAnswerIndex(radioGroupQuestion_4);
        if (index == 0){
            score += 1;
        }
        //Question 5
        RadioGroup radioGroupQuestion_5 = myBinding.radioGroupQuestion5;
        index = getSelectedAnswerIndex(radioGroupQuestion_5);
        if (index == 1){
            score += 1;
        }
        // reset the radiobuttons
        radioGroupQuestion_2.clearCheck();
        radioGroupQuestion_3.clearCheck();
        radioGroupQuestion_4.clearCheck();
        radioGroupQuestion_5.clearCheck();
    }

    private int getSelectedAnswerIndex(RadioGroup radioGroup){
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        int index = radioGroup.indexOfChild(radioButton);
        return index;
    }
}