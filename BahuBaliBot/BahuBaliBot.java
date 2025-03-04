import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;
import dev.robocode.tankroyale.botapi.util.ColorUtil;

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
        ColorUtil.fromHex("#0000FF");
        // Repeat while the bot is running
        while (isRunning()) {
            forward(100);
            turnGunRight(180);
            back(100);
            turnGunRight(180);
        }
    }

    // We saw another bot -> fire!
    @Override
    public void onScannedBot(ScannedBotEvent e) {
        fire(5);
        back(100);
        fire(3);
        forward(100);
        fire(5);
}


    // We were hit by a bullet -> turn perpendicular to the bullet
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // Calculate the bearing to the direction of the bullet
        var bearing = calcBearing(e.getBullet().getDirection());
        fire(3);     
        // Turn 90 degrees to the bullet direction based on the bearing
        turnLeft(90 - bearing);
    }

@Override
    public void onHitWall(HitWallEvent e) {
        setBack(100);
        setTurnRight(90);
    }
}
