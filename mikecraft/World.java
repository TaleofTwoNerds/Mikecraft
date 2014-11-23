package mikecraft;

import org.lwjgl.Sys;

public class World extends MainGame {
	public static void chooseLevel(){
		if (level == 1.1){
			WorldOneOne.drawBackground();
			WorldOneOne.gravitation();
		} else if (level == 1.2){
			WorldOneTwo.drawBackground();
			WorldOneTwo.gravitation();
			WorldOneTwo.render();
            WorldOneTwo.logic(getDelta());
		}/* else if (level == 1.3){
			WorldOneThree.drawBackground();
			WorldOneThree.gravitation();
		} else if (level == 1.4){
			WorldOneFour.drawBackground();
			WorldOneFour.gravitation();
		}*/
	}
	private static long lastFrame;

    private static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    protected static int getDelta() {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }
}
