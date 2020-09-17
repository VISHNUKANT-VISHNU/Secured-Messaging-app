package com.project_VDNRV.securedmessaging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewMessage extends AppCompatActivity {

    private String current = CurrentUser.getInstance().getCodeName();
    private String person = CurrentUser.getInstance().getData();
    private String q1 = CurrentUser.getInstance().getSec_que1();
    private String q2 = CurrentUser.getInstance().getSec_que2();
    private String q3 = CurrentUser.getInstance().getSec_que3();
    private String m = CurrentUser.getInstance().getMes();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String key = CurrentUser.getInstance().getKey();

    public TextView question1,question2,question3,message;
    public TextView answer1,answer2,answer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);


        DatabaseReference databaseReference = database.getReference().child("Users").child(current).child("Sent").child(person);
        question1 = findViewById(R.id.decryption_question1_v);
        question1.setText(q1);
        question1.setVisibility(View.VISIBLE);
        question2 = findViewById(R.id.decryption_question2_v);
        question2.setText(q2);
        question2.setVisibility(View.INVISIBLE);
        question3 = findViewById(R.id.decryption_question3_v);
        question3.setText(q3);
        question3.setVisibility(View.INVISIBLE);
        message=findViewById(R.id.decryption_message_v);
        message.setText(m);
        answer1 = findViewById(R.id.decryption_answer1_v);
        answer1.setVisibility(View.VISIBLE);
        answer2 = findViewById(R.id.decryption_answer2_v);
        answer2.setVisibility(View.INVISIBLE);
        answer3 = findViewById(R.id.decryption_answer3_v);
        answer3.setVisibility(View.INVISIBLE);
        final ProgressBar pb = findViewById(R.id.progressBar3);
        pb.setVisibility(View.VISIBLE);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if (key.equals(dataSnapshot.getKey())) {
                    pb.setVisibility(View.INVISIBLE);
                    Message m = dataSnapshot.getValue(Message.class);
                    String correct_answer1 = m.ANSWER1;
                    String correct_answer2 = m.ANSWER2;
                    String correct_answer3 = m.ANSWER3;
                    question1.setText("Question 1:    "+m.QUESTION1);
                    answer1.setText("Answer :    "+correct_answer1);
                    question2.setText("Question 2:    "+m.QUESTION2);
                    question3.setText("Question 3:    "+m.QUESTION3);
                    answer2.setText("Answer :    "+m.ANSWER2);
                    answer3.setText("Answer :    "+m.ANSWER3);
                    if(!correct_answer2.isEmpty()) {
                        answer2.setText("Answer:    "+correct_answer2);
                        answer2.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        answer2.setVisibility(View.INVISIBLE);
                    }
                    if(!correct_answer3.isEmpty()) {
                        answer3.setText("Answer :    "+correct_answer3);
                        answer3.setVisibility(View.VISIBLE);
                    }else
                        answer3.setVisibility(View.INVISIBLE);
                    if(!m.QUESTION2.isEmpty()) {
                        question2.setText("Question 2:    "+m.QUESTION2);
                        question2.setVisibility(View.VISIBLE);
                    }
                    else
                    question2.setVisibility(View.INVISIBLE);
                    if(!m.QUESTION3.isEmpty()) {
                        question3.setText("Question 3:    "+m.QUESTION3);
                        question3.setVisibility(View.VISIBLE);
                    }
                    else
                        question3.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
