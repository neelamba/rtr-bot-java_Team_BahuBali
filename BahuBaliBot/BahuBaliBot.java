import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;

// ------------------------------------------------------------------
// MyFirstBot
// ------------------------------------------------------------------
// A sample bot original made for Robocode by Mathew Nelson.
// Ported to Robocode Tank Royale by Flemming N. Larsen.
//
// Probably the first bot you will learn about.
// Moves in a seesaw motion, and spins the gun around at each end.
// ------------------------------------------------------------------
public class BahuBaliBot extends Bot {

    // The main method starts our bot
    public static void main(String[] args) {
        new BahuBaliBot().start();
    }

    // Constructor, which loads the bot config file
    BahuBaliBot() {
        super(BotInfo.fromFile("BahuBaliBot.json"));
    }

    // Called when a new round is started -> initialize and do some movement
    @Override
    public void run() {
        // Repeat while the bot is running
        while (isRunning()) {
            forward(100);
            turnGunRight(360);
            fire(1);
            turnGunLeft(180);
            fire(1);
            back(100);
            turnGunRight(360);
            fire(1);
            turnGunLeft(180);
            fire(1);
        }
    }

    // We saw another bot -> fire!
    @Override
    public void onScannedBot(ScannedBotEvent e) {
        if (e.getDirection() < 200 && e.getEnergy() < 200) { 
            fire(3);
        } else {
            fire(1);
        }
        back(100);
        turnRight(180);
        fire(1);
        forward(100);
        turnLeft(180);
        fire(1);
    }

    // public void onScannedRobot(ScannedRobotEvent e) {
    //     // Fire only when the enemy is close
    //     if (e.getDistance() < 200) {
    //         fire(1);
    //     }
    // }


    // We were hit by a bullet -> turn perpendicular to the bullet
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // Calculate the bearing to the direction of the bullet
        var bearing = calcBearing(e.getBullet().getDirection());
        fire(3);
        // Turn 90 degrees to the bullet direction based on the bearing
        turnLeft(90 - bearing);
    }
}
