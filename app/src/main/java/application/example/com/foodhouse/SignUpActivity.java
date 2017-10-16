package application.example.com.foodhouse;

import android.app.ProgressDialog;
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

import application.example.com.foodhouse.Model.User;

public class SignUpActivity extends AppCompatActivity {
    private EditText etPassword, etName, etNumber;
    private Button signUp;
    private DatabaseReference mDatabasReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etPassword=(MaterialEditText)findViewById(R.id.edit_password_signup);
        etName=(MaterialEditText)findViewById(R.id.edit_name_signup);
        etNumber=(MaterialEditText)findViewById(R.id.edit_phone_signup);
        signUp= (Button) findViewById(R.id.buttonSignup_act);
        mDatabasReference= FirebaseDatabase.getInstance().getReference("User");
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog=new ProgressDialog(SignUpActivity.this);
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();
                mDatabasReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(etNumber.getText().toString()).exists()){
                            progressDialog.dismiss();
                            Toast.makeText(SignUpActivity.this,"User already Exists..",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            progressDialog.dismiss();
                            User user=new User(etName.getText().toString(),etPassword.getText().toString());
                            mDatabasReference.child(etNumber.getText().toString()).setValue(user);
                            Toast.makeText(SignUpActivity.this,"Sign Up Successfully..",Toast.LENGTH_SHORT).show();
                            finish();

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
