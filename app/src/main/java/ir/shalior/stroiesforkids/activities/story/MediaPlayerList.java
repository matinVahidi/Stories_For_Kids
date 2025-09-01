package ir.shalior.stroiesforkids.activities.story;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ir.shalior.stroiesforkids.R;
import ir.shalior.stroiesforkids.model.StoryStep;

public class MediaPlayerList {
    OnAllPreparedListener allPreparedListener;
    OnAMediaFinishedListener aMediaFinishedListener;
    OnRandomPausedListener randomPausedListener;
    Handler stopPlayingHandler;
    Runnable runnable;
    MediaPlayer currentPlayer;
    List<MediaPlayer> playerList = new ArrayList<>();
    int playersCount;
    int prepared = 0;
    public static String TAG = "dd123";
    public MediaPlayerList(List<StoryStep> steps, Context context) {
        playersCount = steps.size();
        //make media players:
        //for echfor database getting
        for (int i = 1; i <= playersCount; i++) {
            MediaPlayer mp;
            try {
                AssetManager assetManager = context.getAssets();
// Load our fx in memory ready for use
                AssetFileDescriptor descriptor = assetManager.openFd("step" + i + ".mp3");
                //if (afd == null) return null;
                if (descriptor == null) {
                    Log.d(TAG, "MediaPlayerList: descriptor is null");
                }
                mp = new MediaPlayer();
                mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                descriptor.close();
                mp.prepareAsync();
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        prepared++;
                        Log.d("dd123", "prepared" + prepared);
                        if (prepared == playersCount && allPreparedListener != null) {
                            allPreparedListener.onAllPrepared();
                        }
                    }
                });
                playerList.add(mp);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public MediaPlayerList(Context context, int j) {
        playersCount = 4;
        //make media players:
        //for echfor database getting
        for (int i = 1; i <= playersCount; i++) {
            MediaPlayer mp;
            try {
                AssetManager assetManager = context.getAssets();
// Load our fx in memory ready for use
                AssetFileDescriptor descriptor = assetManager.openFd("step" + i + ".mp3");
                //if (afd == null) return null;

                if (descriptor == null) {
                    Log.d(TAG, "MediaPlayerList: descriptor is null");
                }
                mp = new MediaPlayer();
                mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                descriptor.close();
                mp.prepareAsync();
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        prepared++;
                        Log.d("dd123", "prepared" + prepared);
                        if (prepared == playersCount && allPreparedListener != null) {
                            allPreparedListener.onAllPrepared();
                        }
                    }
                });
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        aMediaFinishedListener.onAMediaFinished(mediaPlayer);
                    }
                });
                playerList.add(mp);
            } catch (IOException e) {
                Log.d(TAG, "MediaPlayerLi mp creation failed");
                e.printStackTrace();
            }

        }
    }

    public MediaPlayerList(Context context) {
        MediaPlayer mp = MediaPlayer.create(context, R.raw.step1);
        MediaPlayer mp2 = MediaPlayer.create(context, R.raw.step2);
        MediaPlayer mp3 = MediaPlayer.create(context, R.raw.step3);
        MediaPlayer mp4 = MediaPlayer.create(context, R.raw.step4);
        playerList.add(mp);
        playerList.add(mp2);
        playerList.add(mp3);
        playerList.add(mp4);
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                prepared++;
                Log.d("dd123", "prepared" + prepared);
                if (prepared == playersCount && allPreparedListener != null) {
                    allPreparedListener.onAllPrepared();
                    //mediaPlayer.start();
                }
            }
        });
    }

    public void play(int position) {
        //reset allother and play this one
        if (isAllPrepared()) {
            pauseAll();
            playerList.get(position).start();
        }
    }

    public void pause(int position) {
        MediaPlayer mp = playerList.get(position);
        if (isAllPrepared() && mp.isPlaying()) {
            mp.pause();
        }
    }

    public MediaPlayer get(int position) {
        Log.d(TAG, "get: ");
        if (isAllPrepared()) {
            return playerList.get(position);
        } else {
            Log.d(TAG, "now now Hney ");
            return null;
        }
    }
    public boolean isAllPrepared() {
        return prepared == playersCount;
    }

    public void setOnAllPreparedListener(OnAllPreparedListener l) {
        this.allPreparedListener = l;
    }

    public void setOnAMediaFinishedListener(OnAMediaFinishedListener listener) {
        this.aMediaFinishedListener = listener;
    }

    public void setOnRandomPausedListener(OnRandomPausedListener listener) {
        this.randomPausedListener = listener;
    }

    public void releseAll() {
        if (stopPlayingHandler != null && runnable != null) {
            stopPlayingHandler.removeCallbacks(runnable);
            stopPlayingHandler = null;
            runnable = null;
        }
        for (MediaPlayer mp : playerList
        ) {
            mp.release();
            mp = null;
        }
    }

    private void seekAllToZero() {
        for (MediaPlayer mp : playerList
        ) {
            mp.seekTo(0);
        }
    }

    void pauseAll() {
        if (isAllPrepared()) {
            for (MediaPlayer mp : playerList
            ) {
                if (mp.isPlaying()) {
                    mp.pause();
                }
            }
        }
    }

    private void resetAll() {

        if (isAllPrepared()) {
            for (MediaPlayer mp : playerList
            ) {
                if (mp.isPlaying()) {
                    mp.seekTo(0);
                    mp.pause();
                }
            }
        }
    }

    public void stopPlayingOn(int time) {
        if (isAllPrepared()) {
            Log.d(TAG, "stopPlayingOn: " + time * 1);

            stopPlayingHandler = new Handler();

            runnable = new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "run: runnable runngin!");
                    pauseAll();
                    randomPausedListener.OnRandomPaused();
                }
            };
            stopPlayingHandler.postDelayed(runnable, time * 1);
        }
    }

    public interface OnAllPreparedListener {
        public void onAllPrepared();
    }

    public interface OnRandomPausedListener {
        public void OnRandomPaused();
    }

    public interface OnAMediaFinishedListener {
        public void onAMediaFinished(MediaPlayer mediaPlayer);
    }
}
