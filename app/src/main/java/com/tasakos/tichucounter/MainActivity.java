package com.tasakos.tichucounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements DialogActivity.ExampleDialogListener {
    private int pnt1=0;
    private int pnt2=0;
    private int wp=0;
    private boolean winning=false;
    private TextView tv1,tv2,points2,points1,n1,n2 ;
    private Switch s1,s2,s3,s4,s6,s7 ;
    private CheckBox c1,c2,c3,c4 ;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.winpts:
                System.out.println("hi");
                openDialog(null,"Set Points", R.layout.points);
                winning=true;
                return true;
            case R.id.name1:
                openDialog(n1,"Team 1", R.layout.name);
                return true;
            case R.id.name2:
                openDialog(n2,"Team 1", R.layout.name);
                return true;
            case R.id.newgame:
                        tv1.setText("");
                        tv2.setText("");
                        s1.setChecked(false);
                        s2.setChecked(false);
                        s3.setChecked(false);
                        s4.setChecked(false);
                        s6.setChecked(false);
                        s7.setChecked(false);
                        c1.setChecked(false);
                        c2.setChecked(false);
                        c3.setChecked(false);
                        c4.setChecked(false);
                        points1.setText("");
                        points2.setText("");
                        return true;
             default:
                 return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n1 = (TextView) findViewById(R.id.textView);
        n2 = (TextView) findViewById(R.id.textView2);
        tv1 = (TextView) findViewById(R.id.tvp1);
        tv2 = (TextView) findViewById(R.id.tvp2);
        points2 = (EditText) findViewById(R.id.pointsst);
        points1 = (EditText) findViewById(R.id.pointsft);
        s1 = (Switch) findViewById(R.id.switch1);
        s2 = (Switch) findViewById(R.id.switch2);
        s3 = (Switch) findViewById(R.id.switch3);
        s4 = (Switch) findViewById(R.id.switch4);
        s7 = (Switch) findViewById(R.id.switch7);
        s6 = (Switch) findViewById(R.id.switch6);
        c1 = (CheckBox) findViewById(R.id.checkBox);
        c2 = (CheckBox) findViewById(R.id.checkBox2);
        c3 = (CheckBox) findViewById(R.id.checkBox3);
        c4 = (CheckBox) findViewById(R.id.checkBox4);
        Calculate();
        n1.setText(getString(R.string.team_1));
        n2.setText(getString(R.string.team_2));

    }



    private void openDialog(TextView n, String title, int layoutId){

            DialogActivity dialogActivity = new DialogActivity(n,title,layoutId);
            dialogActivity.show(getSupportFragmentManager(), "example dialog");

    }

    @Override
    public void applyTexts(String name, TextView n) {
        n.setText(name);

    }

    @Override
    public void setPoints(int points) {
        wp=points;
    }

    private void Calculate(){
        Button addbtn = (Button) findViewById(R.id.addbtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (s1.isChecked()) {
                    pnt1+=200;
                    points1.setText("0");
                    points2.setText("0");
                }
                if (s4.isChecked()){
                    pnt2+=200;
                    points1.setText("0");
                    points2.setText("0");
                }


                if (s2.isChecked() && c1.isChecked()){
                    pnt1+=100;
                    points1.setText("0");
                    points2.setText("0");
                }
                else if(s2.isChecked() && !c1.isChecked()){
                    pnt1-=100;
                    points1.setText("0");
                    points2.setText("0");
                }

                if (s3.isChecked() && c2.isChecked()){
                    pnt1+=200;
                    points1.setText("0");
                    points2.setText("0");
                }
                else if(s3.isChecked() && !c2.isChecked()){
                    pnt1-=200;
                    points1.setText("0");
                    points2.setText("0");
                }

                if (s7.isChecked() && c3.isChecked()){
                    pnt2+=100;
                    points1.setText("0");
                    points2.setText("0");
                }
                else if(s7.isChecked() && !c3.isChecked()){
                    pnt2-=100;
                    points1.setText("0");
                    points2.setText("0");
                }

                if (s6.isChecked() && c4.isChecked()){
                    pnt2+=200;
                    points1.setText("0");
                    points2.setText("0");
                }
                else if(s6.isChecked() && !c4.isChecked()){
                    pnt2-=200;
                    points1.setText("0");
                    points2.setText("0");
                }





                if (points1.getText().length()!=0 && points2.getText().length()==0){
                    pnt1+=Integer.parseInt(points1.getText().toString());
                    pnt2+=100-Integer.parseInt(points1.getText().toString());
                }
                else if (points1.getText().length()==0 && points2.getText().length()!=0){
                    pnt1+=100-Integer.parseInt(points2.getText().toString());
                    pnt2+=Integer.parseInt(points2.getText().toString());
                }
                else if (points1.getText().length()!=0 && points2.getText().length()!=0){
                    pnt1+=Integer.parseInt(points1.getText().toString());
                    pnt2+=Integer.parseInt(points2.getText().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please insert text!", Toast.LENGTH_SHORT).show();
                }


                if (!tv1.getText().equals("") && !tv2.getText().equals("")){
                    pnt1+=Integer.parseInt(tv1.getText().toString());
                    pnt2+=Integer.parseInt(tv2.getText().toString());
                }

                tv1.setText(String.valueOf(pnt1));tv2.setText(String.valueOf(pnt2));


                if (!tv1.getText().equals("")){
                    int x = Integer.parseInt(tv1.getText().toString());
                    int x2 = Integer.parseInt(tv2.getText().toString());
                    if (winning){
                        if (x>=wp){
                            Toast.makeText(getApplicationContext(),"Team 1 wins!", Toast.LENGTH_LONG).show();
                        }
                    else if(x2>=wp){
                        Toast.makeText(getApplicationContext(),"Team 2 wins!", Toast.LENGTH_LONG).show();
                    }
                    }
                }

                pnt1=0;
                pnt2=0;
                s1.setChecked(false);
                s2.setChecked(false);
                s3.setChecked(false);
                s4.setChecked(false);
                s6.setChecked(false);
                s7.setChecked(false);
                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                points1.setText("");
                points2.setText("");

                //System.out.println();
            }
        });
    }

}
