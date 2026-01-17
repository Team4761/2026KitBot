package frc.robot.subsystems.drive;

import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;


public class MotorControllerGroup {
    private final SparkClosedLoopController m_motor1;
    private final SparkClosedLoopController m_motor2;
    private double angle;

    public MotorControllerGroup(SparkClosedLoopController motor1, SparkClosedLoopController motor2) {
        m_motor1 = motor1;
        m_motor2 = motor2;
    }

    public void set(double degrees) {
        this.angle = (degrees / 3.6 + this.angle) % 100;
        m_motor1.setSetpoint((degrees / 3.6 + this.angle) % 100, ControlType.kPosition);
        m_motor2.setSetpoint((degrees / 3.6 + this.angle) % 100, ControlType.kPosition);
    }
}
