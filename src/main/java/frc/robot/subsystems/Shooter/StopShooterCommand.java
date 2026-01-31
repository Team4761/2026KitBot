package frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.RunCommand;

public class StopShooterCommand extends RunCommand {
    public StopShooterCommand(ShooterSubsystem shooter) {
        super(shooter::stop, shooter);
    }
}
