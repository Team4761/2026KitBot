package frc.robot.subsystems.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem {

    // Motor IDs
    public final static int TEMP_UP_DOWN_MOTOR_ID = 9;
    public final static int TEMP_LEFT_RIGHT_MOTOR_ID = 11;

    // Motor Speeds (Adjust these as needed, 0.5 is 50% power)
    private final double MOVEMENT_SPEED = 0.5;

    // Hardware Declaration
    private final CANSparkMax upDownMotor;
    private final CANSparkMax leftRightMotor;

    public IntakeSubsystem() {
        // Initialize motors
        upDownMotor = new CANSparkMax(TEMP_UP_DOWN_MOTOR_ID, MotorType.kBrushed);
        leftRightMotor = new CANSparkMax(TEMP_LEFT_RIGHT_MOTOR_ID, MotorType.kBrushed);

        // Reset motors to factory defaults on startup
        upDownMotor.restoreFactoryDefaults();
        leftRightMotor.restoreFactoryDefaults();
        
        // Set idle mode to Brake to stop movement immediately when buttons are released
        upDownMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        leftRightMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        
        // Save settings to the motor controller
        upDownMotor.burnFlash();
        leftRightMotor.burnFlash();
    }

    // Control Methods

    /**
     * Moves the Up/Down motor (ID 9).
     * @param speed Positive for up, Negative for down.
     */
    public void setUpDownSpeed(double speed) {
        upDownMotor.set(speed);
    }

    /**
     * Moves the Left/Right motor (ID 11).
     * @param speed Positive for Right, Negative for Left.
     */
    public void setLeftRightSpeed(double speed) {
        leftRightMotor.set(speed);
    }

    /**
     * Stops both motors.
     */
    public void stopMotors() {
        upDownMotor.set(0);
        leftRightMotor.set(0);
    }

    // Helper Getters for Commands
    public double getSpeedConstant() {
        return MOVEMENT_SPEED;
    }
}
