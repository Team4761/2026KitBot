package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Drive.DrivetrainSubsystem;
import frc.robot.subsystems.Intake.IntakeSubsystem;
import frc.robot.subsystems.Intake.RunIntakeCommand;
import frc.robot.subsystems.Intake.StopIntakeCommand;
import frc.robot.subsystems.Shooter.RunShooterCommand;
import frc.robot.subsystems.Shooter.ShooterSubsystem;
import frc.robot.subsystems.Shooter.StopShooterCommand;

public class RobotContainer {
  // Subsystems
  private final CommandXboxController controller;
  private final SlewRateLimiter xlimiter;
  private final SlewRateLimiter ylimiter;
  public static final DrivetrainSubsystem drive = new DrivetrainSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();
  private final ShooterSubsystem shooter = new ShooterSubsystem();

  // Internal
  private int currentModule = 0;
  private final CommandScheduler scheduler = CommandScheduler.getInstance();

  public RobotContainer() {
    controller = new CommandXboxController(0);
    xlimiter = new SlewRateLimiter(10);
    ylimiter = new SlewRateLimiter(10);

    configDefaultCmds();
    configBindings();
  }

  public void configDefaultCmds() {
    double multiplier = .5;
    // Set a default command so the drivetrain is updated by the scheduler every tick
    drive.setDefaultCommand(new RunCommand(() -> {
      double leftSpeed = xlimiter.calculate(MathUtil.applyDeadband(controller.getLeftY(), 0.08) * multiplier);
      double rightSpeed = -1 * ylimiter.calculate(MathUtil.applyDeadband(controller.getRightY(), 0.08) * multiplier);
      drive.tankDrive(leftSpeed, rightSpeed);
    }, drive));

    intake.setDefaultCommand(new StopIntakeCommand(intake));
    shooter.setDefaultCommand(new StopShooterCommand(shooter));
  }

  public void configBindings() {
    controller.leftTrigger().onTrue(new RunIntakeCommand(intake, 1.0));
    controller.leftBumper().onTrue(new RunIntakeCommand(intake, -1.0));

    controller.rightTrigger().onTrue(new RunShooterCommand(shooter, 1.0));
    controller.rightBumper().onTrue(new RunShooterCommand(shooter, -1.0));
  }
  /**
   * Must be called from testPeriodic() method in Robot.java
  */
  public void testPeriodic() {
    //scheduler.run();
  }
}
