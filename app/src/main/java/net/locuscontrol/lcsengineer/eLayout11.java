package net.locuscontrol.lcsengineer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Switch;

public class eLayout11 extends AppCompatActivity {

    public TextView area;
    public EditText P ;
    public EditText L ;
    public EditText eD ;
    public  EditText V ;
    public Switch Cu_Al;
    public Switch P1_3;
    public float k=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_layout11);
        area =(TextView) findViewById(R.id.Area);
        P = (EditText) findViewById(R.id.P);
        L = (EditText) findViewById(R.id.L);
        eD = (EditText) findViewById(R.id.eD);
        V = (EditText) findViewById(R.id.V);
        Cu_Al =(Switch) findViewById(R.id.Cu_Al);
        P1_3 = (Switch) findViewById(R.id.P1_3);
        P1_3.setChecked(true);


    }




    public void CableSize(View view){

        //Get the variables
        String PS=P.getText().toString();
        String LS=L.getText().toString();
        String eDS=eD.getText().toString();
        String VS=V.getText().toString();
        Float PN= Float.parseFloat(PS);
        Float LN= Float.parseFloat(LS);
        Float eDN= Float.parseFloat(eDS);
        Float VN= Float.parseFloat(VS);
        Float A;

        //Set Constant depending on selection

        if (Cu_Al.isChecked()){
            k=35;
            //area.setText("Checked - 1");

        }else{
            k=56;
            //area.setText("NotChecked - 0");
        }

        if (PS=="" || LS=="" || eDS=="" || VS=="" ){
            area.setTextColor(getResources().getColor(R.color.Locus_Red));
            area.setText("Please Fill all the fields to calculate");
        }
        else{
            if (P1_3.isChecked()) {
                A=(100*PN*LN)/(k*eDN*VN*VN);
                String areaC=String.format("%.2f", A);
                area.setTextColor(getResources().getColor(R.color.Locus_Blue));
                area.setText("The calculated size of cable is " + areaC +" mm.");

            }
            else{
                A=(200*PN*LN)/(k*eDN*VN*VN);
                String areaC=String.format("%.2f", A);
                area.setTextColor(getResources().getColor(R.color.Locus_Blue));
                area.setText("The calculated size of cable is " + areaC +" mm.");

            }
        }


    }

}
