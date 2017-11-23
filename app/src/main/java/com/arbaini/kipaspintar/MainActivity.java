package com.arbaini.kipaspintar;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arbaini.kipaspintar.pojo.GraphTemp;
import com.arbaini.kipaspintar.pojo.StatusPower;
import com.arbaini.kipaspintar.pojo.TempPojo;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.AnimateGifMode;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private Button mButtonOn,mButtonOff;
    private TextView tvState,tvTemp;
    private ImageView imgLamp;
    APIInterfaces apiInterface;
    private int idChanel;

    private ArrayList<GraphTemp> data;


    ArrayList yAxis;
    ArrayList yValues;
    ArrayList xAxis1;
    BarEntry values ;
    BarChart chart;
    LineChart lineChart;


    BarData bardata;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        idChanel = 369167;

        mButtonOn = (Button) findViewById(R.id.btn_on);
        mButtonOff = (Button) findViewById(R.id.btn_off);
        tvState = (TextView) findViewById(R.id.textView);
        tvTemp = (TextView) findViewById(R.id.tv_temp);
        imgLamp = (ImageView) findViewById(R.id.img_lamp);
        lineChart = (LineChart) findViewById(R.id.chart);

        apiInterface = APIClient.getClient().create(APIInterfaces.class);
        updateTemp();
        loadJSON();


    }


    public void lampOn(View v){



        Call call1 = apiInterface.setPower("GFZDBQZWVWTKZ8BT","ON");
        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String ok = "OK";
                Log.v("RESPON:",response.message().toString());
                if(ok.equals(response.message().toString())){

                    StatusPower statusPower = (StatusPower) response.body();
                    String statusONOFF = statusPower.commandString.toString();
                    Ion.with(imgLamp)
                            .error(R.drawable.kipasdiam)
                            .animateGif(AnimateGifMode.ANIMATE)
                            .load("android.resource://" + getPackageName() + "/" + R.drawable.kipasputar)
                            .withBitmapInfo();

                    tvState.setText("Nyala");
                    //imgLamp.setImageResource(R.drawable.lampuon);

                    updateTemp();

                    Toast.makeText(getApplicationContext(),statusONOFF,Toast.LENGTH_SHORT).show();




                }else{
                    Toast.makeText(getApplicationContext(),"Login gagal",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }


    public void lampOff(View v){



        Call call1 = apiInterface.setPower("GFZDBQZWVWTKZ8BT","OFF");
        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String ok = "OK";
                Log.v("RESPON:",response.message().toString());
                if(ok.equals(response.message().toString())){

                    StatusPower statusPower = (StatusPower) response.body();
                    String statusONOFF = statusPower.commandString.toString();

                    tvState.setText("Mati");
                   // imgLamp.setImageResource(R.drawable.lampuoff);


                    Toast.makeText(getApplicationContext(),statusONOFF,Toast.LENGTH_SHORT).show();

                    updateTemp();

                    Ion.with(imgLamp)
                            .error(R.drawable.kipasdiam)
                            .animateGif(AnimateGifMode.ANIMATE)
                            .load("android.resource://" + getPackageName() + "/" + R.drawable.kipasdiam)
                            .withBitmapInfo();

                    //tvState.setText("Nyala");




                }else{
                    Toast.makeText(getApplicationContext(),"OFF",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });

    }


    public void updateTemp(){
        Call call1 = apiInterface.getTemp(idChanel);
        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String ok = "OK";
                Log.v("RESPON:",response.message().toString());
                if(ok.equals(response.message().toString())){

                    TempPojo tempPojo = (TempPojo) response.body();
                    String temperature = tempPojo.field1.toString();

                    tvTemp.setText(temperature);



                }else{
                    Toast.makeText(getApplicationContext(),"Gagal update",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });

    }


    private void loadJSON() {


        final ArrayList<Entry> entries = new ArrayList<>() ;

        Call<JSONResponse> call = apiInterface.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getGraphTemp()));

                for(int i=0; i < data.size(); i++){

                    String snilai = data.get(i).getField1();

//                    Log.d("DATA", snilai);

                    int nilai = Integer.valueOf(snilai);

                    float fnilai = (float) nilai;



                    entries.add(new Entry(i, fnilai));


                }

                LineDataSet lineDataSet = new LineDataSet(entries,"Celcius");


                LineData lineData = new LineData(lineDataSet);


                lineChart.getDescription().setEnabled(false);

                lineChart.setDrawGridBackground(false);
                lineChart.setDrawBorders(false);

                //lineChart.setAutoScaleMinMaxEnabled(true);

                YAxis leftAxis = lineChart.getAxisLeft();
                leftAxis.setEnabled(false);
                YAxis rightAxis = lineChart.getAxisRight();
                rightAxis.setEnabled(false);

                XAxis xAxis = lineChart.getXAxis();
                xAxis.setEnabled(false);

               // lineChart.getAxisLeft().setDrawAxisLine(false);
                //lineChart.getAxisRight().setDrawGridLines(false);
                //lineChart.getXAxis().setDrawGridLines(false);

                //lineChart.getAxisLeft().setDrawLabels(false);
                //lineChart.getAxisRight().setDrawLabels(false);
                //lineChart.getXAxis().setDrawLabels(false);

                //lineChart.getLegend().setEnabled(false);
                lineChart.setData(lineData);
                lineChart.invalidate();


            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }


}
