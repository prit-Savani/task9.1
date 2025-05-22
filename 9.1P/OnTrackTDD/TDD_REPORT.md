# Test-Driven Development (TDD) Report

## Introduction

This report documents the implementation of a Task Notification System for the OnTrack platform using Test-Driven Development (TDD) methodology. The system was developed to help students better manage their task deadlines through timely notifications.

## TDD Process Overview

Test-Driven Development is a software development approach where tests are written before the actual implementation code. The process follows a "Red-Green-Refactor" cycle:

1. **Red**: Write a failing test that defines a desired function.
2. **Green**: Write the minimum implementation code to make the test pass.
3. **Refactor**: Improve the code while ensuring tests still pass.

## Benefits of TDD

- **Improved Code Quality**: Writing tests first forces developers to think about the design and requirements before implementation.
- **Better Test Coverage**: Since tests are written first, test coverage is naturally high.
- **Early Bug Detection**: Issues are identified at the earliest stage of development.
- **Enhanced Documentation**: Tests serve as documentation for how the code is expected to work.
- **Confidence in Refactoring**: A comprehensive test suite provides confidence when making changes.

## Implementation Steps

### Step 1: Write Customer Requirements (Red)

I started by creating a user story and requirements for the Task Notification System. This helped define what functionality was needed before any code was written.

### Step 2: Create Test Cases (Red)

I created the `TaskNotificationTest.java` file with comprehensive test cases for all required functionality:
- `testGetOverdueTasks()`: Verifying the system can identify tasks past their due date
- `testGetTasksDueToday()`: Ensuring the system recognizes tasks due on the current day
- `testGetUpcomingTasks()`: Confirming the system can find tasks due within a specified period
- `testGetTaskReminders()`: Testing the full notification generation with correct priority levels

At this stage, all tests fail because no implementation exists yet.

### Step 3: Create Supporting Classes (Red → Green)

I implemented the necessary supporting classes:
- `NotificationPriority.java`: An enum defining the different priority levels
- `TaskNotification.java`: A class representing task notifications with message and priority

### Step 4: Implement Core Functionality (Green)

I created the `TaskNotificationService.java` file with all required functionality:
- Methods to retrieve overdue, today's, and upcoming tasks
- Logic to generate appropriate notifications for each task based on its due date
- Proper prioritization of notifications based on urgency

After implementation, all tests passed, confirming the code meets requirements.

### Step 5: Adding Demo Application (Refactor)

I created a `Main.java` class to demonstrate the notification system in action with sample tasks spanning different dates.

## Continuous Integration Setup

A GitHub Actions workflow was configured to run all tests automatically on each commit. This ensures that any changes maintain the expected behavior of the application.

### CI Process

1. Each commit triggers an automated build
2. All tests are run as part of the build process
3. If any test fails, the build fails and team members are notified
4. This maintains code quality and prevents integration issues

## Conclusion

Using TDD for the Task Notification System resulted in well-tested, maintainable code that meets all requirements. The approach forced careful thinking about functionality before implementation and provided confidence that the system works as expected.

The CI pipeline ensures that future changes maintain this quality by automatically testing all code changes before they are integrated into the main codebase. 