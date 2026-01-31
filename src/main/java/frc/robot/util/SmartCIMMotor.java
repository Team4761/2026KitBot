package frc.robot.util;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class SmartCIMMotor {
    private final SparkMax motorSparkMax;

    public SmartCIMMotor(int motorID) {
        this.motorSparkMax = new SparkMax(motorID, MotorType.kBrushed);
        this.motorSparkMax.stopMotor();
    }

    public void setSpeed(double speed) {
        motorSparkMax.set(speed);
    }

    public void stop() {
        motorSparkMax.stopMotor();
    }

    public void setInverted(boolean inverted) {
        motorSparkMax.setInverted(inverted);
    }

    public boolean isInverted() {
        return motorSparkMax.getInverted();
    }
}
