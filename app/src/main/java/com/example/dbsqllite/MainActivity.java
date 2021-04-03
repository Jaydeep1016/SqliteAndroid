package com.example.dbsqllite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper db=new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void TAP(View v){
        TextView TitelShow=findViewById(R.id.txtTitel);
        EditText edtSno=findViewById(R.id.intSno);
        EditText edtSName=findViewById(R.id.intSName);
        EditText edtSage=findViewById(R.id.intSage);
        TextView sd=findViewById(R.id.txtShow);

        String Sno=edtSno.getText().toString();
        String Sname=edtSName.getText().toString();
        String Sage=edtSage.getText().toString();

        Button btnViewAll=findViewById(R.id.btnView);
        Button btnRegister=findViewById(R.id.btnInsert);
        Button btnLogin=findViewById(R.id.btnLogin);
        Button btnClear=findViewById(R.id.btnClear);
        Button btnRF=findViewById(R.id.btnRF);
        Button btnUpdate=findViewById(R.id.btnUpdate);
        Button btnDelete=findViewById(R.id.btnDelete);
        Button btnLogOut=findViewById(R.id.btnLgOut);
        Button btnMain=findViewById(R.id.btnMain);

        ScrollView SView=findViewById(R.id.bgScroll);
        boolean b = !Sno.equals("") && !Sname.equals("") && !Sage.equals("");

        switch (v.getId()){

            case  R.id.btnMain:
                TitelShow.setText("Main Page");
                btnLogin.setVisibility(View.VISIBLE);
                btnRF.setVisibility(View.VISIBLE);
                btnMain.setVisibility(View.GONE);
                edtSno.setEnabled(true);
                edtSName.setEnabled(true);
                edtSage.setEnabled(true);
                edtSage.setText("");
                edtSName.setText("");
                edtSno.setText("");
                SView.setBackgroundColor(Color.parseColor("#FFC107"));

                Toast.makeText(this, "Main Activity", Toast.LENGTH_SHORT).show();
                break;

            case  R.id.btnUpdate:
                if(b) {
                    Boolean F = db.Update(Sno, Sname, Sage);
                    if(F) {

                        TitelShow.setText("View Page");
                        btnViewAll.setVisibility(View.GONE);
                        btnUpdate.setVisibility(View.GONE);
                        btnDelete.setVisibility(View.GONE);
                        btnLogOut.setVisibility(View.GONE);
                        btnMain.setVisibility(View.VISIBLE);
                        edtSno.setEnabled(false);
                        edtSName.setEnabled(false);
                        edtSage.setEnabled(false);
                        SView.setBackgroundColor(Color.parseColor("#FFC107"));

                        Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(this, "Error in DB", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Enter All Data", Toast.LENGTH_SHORT).show();
                }
                break;

                case  R.id.btnDelete:


                    if(b) {
                        Boolean F=db.Delete(Sno);
                        if(F) {

                            TitelShow.setText("Delete Data");
                            btnViewAll.setVisibility(View.GONE);
                            btnUpdate.setVisibility(View.GONE);
                            btnDelete.setVisibility(View.GONE);
                            btnLogOut.setVisibility(View.GONE);
                            btnMain.setVisibility(View.VISIBLE);
                            edtSno.setEnabled(false);
                            edtSName.setEnabled(false);
                            edtSage.setEnabled(false);
                            Toast.makeText(this, "Delete Success", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(this, "Error in DB", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "Enter All Data", Toast.LENGTH_SHORT).show();
                    }


                break;
                case  R.id.btnLgOut:
                btnLogin.setVisibility(View.VISIBLE);
                btnRF.setVisibility(View.VISIBLE);
                btnUpdate.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);
                btnLogOut.setVisibility(View.GONE);

                edtSno.setEnabled(true);
                edtSName.setEnabled(true);
                edtSage.setEnabled(true);
                edtSage.setText("");
                edtSName.setText("");
                edtSno.setText("");
                TitelShow.setText("Login Page");
                Toast.makeText(this, "LogOut Success", Toast.LENGTH_SHORT).show();
                break;

            case  R.id.btnRF:
                btnRegister.setVisibility(View.VISIBLE);
                TitelShow.setText(btnRegister.getText().toString());
                SView.setBackgroundColor(Color.parseColor("#23C4ED"));
                btnClear.setVisibility(View.GONE);
                btnLogin.setVisibility(View.GONE);
                btnClear.setVisibility(View.GONE);
                btnRF.setVisibility(View.GONE);
                break;


            case R.id.btnInsert:
                if(b) {
                    Boolean F = db.insertDone(Sno, Sname, Sage);
                    if(F) {
                        Toast.makeText(this, "Insert Done", Toast.LENGTH_SHORT).show();
                        btnViewAll.setVisibility(View.GONE);
                        btnUpdate.setVisibility(View.GONE);
                        btnDelete.setVisibility(View.GONE);
                        btnLogOut.setVisibility(View.GONE);
                        btnMain.setVisibility(View.VISIBLE);
                        edtSno.setEnabled(false);
                        edtSName.setEnabled(false);
                        edtSage.setEnabled(false);

                    }else
                    {
                        Toast.makeText(this, "Error in DB", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Enter All Data", Toast.LENGTH_SHORT).show();
                }
                break;

                case R.id.btnView:
                    db.ShowAll(sd);
                Toast.makeText(this, "View Done", Toast.LENGTH_SHORT).show();
                break;

                case R.id.btnClear:
                sd.setText("");
                Toast.makeText(this, "Data is Clear", Toast.LENGTH_SHORT).show();
                break;

                case R.id.btnLogin:
                    if(b){
                        Boolean F=db.Login(Sno, Sname, Sage);
                        if(F) {
                            TitelShow.setText("Edit Data");
                            btnLogin.setVisibility(View.GONE);
                            btnRF.setVisibility(View.GONE);
                            btnViewAll.setVisibility(View.VISIBLE);
                            btnUpdate.setVisibility(View.VISIBLE);
                            btnDelete.setVisibility(View.VISIBLE);
                            btnLogOut.setVisibility(View.VISIBLE);
                            edtSno.setEnabled(false);

                            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
                        }
                }else{
                        Toast.makeText(this, "Enter All Data", Toast.LENGTH_SHORT).show();
                    }
                break;
        }

    }
}