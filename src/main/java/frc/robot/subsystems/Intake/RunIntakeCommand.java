package frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RunIntakeCommand extends InstantCommand {
    public RunIntakeCommand(IntakeSubsystem intake, double speed) {
        super(() -> intake.runIntake(speed), intake);
    }
}
