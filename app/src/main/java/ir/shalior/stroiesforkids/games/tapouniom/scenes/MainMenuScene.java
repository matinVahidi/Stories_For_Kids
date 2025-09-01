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

public class MainMenuScene extends Scene {
    private Bitmap titleTextBitmap;
    private Rect titleTextRect;
    private Flex titleTextFlex;

    private Bitmap menuPanelBitmap;
    private Rect menuPanelRect;
    private Flex menuPanelFlex;

    private Flex playButtonFlex;
    private Flex quitButtonFlex;

    public MainMenuScene(SceneKeeper sceneKeeper, ResourceKeeper resourceKeeper, FlexConfig flexConfig) {
        super(sceneKeeper, resourceKeeper, flexConfig, 1, 1);
    }

    @Override
    public void load() {
        titleTextBitmap = resourceKeeper.getBitmap("title_text");
        titleTextRect = new Rect(0, 0, titleTextBitmap.getWidth(), titleTextBitmap.getHeight());
        titleTextFlex = new Flex(new PointF(0.5f, 0.1f), false,
                new PointF(titleTextBitmap.getWidth(), titleTextBitmap.getHeight()), true,
                new Point(-titleTextBitmap.getWidth() / 2, 0), flexConfig);

        menuPanelBitmap = resourceKeeper.getBitmap("menu_panel");
        menuPanelRect = new Rect(0, 0, menuPanelBitmap.getWidth(), menuPanelBitmap.getHeight());
        menuPanelFlex = new Flex(new PointF(0.5f, 1f), false,
                new PointF(menuPanelBitmap.getWidth(), menuPanelBitmap.getHeight()), true,
                new Point(-menuPanelBitmap.getWidth() / 2, -menuPanelBitmap.getHeight()),
                flexConfig);

        playButtonFlex = new Flex(new PointF(0.5f, 1f), false,
                new PointF(840f, 330f), true,
                new Point(-840 / 2, -1000), flexConfig);

        quitButtonFlex = new Flex(new PointF(0.5f, 1f), false,
                new PointF(840f, 330f), true,
                new Point(-840 / 2, -600), flexConfig);
    }

    @Override
    public void unload() {
    }

    @Override
    public void backPressed() {
        sceneKeeper.removeAllScenes();
    }

    @Override
    public void handleInput(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            if (playButtonFlex.getRect().contains((int) motionEvent.getX(),
                    (int) motionEvent.getY())) {
                sceneKeeper.removeScene(this);
                sceneKeeper.addScene(new GameScene(sceneKeeper, resourceKeeper, flexConfig));
            } else if (quitButtonFlex.getRect().contains((int) motionEvent.getX(),
                    (int) motionEvent.getY())) {
                sceneKeeper.removeAllScenes();
            }
        }
    }

    @Override
    public void handleUpdate(double deltaTime) {
    }

    @Override
    public void handleRender(Canvas canvas, Paint paint, double alpha) {
        canvas.drawBitmap(titleTextBitmap, titleTextRect, titleTextFlex.getRect(), paint);
        canvas.drawBitmap(menuPanelBitmap, menuPanelRect, menuPanelFlex.getRect(), paint);
    }
}