package frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.SmartCIMMotor;

public class IntakeSubsystem extends SubsystemBase {
    private final SmartCIMMotor intakeMotor = new SmartCIMMotor(9);

    public IntakeSubsystem() {
        intakeMotor.setInverted(false);
    }

    public void runIntake(double speed) {
        intakeMotor.setSpeed(speed);
    }

    public void stop() {
        intakeMotor.stop();
    }
}
