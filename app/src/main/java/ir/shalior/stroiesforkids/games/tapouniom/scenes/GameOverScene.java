package ir.shalior.stroiesforkids.games.tapouniom.scenes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;

import ir.shalior.stroiesforkids.games.tapouniom.engine.Flex;
import ir.shalior.stroiesforkids.games.tapouniom.engine.FlexConfig;
import ir.shalior.stroiesforkids.games.tapouniom.engine.ResourceKeeper;
import ir.shalior.stroiesforkids.games.tapouniom.engine.Scene;
import ir.shalior.stroiesforkids.games.tapouniom.engine.SceneKeeper;
import ir.shalior.stroiesforkids.games.tapouniom.game.ScoreCounter;
import ir.shalior.stroiesforkids.games.tapouniom.game.ScoreCounterRenderer;

public class GameOverScene extends Scene {
    private final long maxScore;
    private Bitmap gameOverTextBitmap;
    private Rect gameOverTextRect;
    private Flex gameOverTextFlex;
    private Bitmap overPanelBitmap;
    private Rect overPanelRect;
    private Flex overPanelFlex;
    private Flex againButtonFlex;
    private Flex menuButtonFlex;
    private ScoreCounterRenderer scoreCounterRenderer;

    // touch input will be unlocked after half a second to avoid accidental click
    private double unlockTotalDuration = 0.5;
    private double unlockCurrentDuration = 0.0;

    public GameOverScene(SceneKeeper sceneKeeper, ResourceKeeper resourceKeeper,
                         FlexConfig flexConfig, long maxScore) {
        super(sceneKeeper, resourceKeeper, flexConfig, 3, 1);
        this.maxScore = maxScore;
    }

    @Override
    public void load() {
        overPanelBitmap = resourceKeeper.getBitmap("over_panel");
        overPanelRect = new Rect(0, 0, overPanelBitmap.getWidth(), overPanelBitmap.getHeight());
        overPanelFlex = new Flex(new PointF(0.5f, 1f), false,
                new PointF(overPanelBitmap.getWidth(), overPanelBitmap.getHeight()), true,
                new Point(-overPanelBitmap.getWidth() / 2, -overPanelBitmap.getHeight()),
                flexConfig);

        gameOverTextBitmap = resourceKeeper.getBitmap("game_over_text");
        gameOverTextRect = new Rect(0, 0, gameOverTextBitmap.getWidth(),
                gameOverTextBitmap.getHeight());

        gameOverTextFlex = new Flex(new PointF(0.5f, 0.25f), false,
                new PointF(gameOverTextBitmap.getWidth(), gameOverTextBitmap.getHeight()), true,
                new Point(-gameOverTextBitmap.getWidth() / 2, -gameOverTextBitmap.getHeight() - 130),
                flexConfig);

        ScoreCounter scoreCounter = new ScoreCounter();
        scoreCounterRenderer = new ScoreCounterRenderer(scoreCounter, resourceKeeper, flexConfig);

        scoreCounter.add(maxScore);

        againButtonFlex = new Flex(new PointF(0.5f, 1f), false,
                new PointF(840f, 330f), true,
                new Point(-840 / 2, -1000), flexConfig);

        menuButtonFlex = new Flex(new PointF(0.5f, 1f), false,
                new PointF(840f, 330f), true,
                new Point(-840 / 2, -600), flexConfig);
    }

    @Override
    public void unload() {
    }

    @Override
    public void backPressed() {
        sceneKeeper.removeScene(this);
        sceneKeeper.addScene(new MainMenuScene(sceneKeeper, resourceKeeper, flexConfig));
    }

    @Override
    public void handleInput(MotionEvent motionEvent) {
        if (unlockCurrentDuration >= unlockTotalDuration) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (againButtonFlex.getRect().contains((int) motionEvent.getX(),
                        (int) motionEvent.getY())) {
                    sceneKeeper.removeScene(this);
                    sceneKeeper.addScene(new GameScene(sceneKeeper, resourceKeeper, flexConfig));
                } else if (menuButtonFlex.getRect().contains((int) motionEvent.getX(),
                        (int) motionEvent.getY())) {
                    sceneKeeper.removeScene(this);
                    sceneKeeper.addScene(new MainMenuScene(sceneKeeper, resourceKeeper, flexConfig));
                }
            }
        }
    }

    @Override
    public void handleUpdate(double deltaTime) {
        if (unlockCurrentDuration < unlockTotalDuration) {
            unlockCurrentDuration += deltaTime;
        }
    }

    @Override
    public void handleRender(Canvas canvas, Paint paint, double alpha) {
        canvas.drawBitmap(gameOverTextBitmap, gameOverTextRect, gameOverTextFlex.getRect(), paint);
        canvas.drawBitmap(overPanelBitmap, overPanelRect, overPanelFlex.getRect(), paint);
        scoreCounterRenderer.render(canvas, paint);
    }
}