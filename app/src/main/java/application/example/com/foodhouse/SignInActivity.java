package application.example.com.foodhouse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import application.example.com.foodhouse.Common.Common;
import application.example.com.foodhouse.Model.User;

public class SignInActivity extends AppCompatActivity {
    private EditText password, number;
    private Button signIn;
    private DatabaseReference mDatabasReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        password= (MaterialEditText) findViewById(R.id.edit_password);
        number= (MaterialEditText) findViewById(R.id.edit_phone);
        signIn= (Button) findViewById(R.id.buttonSignin_act);
        mDatabasReference= FirebaseDatabase.getInstance().getReference("User");
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog=new ProgressDialog(SignInActivity.this);
                mDialog.setMessage("Please Wait...");
                mDialog.show();
                mDatabasReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(number.getText().toString()).exists()) {
                            mDialog.dismiss();
                            User user = dataSnapshot.child(number.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(password.getText().toString())) {
                                Toast.makeText(SignInActivity.this, "Sign In Successfully !", Toast.LENGTH_SHORT).show();
                                Intent homeIntent=new Intent(SignInActivity.this,Home.class);
                                Common.currentUser=user;
                                startActivity(homeIntent);
                                finish();

                            } else {
                                Toast.makeText(SignInActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            mDialog.dismiss();
                            Toast.makeText(SignInActivity.this,"User not exists in Database",Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
