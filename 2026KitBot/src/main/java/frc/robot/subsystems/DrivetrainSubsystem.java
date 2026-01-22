package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class DrivetrainSubsystem extends SubsystemBase{
    // Motors
    // ONLY RUN THE FRONT MOTORS
    // THE BACK MOTORS FOLLOW THE FRONT MOTORS
    // CHECK THE CONSTRUCTOR
    private SparkMax frontLeftMotor;
    private SparkMax frontRightMotor;
    private SparkMax backLeftMotor;
    private SparkMax backRightMotor;

    DifferentialDrive drivetrain;

    MotorControllerGroup left;
    MotorControllerGroup right;


    // Constructor
    public DrivetrainSubsystem() {
        frontLeftMotor = new SparkMax(Constants.FRONT_LEFT_MOTOR_ID, MotorType.kBrushed);
        frontRightMotor = new SparkMax(Constants.FRONT_RIGHT_MOTOR_ID, MotorType.kBrushed);
        backLeftMotor = new SparkMax(Constants.BACK_LEFT_MOTOR_ID, MotorType.kBrushed);
        backRightMotor = new SparkMax(Constants.BACK_RIGHT_MOTOR_ID, MotorType.kBrushed);
        SparkMaxConfig config = new SparkMaxConfig();
        config.closedLoop
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
            .pid(0.1, 0.0, 0.0)  // P, I, D
            .outputRange(-0.3, 0.3);  // Max 30% speed

        left = new MotorControllerGroup(frontLeftMotor, backLeftMotor);
        right = new MotorControllerGroup(frontRightMotor, backRightMotor);

        drivetrain = new DifferentialDrive(left, right);
    }


    /**
     * 
     * @param xSpeed The speed of the left motors
     * @param ySpeed The speed of the right motors
     * @return
     */
    public Command drive(double xSpeed, double ySpeed) {
        
        return run(() -> {drivetrain.tankDrive(-xSpeed, ySpeed);});
    }    
}
