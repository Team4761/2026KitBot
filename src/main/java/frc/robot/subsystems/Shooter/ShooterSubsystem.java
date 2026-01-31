package frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.SmartCIMMotor;

public class ShooterSubsystem extends SubsystemBase {
    private final SmartCIMMotor shooterMotor = new SmartCIMMotor(9);

    public ShooterSubsystem() {
        shooterMotor.setInverted(false);
    }

    public void runShooter(double speed) {
        shooterMotor.setSpeed(speed);
    }

    public void stop() {
        shooterMotor.stop();
    }
}
