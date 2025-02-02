// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.AlignShooter;
import frc.robot.commands.AutoIntake;
//import frc.robot.commands.AlignWithLIDAR;
//import frc.robot.commands.AutoIntake;
import frc.robot.commands.AutoMove;
import frc.robot.commands.AutoMoveArmDown;
import frc.robot.commands.AutoMoveElevatorUp;
import frc.robot.commands.AutoReverseShoot;
import frc.robot.commands.AutoMoveElevatorDown;
//import frc.robot.commands.AutoMoveAndIntake;
import frc.robot.commands.AutoShoot;
import frc.robot.commands.AutoShoot6Elevator;
import frc.robot.commands.AutoShoot6point9Elevator;
import frc.robot.commands.AutoShootAt6;
import frc.robot.commands.AutoShootWithElevator;
import frc.robot.commands.AutoTurn;
import frc.robot.commands.AutoTurnLLOn;
import frc.robot.commands.AutoZeroYaw;
import frc.robot.commands.ResetDrivetrainEncoders;
import frc.robot.commands.StopNWait;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
//import frc.robot.subsystems.LIDARLiteSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SimpleShooterSubsystem;

public class AutoPath3 extends SequentialCommandGroup {
  
  /** 
   * pre-loaded with 1 ball, pick 1 ball off field, shoot both balls
   * bot at terminal start (56 in from front label to ball)
  */

  public AutoPath3(final DriveSubsystem driveSubsystem, IntakeSubsystem intakeSubsystem, 
  LimelightSubsystem limelightSubsystem, ShooterSubsystem shooterSubsystem, ArmSubsystem armSubsystem) {
    super(

      new ParallelCommandGroup(
        new AutoMoveArmDown(armSubsystem).withTimeout(0.5), 
        new ResetDrivetrainEncoders(driveSubsystem).withTimeout(0.5),
        new AutoTurnLLOn(limelightSubsystem).withTimeout(0.5),
        new AutoIntake(intakeSubsystem).withTimeout(0.5)
      ),

      new ParallelCommandGroup(
        new AutoMove(driveSubsystem, -1.8), //-1.7
        new AutoIntake(intakeSubsystem).withTimeout(2.5), //2.15
        new AutoMoveElevatorUp(shooterSubsystem).withTimeout(2.5),
        new AutoMoveArmDown(armSubsystem).withTimeout(2.5)
      ),

      new StopNWait(driveSubsystem, 0.5),

      new ParallelCommandGroup(
        new AutoReverseShoot(shooterSubsystem).withTimeout(0.16), //0.13
        new AutoZeroYaw(driveSubsystem).withTimeout(0.5)
      ),

      new StopNWait(driveSubsystem, 0.25),

      /*
      new ParallelCommandGroup(
        new AutoTurn(driveSubsystem, 9),
        new AutoShoot(shooterSubsystem).withTimeout(1.25)
      ),

      new StopNWait(driveSubsystem, 0.05),*/
      
      new AutoTurn(driveSubsystem, 9),

      new StopNWait(driveSubsystem, 0.2),

      new AutoShoot(shooterSubsystem).withTimeout(1),

      // Shoot ball
      new ParallelCommandGroup(
        new AutoShootWithElevator(shooterSubsystem).withTimeout(0.15),
        new AutoMoveArmDown(armSubsystem).withTimeout(0.15)
      ),

      new AutoShoot(shooterSubsystem).withTimeout(0.5),

      new ParallelCommandGroup(
        new AutoShootWithElevator(shooterSubsystem).withTimeout(1),
        new AutoMoveArmDown(armSubsystem).withTimeout(1)
      )
    );
  }
}