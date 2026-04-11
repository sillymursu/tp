---
layout: default.md
title: "User Guide"
pageNav: 3
---

# LeTutor User Guide

---

## Who is this guide for?

LeTutor is built for **tutors and educators** who need a fast way to manage **students**, **assignments**, and **milestone progress** without relying on spreadsheets or clicking through many menus.

This guide is written for users who:
- are comfortable using a computer and typing commands
- want a clear step-by-step explanation of each feature
- do not need programming knowledge to use the app

## What problem does LeTutor solve?

Keeping track of multiple students, class groups, assignment deadlines, and completion progress can get messy very quickly. LeTutor gives you one place to:

- **Manage students efficiently** — store student contact details and group memberships
- **Manage assignments cleanly** — create assignments and assign them to one or more groups
- **Track progress clearly** — view and update milestone completion per student
- **Filter relevant information quickly** — find students by name or show only students and assignments belonging to a chosen group

---

## Table of Contents

1. [Quick Start](#quick-start)
2. [Key Concepts](#key-concepts)
3. [Features](#features)
   - [Notes about the command format](#notes-about-the-command-format)
   - [Viewing help — `help`](#viewing-help--help)
   - [Adding a student — `add /students`](#adding-a-student--add-students)
   - [Adding an assignment — `add /assignments`](#adding-an-assignment--add-assignments)
   - [Listing all students — `list`](#listing-all-students--list)
   - [Listing all assignments — `get /assignments`](#listing-all-assignments--get-assignments)
   - [Viewing a student's details — `get /students`](#viewing-a-students-details--get-students)
   - [Viewing student milestones — `get /students ... /milestones`](#viewing-student-milestones--get-students--milestones)
   - [Updating a milestone — `set /students ... /milestones`](#updating-a-milestone--set-students--milestones)
   - [Viewing a specific assignment — `get /assignments <assignmentId>`](#viewing-a-specific-assignment--get-assignments-assignmentid)
   - [Finding students by name — `find /students`](#finding-students-by-name--find-students)
   - [Finding by group — `find /groups`](#finding-by-group--find-groups)
   - [Editing a student — `edit /students`](#editing-a-student--edit-students)
   - [Editing an assignment — `edit /assignments`](#editing-an-assignment--edit-assignments)
   - [Deleting a student — `delete /students`](#deleting-a-student--delete-students)
   - [Deleting an assignment — `delete /assignments`](#deleting-an-assignment--delete-assignments)
   - [Clearing all entries — `clear`](#clearing-all-entries--clear)
   - [Exiting — `exit`](#exiting-the-program--exit)
   - [Saving the data](#saving-the-data)
   - [Editing the data file](#editing-the-data-file)
4. [FAQ](#faq)
5. [Known Issues](#known-issues)
6. [Command Summary](#command-summary)

---

## Quick start

### Installation

1. Ensure you have **Java 17 or above** installed on your computer.
   - **Windows:** Follow the [Java 17 Installation Guide for Windows](https://se-education.org/guides/tutorials/javaInstallationWindows.html)
   - **Mac:** Follow the [Java 17 Installation Guide for Mac](https://se-education.org/guides/tutorials/javaInstallationMac.html) exactly
   - **Linux:** Follow the [Java 17 Installation Guide for Linux](https://se-education.org/guides/tutorials/javaInstallationLinux.html)

2. Download the latest `letutor.jar` from the [GitHub Releases page](https://github.com/AY2526S2-CS2103T-T08-4/tp/releases).

3. Create a folder anywhere on your computer and place `letutor.jar` inside it. This folder will also store your LeTutor data.

4. Open a terminal:
   - **Windows:** Search for **PowerShell** or **Command Prompt**
   - **Mac:** Open **Terminal**
   - **Linux:** Open your preferred terminal

5. Navigate to the folder containing `letutor.jar`. For example:
```
cd C:\Users\YourName\LeTutor
```

6. Run the app:
```
java -jar letutor.jar
```

7. The app window should appear within a few seconds.

8. Type a command into the command box and press <kbd>Enter</kbd> to run it.

> **Tip:** If you ever feel lost after filtering results, use `list` to return to the full student and assignment list.
> {: .tip}

---

## Key Concepts

Before using LeTutor, it helps to understand four core ideas:

### Student

A student entry stores:

1. Student ID
2. Name
3. Phone number
4. Email address
5. One or more groups

Student IDs are automatically generated and appear in the format `S1`, `S2`, `S3`, ...

### Assignment

An assignment stores:

1. Assignment ID
2. Label
3. One or more groups
4. Due date

Assignment IDs are automatically generated and appear in the format `A1`, `A2`, `A3`, ...

### Group

A group connects students and assignments.
If a student and an assignment share **at least one group**, that assignment can appear in the student's milestone view.

### Milestone status

Milestones show a student's progress for relevant assignments.

Stored statuses:

* `NOT_STARTED`
* `COMPLETED`

View-only computed status:

* `OVERDUE`

> **Note:** `OVERDUE` is not manually set. It appears automatically when the due date has passed and the milestone is still incomplete.
{: .note}
---

## Features

### Notes about the command format

> **Note:**
> Words in `UPPER_CASE` are values you must supply.
> Example: in `get /students STUDENT_ID`, `STUDENT_ID` should be replaced with something like `S1`.
{: .note}

> **Note:**
> Values inside `< >` are placeholders shown for explanation only. Do not type the `<` and `>` symbols literally.
{: .note}

> **Note:**
> Fields inside `{ ... }` must be entered in the stated order.
{: .note}

> **Note:**
> Multiple groups inside one field should be separated by commas.
> Example: `Sec3A, Sec3B`
{: .note}

> **Note:**
> Commands such as `help`, `list`, `clear`, and `exit` ignore extra text after them.
{: .note}

> **Caution:**
> If you are copying commands from a PDF or document, check that spaces were copied correctly before pasting into LeTutor.
{: .caution}
---

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

**Expected output:** A help window appears or help information is shown.

![Help command result](images/help-image.png)

---

### Adding a student : `add /students`

Adds a student to LeTutor.

Format: `add /students {<name>; <phone>; <email>; <groups>}`

**Parameter reference:**

| Field      | Description                             |
| ---------- | --------------------------------------- |
| `<name>`   | Student's full name                     |
| `<phone>`  | 8-digit phone number                    |
| `<email>`  | Student's email address                 |
| `<groups>` | One or more groups, separated by commas |

Rules:

* The phone number must contain 8 digits.
* The email should contain `@`.
* The name, phone, and email fields must not contain `;`.
* The groups field supports multiple groups separated by commas.

Examples:

* `add /students {John Doe; 98765432; johnd@example.com; Sec3A}`
* `add /students {Jane Tan; 91234567; janetan@email.com; Sec3A, Math}`

> **Tip:**
> Use consistent group names across students and assignments. For example, avoid mixing `Sec3A`, `sec3a`, and `SEC3A`.
{: .tip}

**Expected output:** The student appears in the list and a confirmation message is shown.

![Add student result](images/add-student.png)

---

### Adding an assignment 

`add /assignments`

Adds an assignment to LeTutor.

Format: `add /assignments {<label>; <groups>; <dueDate>}`

**Parameter reference:**

| Field       | Description                             |
| ----------- | --------------------------------------- |
| `<label>`   | Assignment name or label                |
| `<groups>`  | One or more groups, separated by commas |
| `<dueDate>` | Due date in `YYYY-MM-DD` format         |

Examples:

* `add /assignments {Finish Math HW; Sec3A; 2026-04-20}`
* `add /assignments {Science Quiz; Sec3A, Sec3B; 2026-04-01}`

> **Note:**
> An assignment can belong to more than one group.
{: .note}

**Expected output:** The assignment appears in the assignment list and a confirmation message is shown.

![Add assignment result](images/add-assignment.png)

---

### Listing all students and assignments: `list`

Shows all students and assignments currently in LeTutor.

Format: `list`

> **Tip:**
> Run `list` after using a find or group filter if you want to return to the full student list & full assignment list.
{: .tip}

**Expected output:** The student list resets to show all students, assignment list shows all assignments.

---

### Listing all students only: `get /students`

Shows all students currently in LeTutor.

Format: `get /students`

**Expected output:** The student list resets to show all students.

---

### Viewing a student's details : `get /students <studentId>`

Shows the details of a specific student.

Format: `get /students <studentId>`

* The `studentId` is automatically generated.
* Student IDs are shown in the format `S1`, `S2`, `S3`, ...

Example:

* `get /students S1`

**Expected output:** The selected student's details are shown in the app.

![Get student result](images/get-student.png)

---

### Viewing student milestones : `get /students ... /milestones`

Shows the milestone progress of a student.

Format: `get /students <studentId> /milestones`

Rules:

* Each milestone corresponds to one assignment.
* A student sees milestones only for assignments that share **at least one group** with the student.
* The milestone list is shown in assignment order.
* Stored milestone statuses are `NOT_STARTED` and `COMPLETED`.
* `OVERDUE` is computed automatically for display.

Example:

* `get /students S1 /milestones`

Example milestone output:

* `A1 | NOT_STARTED | due=2026-04-01 | completedAt=-`
* `A2 | COMPLETED | due=2026-04-03 | completedAt=2026-03-30T1200H`
* `A3 | OVERDUE | due=2026-03-20 | completedAt=-`

> **Tip:**
> This command is useful when preparing for a lesson and you want to check a student's outstanding work quickly.
{: .tip}

**Expected output:** The student's milestone progress is shown.

![Milestone view result](images/milestone-view.png)

---

### Updating a milestone : `set /students ... /milestones`

Sets the milestone status of one assignment for one student.

Format: `set /students <studentId> /milestones <assignmentId> <status> [completedAt]`

Rules:

* `studentId` values look like `S1`, `S2`, `S3`, ...
* `assignmentId` values look like `A1`, `A2`, `A3`, ...
* Only the following stored statuses are allowed:

   * `NOT_STARTED`
   * `COMPLETED`
* If the status is `NOT_STARTED`, do **not** provide `completedAt`.
* If the status is `COMPLETED`, you **must** provide `completedAt`.
* `OVERDUE` cannot be set manually.
* The student and assignment must share **at least one group**.

Examples:

* `set /students S1 /milestones A1 NOT_STARTED`
* `set /students S1 /milestones A1 COMPLETED 2026-03-30T1200H`

> **Note:**
> Use `NOT_STARTED` if you want to reset a milestone to incomplete.
{: .note}

**Expected output:** The milestone status is updated and a confirmation message is shown.

![Set milestone result](images/set-milestone.png)

---

### Listing all assignments : `get /assignments`

Shows all assignments currently in LeTutor.

Format: `get /assignments`

**Expected output:** The assignment list shows all assignments.

---

### Viewing a specific assignment : `get /assignments <assignmentId>`

Shows the selected assignment in the app.

Format: `get /assignments <assignmentId>`

Rules:

* Assignment IDs are automatically generated.
* Assignment IDs are shown in the format `A1`, `A2`, `A3`, ...

Example:

* `get /assignments A1`

**Expected output:** The selected assignment is shown in the assignment panel.

---

### Finding students by name : `find /students`

Finds students whose names contain any of the given keywords.

Format: `find /students <keywords>`

Rules:

* Search is case-insensitive.
* Keyword order does not matter.
* Only full words are matched.
* Students matching at least one keyword will be returned.

Examples:

* `find /students Johnny`
* `find /students alex david`

> **Tip:**
> Use this command before editing or deleting a student if you need to narrow down the list first.
{: .tip}


**Expected output:** Only matching students remain visible in the student list.

![Find students result](images/find-students.png)

---

### Finding by group : `find /groups`

Finds all students and assignments that belong to the specified group.

Format: `find /groups <groupName>`

Rules:

* The student list is filtered to show only students in the specified group.
* The assignment list is filtered to show only assignments tagged to that group.
* The group name should match an existing group name exactly.
* If no matching group is found, no students and no assignments will be shown.

Example:

* `find /groups T08`

**Expected output:** The result display shows a summary such as:

* `X persons listed and Y assignments listed for Group "T08"`

![Find groups result](images/find-groups.png)

---

### Editing a student : `edit /students`

Edits the details of an existing student.

Format: `edit /students <studentId> {<name>; <phone>; <email>; <groups>}`

Rules:

* The `studentId` identifies which student to edit.
* You may leave fields empty if you do not want to change them.
* The groups field follows the same format as `add /students`.

Examples:

* `edit /students S2 {John Doe; 98765432; johnd@mail.com; Sec3B}`
* `edit /students S2 {John; ; ;}`

> **Tip:**
> Use empty fields carefully. Keep the semicolons in place so LeTutor can tell which field you are skipping.
{: .tip}

**Expected output:** The student's details are updated and a confirmation message is shown.

![Edit student result](images/edit-student.png)

---

### Editing an assignment : `edit /assignments`

Edits the details of an existing assignment.

Format: `edit /assignments <assignmentId> {<label>; <groups>; <dueDate>}`

Rules:

* The `assignmentId` identifies which assignment to edit.
* You may leave fields empty if you do not want to change them.
* The groups field supports multiple groups separated by commas.

Examples:

* `edit /assignments A1 {A-Testing; Sec3A; 2026-05-30}`
* `edit /assignments A2 {Math Worksheet; Sec3A, Sec3B; 2026-05-01}`
* `edit /assignments A3 {Quiz 2; ; }`

**Expected output:** The assignment is updated and a confirmation message is shown.

![Edit assignment result](images/edit-assignment.png)

---

### Deleting a student : `delete /students`

Deletes a student from LeTutor.

Format: `delete /students <studentId>`

Example:

* `delete /students S3`

> **Warning:**
> Deletion is permanent and cannot be undone within the app.
{: .warning

**Expected output:** The student is removed and a confirmation message is shown.

![Delete student result](images/delete-student.png)

---

### Deleting an assignment : `delete /assignments`

Deletes an assignment from LeTutor.

Format: `delete /assignments <assignmentId>`

Example:

* `delete /assignments A2`

> **Warning:**
> Deleting an assignment removes it from the system permanently.
{: .note}

**Expected output:** The assignment is removed and a confirmation message is shown.

![Delete assignment result](images/delete-assignment.png)

---

### Clearing all entries : `clear`

Clears all student and assignment entries from LeTutor.

Format: `clear`

> **Warning:**
> This permanently deletes all student and assignment data in the app.
{: .note}

**Expected output:** The lists become empty and a confirmation message is shown.

---

### Exiting the program : `exit`

Closes LeTutor.

Format: `exit`

**Expected output:** The application closes. All changes have already been saved automatically.

---

### Saving the data

LeTutor saves data automatically to the hard disk after every command that changes data. There is no need to save manually.

---

### Editing the data file

LeTutor data is saved automatically as a JSON file at:

`[JAR file location]/data/addressbook.json`

Advanced users may update the data file directly.

> **Caution:**
> If the file is edited into an invalid format, LeTutor may discard the data and start with an empty file on the next run.
{: .caution}

> **Warning:**
> Only edit the data file if you are confident that you understand the structure.
{: .warning

---

## FAQ

**Q: How do I move my data to another computer?**
A: Install LeTutor on the other computer and replace its generated data file with the `addressbook.json` file from your original LeTutor folder.

**Q: Why can't I see all students after using `find /students` or `find /groups`?**
A: Those commands filter the current view. Run `list` to return to the full student list.

**Q: Why does `get /students S1 /milestones` not show every assignment in the system?**
A: A student only sees assignments that share at least one group with that student.

**Q: Can I manually set a milestone to `OVERDUE`?**
A: No. `OVERDUE` is computed automatically by the app based on due date and completion status.

**Q: Can one assignment belong to more than one group?**
A: Yes. Separate group names with commas when adding or editing an assignment.

---

## Known Issues

1. **When using multiple screens**, if you move the application to a secondary screen and later return to using only one screen, the GUI may open off-screen. Delete the `preferences.json` file and relaunch the app.

2. **If the Help Window is minimised**, running `help` again may not open a new help window. Restore the minimised help window manually.

---

## Command Summary

| Action                     | Format                                                                        | Example                                                         |
| -------------------------- | ----------------------------------------------------------------------------- | --------------------------------------------------------------- |
| **Help**                   | `help`                                                                        | `help`                                                          |
| **Add student**            | `add /students {<name>; <phone>; <email>; <groups>}`                          | `add /students {John Doe; 98765432; johnd@example.com; Sec3A}`  |
| **Add assignment**         | `add /assignments {<label>; <groups>; <dueDate>}`                             | `add /assignments {Math; Sec3A, Sec3B; 2026-03-20}`             |
| **List students**          | `list`                                                                        | `list`                                                          |
| **List assignments**       | `get /assignments`                                                            | `get /assignments`                                              |
| **Get student**            | `get /students <studentId>`                                                   | `get /students S3`                                              |
| **Get student milestones** | `get /students <studentId> /milestones`                                       | `get /students S1 /milestones`                                  |
| **Set milestone**          | `set /students <studentId> /milestones <assignmentId> <status> [completedAt]` | `set /students S1 /milestones A1 COMPLETED 2026-03-30T1200H`    |
| **Get assignment**         | `get /assignments <assignmentId>`                                             | `get /assignments A2`                                           |
| **Find students**          | `find /students <keywords>`                                                   | `find /students alex david`                                     |
| **Find groups**            | `find /groups <groupName>`                                                    | `find /groups Science`                                          |
| **Edit student**           | `edit /students <studentId> {<name>; <phone>; <email>; <groups>}`             | `edit /students S1 {John Doe; 98765432; johnd@mail.com; Sec3B}` |
| **Edit assignment**        | `edit /assignments <assignmentId> {<label>; <groups>; <dueDate>}`             | `edit /assignments A1 {Quiz 2; Sec3A, Sec3B; 2026-04-01}`       |
| **Delete student**         | `delete /students <studentId>`                                                | `delete /students S3`                                           |
| **Delete assignment**      | `delete /assignments <assignmentId>`                                          | `delete /assignments A2`                                        |
| **Clear**                  | `clear`                                                                       | `clear`                                                         |
| **Exit**                   | `exit`                                                                        | `exit`                                                          |
