package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;

public class UIElement {

    static HTextBox fpsCounter = new HTextBox(new Vector3f(-0.99f, 0.99f, 0), 0.15f, 0.05f);
    static long time = System.currentTimeMillis();
    static int frames =0;

    public static void calculateFrames() {
        if (System.currentTimeMillis() - time > 1000) {
            fpsCounter.setText(String.valueOf(frames), 0.05f);
            time = System.currentTimeMillis();
            frames = 0;
        } else {
            frames++;
        }
    }

}
