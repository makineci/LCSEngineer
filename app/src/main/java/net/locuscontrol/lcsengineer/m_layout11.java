package net.locuscontrol.lcsengineer;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class m_layout11 extends AppCompatActivity {

    private Spinner w_c, coil, kvs_s;
    private EditText Tin, Tout, DP, Cap, FR, kvs_c, DP_C;
    private double Capasity, DT, Flow, kvs_cN, kvs_sN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_layout11);

        // Assign the Spinners
        w_c = (Spinner) findViewById(R.id.w_c);
        coil = (Spinner) findViewById(R.id.coil);
        kvs_s = (Spinner) findViewById(R.id.kvs_s);

        w_c.setSelection(((ArrayAdapter) w_c.getAdapter()).getPosition("kW"));
        coil.setSelection(((ArrayAdapter) coil.getAdapter()).getPosition("Heating"));

        //Assign the EditText
        Tin = (EditText) findViewById(R.id.Tin);
        Tout = (EditText) findViewById(R.id.Tout);
        DP = (EditText) findViewById(R.id.DP);
        Cap = (EditText) findViewById(R.id.Cap);
        FR = (EditText) findViewById(R.id.FR);
        kvs_c = (EditText) findViewById(R.id.kvs_c);
        DP_C = (EditText) findViewById(R.id.DP_C);
        Cap.setText("10.5");

        Tin.setText("90");
        Tout.setText("70");
        DP.setText("6");

