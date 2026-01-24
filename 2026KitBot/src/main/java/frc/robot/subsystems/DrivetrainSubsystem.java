package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
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

    private DifferentialDrive drivetrain;

    public DrivetrainSubsystem() {
        // Initialize Motors
        frontLeftMotor = new SparkMax(Constants.FRONT_LEFT_MOTOR_ID, MotorType.kBrushed);
        frontRightMotor = new SparkMax(Constants.FRONT_RIGHT_MOTOR_ID, MotorType.kBrushed);
        backLeftMotor = new SparkMax(Constants.BACK_LEFT_MOTOR_ID, MotorType.kBrushed);
        backRightMotor = new SparkMax(Constants.BACK_RIGHT_MOTOR_ID, MotorType.kBrushed);

        // -- Configure Leader Motors (Front) --
        SparkMaxConfig frontConfig = new SparkMaxConfig();
        frontConfig.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder);
        frontConfig.idleMode(IdleMode.kBrake); 
        
        // Apply config to leaders
        frontLeftMotor.configure(frontConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        frontRightMotor.configure(frontConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // -- Configure Follower Motors (Back) --
        SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
        leftFollowerConfig.follow(frontLeftMotor);
        leftFollowerConfig.idleMode(IdleMode.kBrake);
        
        SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();
        rightFollowerConfig.follow(frontRightMotor);
        rightFollowerConfig.idleMode(IdleMode.kBrake);

        // Apply config to followers
        backLeftMotor.configure(leftFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        backRightMotor.configure(rightFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // Initialize DifferentialDrive with only the leaders
        // The followers will automatically mimic the leaders (I think)
        drivetrain = new DifferentialDrive(frontLeftMotor, frontRightMotor);
    }

    /*
     * Controls the robot using Tank Drive.
     * Method is void so it can be called inside a RunCommand loop.
     * @param leftSpeed The speed of the left motors
     * @param rightSpeed The speed of the right motors
     */
    public void drive(double leftSpeed, double rightSpeed) {
        drivetrain.tankDrive(leftSpeed, rightSpeed);
    }    
}