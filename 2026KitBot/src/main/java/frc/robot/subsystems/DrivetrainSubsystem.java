package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class DrivetrainSubsystem {
    // Motors
    // ONLY RUN THE FRONT MOTORS
    // THE BACK MOTORS FOLLOW THE FRONT MOTORS
    // CHECK THE CONSTRUCTOR
    SparkMax frontLeftMotor;
    SparkMax frontRightMotor;
    SparkMax backLeftMotor;
    SparkMax backRightMotor;

    DifferentialDrive drivetrain;

    MotorControllerGroup left;
    MotorControllerGroup right;


    // Constructor
    public DrivetrainSubsystem() {
        frontLeftMotor = new SparkMax(Constants.FRONT_LEFT_MOTOR_ID, MotorType.kBrushless);
        frontRightMotor = new SparkMax(Constants.FRONT_RIGHT_MOTOR_ID, MotorType.kBrushless);
        backLeftMotor = new SparkMax(Constants.BACK_LEFT_MOTOR_ID, MotorType.kBrushless);
        backRightMotor = new SparkMax(Constants.BACK_RIGHT_MOTOR_ID, MotorType.kBrushless);
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
     * @param speedForwards A number between -1.0 and 1.0, where 1.0 is 100% power.
     * @param speedTurn A number between -1.0 and 1.0, where 1.0 is 100% power.
     */
    public void drive(double xSpeed, double ySpeed) {
        drivetrain.tankDrive(xSpeed, ySpeed);
    }    
}
