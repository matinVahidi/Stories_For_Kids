package ir.shalior.stroiesforkids.games.tapouniom.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Typeface;

import ir.shalior.stroiesforkids.games.tapouniom.engine.Flex;
import ir.shalior.stroiesforkids.games.tapouniom.engine.FlexConfig;
import ir.shalior.stroiesforkids.games.tapouniom.engine.ResourceKeeper;

public class ScoreCounterRenderer {
    private final ScoreCounter scoreCounter;

    private final Typeface typeface;
    private final Flex scoreTextFlex;
    private final int colorMaxScore = Color.argb(255, 255, 102, 0);
    private final int colorBelowMaxScore = Color.argb(255, 128, 128, 128);

    public ScoreCounterRenderer(ScoreCounter scoreCounter, ResourceKeeper resourceKeeper, FlexConfig flexConfig) {
        this.scoreCounter = scoreCounter;
        typeface = resourceKeeper.getTypeface("IndieFlower");
        scoreTextFlex = new Flex(new PointF(0.5f, 0.25f), false,
                new PointF(0f, 180f), true,
                new Point(), flexConfig);
    }

    public void render(Canvas canvas, Paint paint) {
        int color = scoreCounter.isBelowMax() ? colorBelowMaxScore : colorMaxScore;
        paint.setColor(color);
        paint.setTextSize(scoreTextFlex.getSize().y);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(typeface);
        canvas.drawText(String.valueOf(scoreCounter.getCurrent()),
                scoreTextFlex.getPosition().x, scoreTextFlex.getPosition().y, paint);
    }
}