// Calculate the Kvs and select the KVS close, after calculate the DP depending on selected kVS

        DoJob();


        Cap.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            if(s.length()!=0){
               DoJob();

            }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Tin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.length()!=0){
                    DoJob();

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Tout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.length()!=0){
                    DoJob();

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        DP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.length()!=0){
                    DoJob();

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        w_c.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DoJob();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        coil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (coil.getSelectedItem().toString().equals("Heating")){
                    Tin.setText("90");
                    Tout.setText("70");
                    DP.setText("6");
                    DoJob();

                }else if (coil.getSelectedItem().toString().equals("Cooling")){
                    Tin.setText("7");
                    Tout.setText("12");
                    DP.setText("12");
                    DoJob();
                }else{
                    Tin.setText("90");
                    Tout.setText("70");
                    DP.setText("30");
                    DoJob();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        kvs_s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kvsselect(kvs_s.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }




    void DoJob(){
// Capasity Calculation
        if (w_c.getSelectedItem().toString().equals("kW")) {
            Double cons = 860.425;
            Double CapKw = Double.parseDouble(Cap.getText().toString());
            Capasity = CapKw * cons;
        } else {
            Capasity = Double.parseDouble(Cap.getText().toString());
        }

        // Flow Rate Calculation
        Double TinD = Double.parseDouble(Tin.getText().toString());
        Double ToutD = Double.parseDouble(Tout.getText().toString());

        if (TinD > ToutD) {
            DT = TinD - ToutD;
        } else {
            DT = ToutD - TinD;
        }
        Flow = Capasity / DT;
        FR.setText(String.format("%.2f", Flow));

// KVS Calculation kvs= (FR/1000)/sqrt(DP/100)

        kvs_cN = (Flow / 1000) / Math.sqrt(Double.parseDouble(DP.getText().toString()) / 100);
        kvs_c.setText(String.format("%.2f", kvs_cN));

        //select the proper kvs from list
        String kvstext = findkvs(kvs_cN);
        kvs_s.setSelection(((ArrayAdapter) kvs_s.getAdapter()).getPosition(kvstext));
        kvstext=kvstext.replace(",",".");
        kvs_sN = Double.parseDouble(kvstext);
        Double Dp_c = (Flow*Flow)/(10000*kvs_sN*kvs_sN);
        DP_C.setText(String.format("%.2f", Dp_c));

    }

    private String findkvs(Double KVS_C) {
        String KVS_S = "0";

        if (KVS_C <= 1) {
            if (KVS_C <= 0.16) {
                KVS_S = "0,16";
            } else if (KVS_C > 0.16 && KVS_C <= 0.25) {
                if ((KVS_C / 0.16) < (0.25 / KVS_C)) {
                    KVS_S = "0,16";
                } else {
                    KVS_S = "0,25";
                }
            } else if (KVS_C > 0.25 && KVS_C <= 0.40) {
                if ((KVS_C / 0.25) < (0.40 / KVS_C)) {
                    KVS_S = "0,25";
                } else {
                    KVS_S = "0,4";
                }
            } else if (KVS_C >= 0.4 && KVS_C <= 0.63) {
                if ((KVS_C / 0.4) < (0.63 / KVS_C)) {
                    KVS_S = "0,4";
                } else {
                    KVS_S = "0,63";
                }
            } else {
                if ((KVS_C / 0.63) < (1 / KVS_C)) {
                    KVS_S = "0,63";
                } else {
                    KVS_S = "1";
                }
            }

        } else if (KVS_C > 1 && KVS_C <= 10) {
            if (KVS_C > 1 && KVS_C <= 1.6) {
                if ((KVS_C / 1) < (1.6 / KVS_C)) {
                    KVS_S = "1";
                } else {
                    KVS_S = "1,6";
                }
            } else if (KVS_C > 1.6 && KVS_C <= 2.5) {
                if ((KVS_C / 1.6) < (2.5 / KVS_C)) {
                    KVS_S = "1,6";
                } else {
                    KVS_S = "2,5";
                }
            } else if (KVS_C > 2.5 && KVS_C <= 4) {
                if ((KVS_C / 2.5) < (4 / KVS_C)) {
                    KVS_S = "2,5";
                } else {
                    KVS_S = "4";
                }
            } else if (KVS_C >= 4 && KVS_C <= 6.3) {
                if ((KVS_C / 4) < (6.3 / KVS_C)) {
                    KVS_S = "4";
                } else {
                    KVS_S = "6,3";
                }
            } else {
                if ((KVS_C / 6.3) < (10 / KVS_C)) {
                    KVS_S = "6,3";
                } else {
                    KVS_S = "10";
                }
            }

        } else if (KVS_C > 10 && KVS_C <= 100) {
            if (KVS_C > 10 && KVS_C <= 16) {
                if ((KVS_C / 10) < (16 / KVS_C)) {
                    KVS_S = "10";
                } else {
                    KVS_S = "16";
                }
            } else if (KVS_C > 16 && KVS_C <= 25) {
                if ((KVS_C / 16) < (25 / KVS_C)) {
                    KVS_S = "16";
                } else {
                    KVS_S = "25";
                }
            } else if (KVS_C > 25 && KVS_C <= 40) {
                if ((KVS_C / 25) < (40 / KVS_C)) {
                    KVS_S = "25";
                } else {
                    KVS_S = "40";
                }
            } else if (KVS_C >= 40 && KVS_C <= 63) {
                if ((KVS_C / 40) < (63 / KVS_C)) {
                    KVS_S = "40";
                } else {
                    KVS_S = "63";
                }
            } else {
                if ((KVS_C / 63) < (100 / KVS_C)) {
                    KVS_S = "63";
                } else {
                    KVS_S = "100";
                }
            }
        } else {
            if (KVS_C > 100 && KVS_C <= 160) {
                if ((KVS_C / 100) < (160 / KVS_C)) {
                    KVS_S = "100";
                } else {
                    KVS_S = "160";
                }
            } else if (KVS_C > 160 && KVS_C <= 250) {
                if ((KVS_C / 160) < (250 / KVS_C)) {
                    KVS_S = "160";
                } else {
                    KVS_S = "250";
                }
            } else if (KVS_C > 250) {
                if ((KVS_C / 250) < (360 / KVS_C)) {
                    KVS_S = "250";
                } else {
                    KVS_S = "360";
                }
            }
        }
        return KVS_S;
    }

    void kvsselect(String ktxt){

        kvs_s.setSelection(((ArrayAdapter) kvs_s.getAdapter()).getPosition(ktxt));
        ktxt=ktxt.replace(",",".");
        kvs_sN = Double.parseDouble(ktxt);
        Double Dp_c = (Flow*Flow)/(10000*kvs_sN*kvs_sN);
        DP_C.setText(String.format("%.2f", Dp_c));
    }

}