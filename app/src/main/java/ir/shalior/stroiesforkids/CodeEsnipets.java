package ir.shalior.stroiesforkids;

//Todo https://github.com/LukeDeighton/WheelView for wheel effect :) its done


import android.media.MediaPlayer;

public class CodeEsnipets {
    public CodeEsnipets() {
        MediaPlayer mp = new MediaPlayer();
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

            }
        });
    }
}
/*
public class StoryActivity extends AppCompatActivity implements StoryPresenter.ViewP {
    ProgressBar progressBar;Button btn;
    int[] mStopTimes;
    SoundPool mSP;
    int idFX1;
    int nowPlaying;
    StoryPresenter presenter;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        presenter =new StoryPresenter();
        viewsPreparer();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder().build();
            mSP = new SoundPool.Builder().setMaxStreams(4).setAudioAttributes(audioAttributes).build();
        }else{
            mSP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        try{
// Create objects of the 2 required classes
            AssetManager assetManager = this.getAssets();
            AssetFileDescriptor descriptor;
// Load our fx in memory ready for use
            descriptor = assetManager.openFd("arcademusicloop.wav");
            idFX1 = mSP.load(descriptor, 0);
            mSP.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                    progressBar.setMax(2);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        progressBar.setProgress(2,true);
                    }
                    progressBar.setProgress(2);
                    progressBar.setVisibility(View.GONE);

                }
            });
        }catch(IOException e){
// Print an error message to the console
            Log.e("error", "failed to load sound files");
        }
        //nowPlaying = mSP.play(idFX1,1f,1f, 0 , 2,1);
        Handler finishDialogCloserHandler = new Handler();
        finishDialogCloserHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSP.pause(nowPlaying);
            }
        }, 1700);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nowPlaying = mSP.play(idFX1,1f,1f, 0 , 1,1);

                Handler finishDialogCloserHandler = new Handler();
                finishDialogCloserHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSP.pause(nowPlaying);
                    }
                }, 4000);



                public class StoryActivity extends AppCompatActivity {
    ProgressBar progressBar;Button btn;
    int[] mStopTimes;
    SoundPool mSP;
    int idFX1;
    int nowPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        presenter =new StoryPresenter();
        viewsPreparer();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder().build();
            mSP = new SoundPool.Builder().setMaxStreams(4).setAudioAttributes(audioAttributes).build();
        }else{
            mSP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        try{
// Create objects of the 2 required classes
            AssetManager assetManager = this.getAssets();
            AssetFileDescriptor descriptor;
// Load our fx in memory ready for use
            descriptor = assetManager.openFd("arcademusicloop.wav");
            idFX1 = mSP.load(descriptor, 0);
            mSP.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                    progressBar.setMax(2);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        progressBar.setProgress(2,true);
                    }
                    progressBar.setProgress(2);
                    progressBar.setVisibility(View.GONE);

                }
            });
        }catch(IOException e){
// Print an error message to the console
            Log.e("error", "failed to load sound files");
        }
        //nowPlaying = mSP.play(idFX1,1f,1f, 0 , 2,1);
        Handler finishDialogCloserHandler = new Handler();
        finishDialogCloserHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSP.pause(nowPlaying);
            }
        }, 1700);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nowPlaying = mSP.play(idFX1,1f,1f, 0 , 1,1);

                Handler finishDialogCloserHandler = new Handler();
                finishDialogCloserHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSP.pause(nowPlaying);
                    }
                }, 4000);
            }
        });

        //best way to work whit boxes is to make a box of othat obj first then work with the cute box
        User user = ObjectBox.get().boxFor(User.class).get(1);
        Box<User> userBox = ObjectBox.get().boxFor(User.class);


/*
        MediaPlayer mediaPlayer = new MediaPlayer();
        AssetManager assetManager = this.getAssets();
        AssetFileDescriptor descriptor;
// Load our fx in memory ready for use
        try {
            descriptor = assetManager.openFd("arcademusicloop.wav");
            mediaPlayer.setDataSource(descriptor);
        } catch (IOException e) {
            e.printStackTrace();
        }    }

@Override
public void viewsPreparer() {
        progressBar = findViewById(R.id.progressBar);
        btn = findViewById(R.id.button);
        }
        }
*/
/*



                TransformerItem(DefaultTransformer::class.java),
                TransformerItem(AccordionTransformer::class.java),
                TransformerItem(BackgroundToForegroundTransformer::class.java),
                TransformerItem(CubeInTransformer::class.java),
                TransformerItem(CubeOutTransformer::class.java),
                TransformerItem(DepthPageTransformer::class.java),
                TransformerItem(FlipHorizontalTransformer::class.java),
                TransformerItem(FlipVerticalTransformer::class.java),
                TransformerItem(ForegroundToBackgroundTransformer::class.java),
                TransformerItem(RotateDownTransformer::class.java),
                TransformerItem(RotateUpTransformer::class.java),
                TransformerItem(ScaleInOutTransformer::class.java),
                TransformerItem(StackTransformer::class.java),
                TransformerItem(TabletTransformer::class.java),
                TransformerItem(ZoomInTransformer::class.java),
                TransformerItem(ZoomOutSlideTransformer::class.java),
                TransformerItem(ZoomOutTransformer::class.java),
                TransformerItem(DrawerTransformer::class.java)


                 @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

 */




