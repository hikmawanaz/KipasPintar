package com.arbaini.kipaspintar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arbaini.kipaspintar.pojo.StatusPower;
import com.arbaini.kipaspintar.pojo.TempPojo;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.AnimateGifMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button mButtonOn,mButtonOff;
    private TextView tvState,tvTemp;
    private ImageView imgLamp;
    APIInterfaces apiInterface;
    private int idChanel;

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

        apiInterface = APIClient.getClient().create(APIInterfaces.class);
        updateTemp();


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

}
