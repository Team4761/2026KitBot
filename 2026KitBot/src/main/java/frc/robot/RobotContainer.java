package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class RobotContainer {
  // Subsystems
  private final XboxController controller;
  private final SlewRateLimiter xlimiter;
  private final SlewRateLimiter ylimiter;
  public static final DrivetrainSubsystem drive = new DrivetrainSubsystem();

  // Internal
  private int currentModule = 0;
  private final CommandScheduler scheduler = CommandScheduler.getInstance();

  public RobotContainer() {
    controller = new XboxController(0);
    xlimiter = new SlewRateLimiter(10);
    ylimiter = new SlewRateLimiter(10);

    configDefaultCmds();
  }

  public void configDefaultCmds() {
    int multiplier = 1;
    double xSpeed =  xlimiter.calculate(MathUtil.applyDeadband(controller.getLeftY(), 0.08) * multiplier);
    double ySpeed =  ylimiter.calculate(MathUtil.applyDeadband(controller.getLeftX(), 0.08) * multiplier);
    scheduler.schedule(new RunCommand(() -> drive.drive(xSpeed, ySpeed)));
}
  /**
   * Must be called from testPeriodic() method in Robot.java
  */
  public void testPeriodic() {
    // Motor selection
    if (controller.getLeftBumperButton()) {
        currentModule = (currentModule - 1) % 4;
    }
    if (controller.getRightBumperButton()) {
        currentModule = (currentModule + 1) % 4;
    }

    if (drive != null) {
        // TODO: Spin single motor
        // TODO: Display current module stats on smart dashboard
    }
  }
}