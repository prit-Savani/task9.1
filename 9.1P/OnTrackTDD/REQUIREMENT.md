# Task Notification System for OnTrack

## User Story

As a student using the OnTrack platform, I want to receive notifications about my task deadlines so that I can better manage my time and never miss an important deadline.

## Problem Statement

Students often struggle to keep track of multiple assignment deadlines across different subjects. Without timely reminders, they risk submitting work late, resulting in penalties and increased stress. Currently, OnTrack shows task deadlines but doesn't proactively notify students about approaching or overdue deadlines.

## Requirements

The system should:

1. Identify and highlight overdue tasks that haven't been completed yet
2. Notify students about tasks due on the current day
3. Provide advance notice about upcoming tasks that are due within the next few days
4. Prioritize notifications based on urgency (overdue tasks being highest priority)
5. Present clear, actionable notifications with meaningful context

## Expected Behavior

- When a task deadline has passed and the task isn't marked as completed, the system should generate a high-priority notification
- For tasks due on the current day, a medium-priority notification should remind students to complete and submit their work
- For tasks approaching their deadline (e.g., due tomorrow), a low-priority notification should be displayed as an early reminder
- All notifications should include the task title and contextual information about the deadline

## Benefits

- Helps students manage their time more effectively
- Reduces instances of late submissions due to forgotten deadlines
- Decreases student stress by providing timely reminders
- Improves overall academic performance through better task management

## Acceptance Criteria

- Notifications are generated correctly based on task due dates
- Overdue tasks generate high-priority notifications
- Today's tasks generate medium-priority notifications
- Upcoming tasks generate low-priority notifications
- Completed tasks do not generate notifications regardless of their due date
- Notifications include clear, actionable information with the task title 