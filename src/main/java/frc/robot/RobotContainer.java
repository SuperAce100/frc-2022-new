// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AlignShooter;
import frc.robot.commands.AutoMove;
import frc.robot.commands.JoystickDrive;
import frc.robot.commands.MoveArm;
import frc.robot.commands.MoveClimb;
import frc.robot.commands.RollIntake;
import frc.robot.commands.ShootShooter;
import frc.robot.commands.Autonomous.DefaultAutoPath;
import frc.robot.commands.Autonomous.TestMove;
import frc.robot.commands.Autonomous.TestPath;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.JoystickDrive;
import frc.robot.commands.SimpleShootShooter;
import frc.robot.commands.Autonomous.AutoPath1;
import frc.robot.commands.Autonomous.AutoPath2;
import frc.robot.commands.Autonomous.AutoPath3;
import frc.robot.commands.Autonomous.AutoPath4;
import frc.robot.commands.Autonomous.DefaultAutoPath;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
//import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
//import frc.robot.subsystems.LIDARLiteSubsystem;
// import frc.robot.subsystems.LaserSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SimpleShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public final static SimpleShooterSubsystem simpleShooterSubsystem = new SimpleShooterSubsystem();
  public final static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  public final static DriveSubsystem driveSubsystem = new DriveSubsystem();
  public final static IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  // public final static LaserSubsystem laserSubsystem = new LaserSubsystem();
  public final static ArmSubsystem armSubsystem = new ArmSubsystem();
  public final static LimelightSubsystem limelightSubsystem = new LimelightSubsystem();
  //public static LIDARLiteSubsystem lidarLiteSubsystem = new LIDARLiteSubsystem();
  public final static ClimbSubsystem climbSubsystem = new ClimbSubsystem();

  public static final XboxController driverController = new XboxController(0);
  public static final XboxController manipulatorController = new XboxController(1);

  //public static AutoPath1 autoPath1 = new AutoPath1(driveSubsystem, intakeSubsystem, limelightSubsystem, shooterSubsystem);
  public static AutoPath2 defaultAutoPath = new AutoPath2(driveSubsystem, intakeSubsystem, limelightSubsystem, shooterSubsystem, armSubsystem);
  //public static AutoPath3 autoPath3 = new AutoPath3(driveSubsystem, intakeSubsystem, limelightSubsystem, shooterSubsystem, armSubsystem);
  //public static AutoPath4 autoPath4 = new AutoPath4(driveSubsystem, intakeSubsystem, limelightSubsystem, shooterSubsystem, armSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    driveSubsystem.setDefaultCommand(
      new JoystickDrive(driveSubsystem)
    );

    climbSubsystem.setDefaultCommand(
      new MoveClimb(climbSubsystem)
    );

    /*
    simpleShooterSubsystem.setDefaultCommand(
      new SimpleShootShooter(simpleShooterSubsystem)
    );*/

    shooterSubsystem.setDefaultCommand(
      new ShootShooter(shooterSubsystem)
    );

    armSubsystem.setDefaultCommand(
      new MoveArm(armSubsystem)
    );

    intakeSubsystem.setDefaultCommand(
      new RollIntake(intakeSubsystem)
    );

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    /*
    new JoystickButton(manipulatorController, Button.kY.value).whenPressed(
      new AutoMove(driveSubsystem, -0.35)
    );*/

    
    /*new JoystickButton(manipulatorController, Button.kLeftBumper.value).whenPressed(
      //new AutoPath2(driveSubsystem, intakeSubsystem, limelightSubsystem, shooterSubsystem, armSubsystem)
      //new TestPath(driveSubsystem, shooterSubsystem, intakeSubsystem, armSubsystem, limelightSubsystem)
      //new AutoMove(driveSubsystem, -0.35)
      new TestMove(driveSubsystem)
    );*/
  }

  public Command getAutonomousCommand() {
    return defaultAutoPath;
  }
}