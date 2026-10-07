package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;

public class UIElement {

    static HTextBox fpsCounter = new HTextBox(new Vector3f(-0.9f, 0.9f, 0), 0.2f, 0.1f);
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

    public static HTextBox getFPSBox() {
        return fpsCounter;
    }
}
