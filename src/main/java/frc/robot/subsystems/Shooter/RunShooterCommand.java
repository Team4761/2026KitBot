package frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RunShooterCommand extends InstantCommand {
    public RunShooterCommand(ShooterSubsystem shooter, double speed) {
        super(() -> shooter.runShooter(speed), shooter);
    }
}
