package frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.RunCommand;

public class StopIntakeCommand extends RunCommand {
    public StopIntakeCommand(IntakeSubsystem intake) {
        super(intake::stop, intake);
    }
}
