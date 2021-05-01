package com.example.datapicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button button;
    private TextView text;
    private int mDay, mMes, mAno;
    private TextView textDia;
    private TextView textMes;
    private TextView textAno;
    private Button buttonTeste;
    private AutoCompleteTextView autoCompleteTextView;



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        String[]option = {"Casa", "Transporte", "Saúde", "Lazer", "Despesas Pessoais"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.option_item, option);
        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false);
        autoCompleteTextView.setAdapter(arrayAdapter);


        text = findViewById(R.id.textView);

        textDia = findViewById(R.id.textDia);
        textMes = findViewById(R.id.textMes);
        textAno = findViewById(R.id.textAno);
        Calendario calendario = new Calendario();

        buttonTeste = findViewById(R.id.buttonTeste);
        button = findViewById(R.id.buttonAbrir);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.get(Calendar.YEAR);
        c.get(Calendar.MONTH);
        c.get(Calendar.DATE);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        text.setText(currentDateString);
        //Método para recuperar valores
        pegarData(year, month, dayOfMonth);
    }

    private void pegarData(int ayear, int amonth, int adayOfMonth) {
        Calendar cal = Calendar.getInstance();
        int diaA = cal.get(Calendar.DATE);
        int mesA = cal.get(Calendar.MONTH);
        int AnoA = cal.get(Calendar.YEAR);

        //Recuperar Valores de dia, mês e ano
        int teste = adayOfMonth;
        int teste1 = amonth;
        int teste2 = ayear;

        textDia.setText("Dia: " + teste);
        textMes.setText("Mês: " + (amonth + 1));
        textAno.setText("Ano: " + (ayear));
    }
}