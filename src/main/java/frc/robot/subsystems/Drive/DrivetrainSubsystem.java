package frc.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class DrivetrainSubsystem extends SubsystemBase{
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
        frontLeftMotor = new SparkMax(Constants.FRONT_LEFT_MOTOR_ID, MotorType.kBrushed);
        frontRightMotor = new SparkMax(Constants.FRONT_RIGHT_MOTOR_ID, MotorType.kBrushed);
        backLeftMotor = new SparkMax(Constants.BACK_LEFT_MOTOR_ID, MotorType.kBrushed);
        backRightMotor = new SparkMax(Constants.BACK_RIGHT_MOTOR_ID, MotorType.kBrushed);

        left = new MotorControllerGroup(frontLeftMotor, backLeftMotor);
        right = new MotorControllerGroup(frontRightMotor, backRightMotor);

        drivetrain = new DifferentialDrive(left, right);
    }


    /**
     * 
     * @param speedForwards A number between -1.0 and 1.0, where 1.0 is 100% power.
     * @param speedTurn A number between -1.0 and 1.0, where 1.0 is 100% power.
     */
    public void arcadeDrive(double speed, double rotation) {
        drivetrain.arcadeDrive(speed, rotation);
    }
}
