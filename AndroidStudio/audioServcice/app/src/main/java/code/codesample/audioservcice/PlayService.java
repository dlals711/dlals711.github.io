package code.codesample.audioservcice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class PlayService extends Service {
    private MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Service", "onCreate");
        IntentFilter filter=new IntentFilter("com.codesample.audioservcice.Request");
        registerReceiver(receiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Service", "onStartCommand");
        play();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Service", "onDestroy");
        unregisterReceiver(receiver);
        stop();
    }

    public PlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null){
                int progress=intent.getIntExtra("progress", -1);
                if(progress!=-1 && player!=null){
                    player.seekTo(progress);
                    player.start();
                }
            }
        }
    };

    private void stop(){
        if(player!=null) {
            player.stop();
            player.release();
            player = null;
        }
        Intent intent = new Intent("com.codesample.audioservcice.Play");
        intent.putExtra("status", "stop");
        sendBroadcast(intent);
    }

    private void play(){
        if(player!=null && player.isPlaying()) {
            player.seekTo(0);
            return;
        }
        if(player==null)
            player=MediaPlayer.create(this, R.raw.pathos);
        player.start();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(player!=null && player.isPlaying()) {
                    Intent intent = new Intent("com.codesample.audioservcice.Play");
                    intent.putExtra("status", "play");
                    intent.putExtra("duration", player.getDuration());
                    intent.putExtra("current", player.getCurrentPosition());
                    sendBroadcast(intent);
                } else {
                    timer.cancel();
                }
            }
        }, 0,1000);
    }
}