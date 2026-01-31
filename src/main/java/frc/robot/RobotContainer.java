package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Drive.DrivetrainSubsystem;


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
    double multiplier = .5;
    // Set a default command so the drivetrain is updated by the scheduler every tick
    drive.setDefaultCommand(new RunCommand(() -> {
      double speed = xlimiter.calculate(MathUtil.applyDeadband(controller.getLeftY(), 0.08) * multiplier);
      double rotation = ylimiter.calculate(MathUtil.applyDeadband(controller.getRightY(), 0.08) * multiplier);
      drive.arcadeDrive(speed, rotation);
    }, drive));
  }
  /**
   * Must be called from testPeriodic() method in Robot.java
  */
  public void testPeriodic() {
    //scheduler.run();
  }
}
