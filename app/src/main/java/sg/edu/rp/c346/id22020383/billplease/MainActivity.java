package sg.edu.rp.c346.id22020383.billplease;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplayAmount;
    EditText amountInput;
    TextView tvDisplayPax;
    EditText paxInput;
    ToggleButton svsButton;
    ToggleButton gstButton;
    TextView tvDisplayDiscount;
    EditText discountInput;
    RadioGroup rgGroupPaymentType;
    RadioButton rgButtonCash;
    Button splitButton;
    Button resetButton;
    TextView billView;
    TextView eachPaysView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplayAmount = findViewById(R.id.amountView);
        amountInput = findViewById(R.id.amountEdit);

        tvDisplayPax = findViewById(R.id.paxView);
        paxInput = findViewById(R.id.paxEdit);

        svsButton = findViewById(R.id.svstbtn);
        gstButton = findViewById(R.id.gsttbtn);

        tvDisplayDiscount = findViewById(R.id.discountView);
        discountInput = findViewById(R.id.discountEdit);

        rgGroupPaymentType = findViewById(R.id.radioGroupPaymentType);

        rgButtonCash = findViewById(R.id.radioButtonC);

        splitButton = findViewById(R.id.splitBtn);
        resetButton = findViewById(R.id.resetBtn);

        billView = findViewById(R.id.billTv);
        eachPaysView = findViewById(R.id.eachpaysTv);



        splitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String paxnum = paxInput.getText().toString();
                double doublePax = Double.parseDouble(paxnum);
                String strAmt = amountInput.getText().toString();
                double doubleAmt = Double.parseDouble(strAmt);
                String strDis = discountInput.getText().toString();
                double doubleDis = Double.parseDouble(strDis);
                double SVS = 1.10;
                double GST = 1.07;
                double total = 0.0;
                if (svsButton.isChecked() && gstButton.isChecked()){
                    total = doubleAmt * (1 - doubleDis / 100) * SVS * GST;
                } else if (gstButton.isChecked()) {
                    total = doubleAmt * (1 - doubleDis / 100) * GST;
                } else if (svsButton.isChecked()) {
                    total = doubleAmt * (1 - doubleDis / 100) * SVS;
                } else {
                    total = doubleAmt * (1 - doubleDis / 100);
                }
                double each = total / doublePax;
                String printTotal = String.format("%.2f", total);
                String printEach = String.format("%.2f", each);
                String paymentMethod = rgButtonCash.isChecked() ? " in cash" : " via PayNow to 12345678";
                billView.setText("Total Bill: $" + printTotal);
                eachPaysView.setText("Each pays: $" + printEach + paymentMethod);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountInput.setText("");
                paxInput.setText("");
                svsButton.setChecked(false);
                gstButton.setChecked(false);
                discountInput.setText("");
                rgGroupPaymentType.clearCheck();
                billView.setText("");
            }
        });
    }
}