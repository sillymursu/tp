# LeTutor Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

*   This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org/).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete /students S1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `AssignmentListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete /students S1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser`
  (`XYZ` is a placeholder for the specific command name, e.g. `AddCommandParser`) which uses other parser
  classes to parse the user command and create the corresponding `XYZCommand` object.

* For overloaded commands that support multiple paths, `AddressBookParser` first inspects the command path
  before choosing the parser. For example:
    * `find /students ...` is delegated to `FindCommandParser`
    * `find /groups ...` is delegated to `FindGroupCommandParser`

* All `XYZCommandParser` classes (e.g. `AddCommandParser`, `DeleteCommandParser`, `FindGroupCommandParser`)
  inherit from the `Parser` interface so that they can be treated similarly where possible, such as during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />

The `Model` component,

* stores the address book data i.e., all `Person` and `Assignment` objects (which are contained in a
  `UniquePersonList` and `UniqueAssignmentList` objects respectively).
* **`Person`** entities have the following value object fields: `StudentId`, `Name`, `Phone`, `Email`, and `Group`
  (a student can belong to multiple groups).
* **`Assignment`** entities have the following value object fields: `AssignmentId`, `Label`, `Set<Group>`, and `DueDate`
  (an assignment can belong to multiple groups).
* stores the currently 'selected' `Person`/`Assignment` objects (e.g., results of a search query) as separate
  _filtered_ lists which are exposed to outsiders as unmodifiable `ObservableList`s that can be observed.
  For example, the UI can be bound to these lists so that it automatically updates when the filtered data changes.
* supports filtering both students and assignments together for commands such as `find /groups`, allowing the
  student panel and assignment panel to stay in sync.
* stores a `UserPref` object that represents the user's preferences. This is exposed to the outside as a
  `ReadOnlyUserPref` object.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain,
  they should make sense on their own without depending on other components).
* has a hidden `GroupManager` object that keeps track of the `Group` objects used by `Person` and `Assignment` objects.

**Note:** The current model shows the core domain entities (`Person`, `Assignment`, and their value objects).
Additional subsystems such as `MilestoneStore`, `GroupManager`, and the milestone model classes are managed by
`AddressBook` and `ModelManager` but are intentionally simplified in this diagram for clarity. Refer to the full
model code in `src/main/java/seedu/address/model` for the complete system structure.


</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Find group command

#### Implementation

The following sequence diagram shows the overall sequence within the `Logic` component:

<puml src="diagrams/FindGroupSequenceDiagram.puml" />

The following sequence diagram shows how the `Model` component handles the command to find groups:

<puml src="diagrams/FindGroupsModelSequenceDiagram.puml" />

The `find /groups` command allows the user to filter both the student list and the assignment list by a group name.

The command flow is as follows:

1. The user enters `find /groups <groupName>`.
2. `AddressBookParser` identifies the top-level command word `find`.
3. If the command path starts with `/groups`, `AddressBookParser` delegates parsing to `FindGroupCommandParser`.
4. `FindGroupCommandParser` validates the input format and constructs a `FindGroupCommand` using a `GroupName`.
5. When executed, `FindGroupCommand` calls `Model#setFilteredPersonsAndAssignmentsByGroups(GroupName)`.
6. `ModelManager` searches for the matching `Group`, retrieves the `StudentId`s and `AssignmentId`s associated with it,
   and updates both filtered lists:
    * `filteredPersons` shows only students in the specified group
    * `filteredAssignments` shows only assignments in the specified group
7. If no matching group is found, both filtered lists are set to empty.
8. The command returns a `CommandResult` summarising the number of students and assignments shown.

#### Design considerations

**Aspect: How group filtering is performed**

* **Current choice:** Filter both lists using the `Group` object’s stored `StudentId` and `AssignmentId` collections.
    * Pros:
        * Reuses the existing `GroupManager` / group bookkeeping structure.
        * Keeps the command logic simple, because `FindGroupCommand` only needs to call a single model method.
        * Ensures the student and assignment panels stay consistent with each other after filtering.
    * Cons:
        * The command currently depends on an exact `GroupName` match.
        * If no matching group exists, the result is an empty filtered view rather than a fuzzy or partial match.

* **Alternative:** Scan all students and assignments directly and match them by group membership each time.
    * Pros:
        * Could support more flexible matching in the future, such as case-insensitive or partial-name search.
    * Cons:
        * Duplicates logic that is already represented in the group-based data structure.
        * Makes the command path less aligned with the existing model design.

### Edit student command

#### Implementation

The following sequence diagram shows the overall sequence within the `Logic` component for the `edit /students` command:

<puml src="diagrams/EditStudentSequenceDiagram.puml" />

The `edit /students` command allows the user to modify one or more fields of an existing student while leaving the remaining fields unchanged.

The command flow is as follows:
1. The user enters `edit /students <studentId> {<name>; <phone>; <email>; <groups>}`.
2. `AddressBookParser` identifies the top-level command word `edit`.
3. If the command path starts with `/students`, `AddressBookParser` delegates parsing to `EditCommandParser`.
4. `EditCommandParser` validates the input format using a regular expression, parses the `StudentId`, and constructs an `EditCommand` using an `EditPersonDescriptor`.
5. Only non-empty fields are written into the descriptor. Empty fields are treated as “leave unchanged”.
6. If no fields are edited, parsing fails with `MESSAGE_NOT_EDITED`.
7. When executed, `EditCommand` searches the current filtered student list for the student with the given `StudentId`.
8. `EditCommand` creates a new edited `Person` object by combining the existing student data with the edited fields from the descriptor.
9. Before saving, `EditCommand` checks for duplicate person identity, duplicate phone number, and duplicate email address.
10. If validation passes, `EditCommand` updates the student in the model using `Model#setPerson(Person, Person)`.
11. The command then removes the student from the old groups and adds the student to the edited groups.
12. The filtered student list is reset to show all students.
13. The command returns a `CommandResult` summarising the edited student.

#### Design considerations

**Aspect: How partial student edits are represented**

* **Current choice:** Use an `EditPersonDescriptor` containing optional edited fields, then build a new `Person` with `createEditedPerson(...)`.
    * Pros:
        * Supports partial edits naturally.
        * Preserves the immutability-style design of `Person`.
        * Keeps parsing and command execution responsibilities cleanly separated.
    * Cons:
        * Requires an additional descriptor class and merging logic.

* **Alternative:** Mutate the existing `Person` object directly during parsing or command execution.
    * Pros:
        * Reduces the number of temporary objects created.
    * Cons:
        * Makes validation and rollback harder.
        * Couples parsing more tightly with model updates.
        * Conflicts with the current value-object style used in the model.

**Aspect: How the target student is resolved**

* **Current choice:** Search the currently filtered student list using `StudentId`.
    * Pros:
        * Keeps the command aligned with what the user currently sees in the UI.
        * Reuses the filtered-list workflow already used by other student commands.
    * Cons:
        * A student that exists but is not currently shown in the filtered list cannot be edited through this path.

* **Alternative:** Look up the student directly from the full address book by `StudentId`.
    * Pros:
        * Makes editing independent of the current filtered view.
    * Cons:
        * Reduces consistency with the visible UI state.
        * Introduces a different access pattern from the existing student edit implementation.

### Edit assignment command

#### Implementation

The following sequence diagram shows the overall sequence within the `Logic` component for the `edit /assignments` command:

<puml src="diagrams/EditAssignmentSequenceDiagram.puml" />

The `edit /assignments` command allows the user to modify one or more fields of an existing assignment while leaving the remaining fields unchanged.

The command flow is as follows:
1. The user enters `edit /assignments <assignmentId> {<label>; <groups>; <dueDate>}`.
2. `AddressBookParser` identifies the top-level command word `edit`.
3. If the command path starts with `/assignments`, `AddressBookParser` delegates parsing to `EditAssignmentCommandParser`.
4. `EditAssignmentCommandParser` validates the command path, parses the `AssignmentId`, and parses the 3-field tuple using `parseTuple3AllowEmpty(...)`.
5. Only non-empty fields are written into the descriptor. Empty fields are treated as “leave unchanged”.
6. If the groups field is provided, it is split by commas and parsed into a `Set<Group>`.
7. If no fields are edited, parsing fails with `MESSAGE_NOT_EDITED`.
8. When executed, `EditAssignmentCommand` retrieves the target assignment from the model using `AssignmentId`.
9. `EditAssignmentCommand` creates a new edited `Assignment` object by combining the existing assignment data with the edited fields from the descriptor.
10. Before saving, `EditAssignmentCommand` checks whether the edited assignment would duplicate an existing assignment.
11. If validation passes, `EditAssignmentCommand` updates the assignment in the model using `Model#setAssignment(Assignment, Assignment)`.
12. The command then removes the assignment from the old groups, removes any now-empty groups where applicable, and adds the assignment to the edited groups.
13. The filtered assignment list is reset to show all assignments.
14. The command returns a `CommandResult` summarising the edited assignment.

#### Design considerations

**Aspect: How partial assignment edits are represented**

* **Current choice:** Use an `EditAssignmentDescriptor` containing optional edited fields, then build a new `Assignment` with `createEditedAssignment(...)`.
    * Pros:
        * Supports partial edits without introducing many parser branches.
        * Keeps the command logic consistent with the student edit flow.
        * Preserves the existing value-object style of `Assignment`.
    * Cons:
        * Requires descriptor-copying and field-merging logic.

* **Alternative:** Replace the tuple-based edit flow with a fully prefixed syntax for each editable field.
    * Pros:
        * Makes optional fields more explicit in the command format.
    * Cons:
        * Would require a larger parser redesign.
        * Makes the edit command family less consistent with the current tuple-based syntax.

**Aspect: How edited assignment-group relationships are updated**

* **Current choice:** Update the assignment first, then remove old group links and add new group links.
    * Pros:
        * Keeps group membership data consistent with the edited assignment.
        * Allows empty groups to be cleaned up immediately.
        * Reuses the existing group bookkeeping structure already present in the model.
    * Cons:
        * Requires multiple model operations after the main assignment replacement.

* **Alternative:** Recompute group-to-assignment relationships lazily only when group views are requested.
    * Pros:
        * Reduces the amount of work done inside the edit command itself.
    * Cons:
        * Risks stale or inconsistent group data between commands.
        * Pushes additional complexity into later read operations.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Manages a significant number of students across 2 \- 3 groups
* Manages student assignments and learning progress
* Needs quick access to summarised student information
* Needs to keep track of group assignment information
* Needs to identify struggling students
* Needs to keep track of students in groups
* Prefers using a keyboard CLI over complex graphical interface

**Value proposition**:

All-in-one student management system tailored for tutors and teaching assistants, relieving the need to use more than 1 application to track student information.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                                                     | So that I can…​                                                      |
|----------|--------------------------------------------|------------------------------------------------------------------|----------------------------------------------------------------------|
| `* * *`  | Account Owner | assign roles (Tutor/Teaching Assistant) to each contact          | can organise contacts and enable the correct role-specific workflows |
| `* * *`  | Account Owner | add a profile with contact details                               | can keep all contacts in one place                                   |
| `* * *`  | Account Owner | search and open a profile by name                                | can access the profile's information quickly during teaching         |
| `* * *`  | Account Owner | edit a profile with contact details                              | can change profile contact information efficiently                   |
| `* * *`  | Account Owner | add students to groups                                           | can assign the same assignment to a group of students efficiently    |
| `* * *`  | Account Owner | delete a profile with contact details                            | can remove redundant contacts                                        |
| `* * *`  | Tutor | add Student assignments to particular groups                     | can keep track of assignments efficiently                            |
| `* * *`  | Tutor | edit Student assignments                                         | can change assignments when the need arises                          |
| `* * *`  | Tutor | delete Student assignments                                       | remove redundant assignments when the need arises                    |
| `* *`  | Tutor | view a list of Student profiles with assignment submission status | can identify missing submissions at a glance                         |
| `* *`  | Tutor | view students and assignment in a group                          | can keep track of groups at a glance                                 |

## Use cases

### Use case 1: Add Student Profile

Name: Add Student Profile

Actor: Tutor, TA

System: LeTutor

**MSS**

1. Tutor inputs command to add student.
2. Tutor enters a valid name and at least one valid contact (phone or email).
3. System validates appropriate fields.
4. System checks for duplicates.
5. UI shows confirmation “Student added.”.

    Use case ends.

**Extensions**

* 3a. Invalid field formats

     * 3a1. System shows an error message

        Use case resumes at step 3.

* 4a. Duplicate detected

    * 4a1. System shows an error message

      Use case resumes at step 3.

---

### Use case 2: Search and View Student Profile

Name: Search and View Student Profile

Actor: Tutor, TA

System: LeTutor

Preconditions: At least one student exists in the Tutor's directory

**MSS**

1. Tutor searches for student.
2. System performs a case-insensitive, partial match on student names.
3. System displays list of matching students.
4. Tutor selects a student from the list.
5. System shows student profile.

   Use case ends.

**Extensions**

* 3a. No match found.

     * 3a1. System shows an empty list and message saying "No students found"

        Use case ends.

* 4a. Tutor decides not to view the student profile.

  Use case ends.

---

### Use case 3: Edit Student Profile

Name: Edit Student Profile

Actor: Tutor, TA

System: LeTutor

Preconditions: Target student already exists in the directory.

**MSS**

1. Tutor <ins>searches for student (U2) </ins>.
2. Tutor edits student profile.
3. Tutor updates one or more fields.
4. System validates updated fields.
5. System checks for duplicates.
6. System updates the student list.
7. UI shows confirmation "Student updated."

   Use case ends.

**Extensions**

* 4a. Invalid fields formats

     * 4a1. System shows an error message

        Use case resumes at step 3.

* 5a. Duplicate detected

    * 5a1. System shows an error message

      Use case resumes at step 3.

---

### Use case 4: Delete Student Profile

Name: Delete Student Profile

Actor: Tutor, TA

System: LeTutor

Preconditions: Target student already exists in the directory.

**MSS**

1. Tutor <ins>searches for student (U2) </ins>.
2. Tutor deletes student profile.
3. Systems deletes the student and all related fields from this account.
4. UI shows confirmation message "Student deleted."

   Use case ends.

---

### Use case 5: View Assignment/Milestone Progress

Name: View Assignment/Milestone Progress

Actor: Tutor

System: LeTutor

Preconditions: Tutor is signed in, Student exists, Predefined milestone exists for student.


**MSS**

1.  Tutor opens the student profile.
2.  Tutor navigates to the assignments/milestones progress section.
3.  System displays all predefined assignments/milestones as status bubbles.
4.  System shows the current status of each bubble (e.g., completed, pending, overdue).
5.  Tutor reviews the student’s progress across all milestones.

    Use case ends.

**Extensions**

* 3a. No predefined milestones exist

     * 3a1. System shows an empty state message.

        Use case ends.

---

### Use case 6: Mark Milestone as Completed

Name: Mark Milestone as Completed

Actor: Tutor

System: LeTutor

Preconditions: Tutor is signed in as Tutor, Target student exists, A predefined assignment/milestone exists for the student.


**MSS**

1. Tutor opens the student profile.
2. Tutor selects a pending or overdue milestone bubble.
3. Tutor marks the milestone as completed.
4. System saves the updated milestone status.
5. UI updates the bubble to show completed status.

   Use case ends.

**Extensions**
* 4a. Save error

     * 4a1. System displays an error and keeps the previous milestone status

        Use case ends.

---

### Use case 7: Automatically Mark Milestone as Overdue

Name: Automatically Mark Milestone as Overdue

Actor: Tutor

System: LeTutor

Preconditions: A predefined milestone exists with a due date, The milestone is not completed, The due date has passed.

**MSS**

1. System checks milestone due dates periodically or when the student profile is accessed.
2. System identifies milestones whose due dates have passed and are still incomplete.
3. System changes the milestone status to overdue.
4. UI updates the milestone bubble to show overdue status.

   Use case ends.

**Extensions**
* 3a. Status update error

     * 3a1. System logs the error and retains the previous milestone status

        Use case ends.

---

### Use case 8: View Students with Overdue Milestones

Name: View Students with Overdue Milestones

Actor: Tutor

System: LeTutor

Preconditions: Tutor is signed in as Tutor, Students and predefined milestones exist, At least one student has milestone records


**MSS**

1. Tutor opens the student management or progress overview page.
2. Tutor views milestone statuses across multiple students.
3. Tutor sees students with overdue milestone status.
4. Tutor identifies which students are falling behind.

   Use case ends.

**Extensions**
* 3a. No students have overdue milestones.

     * 3a1. System shows that no overdue milestones are present.

        Use case ends.
---

### Use case 9: View the details of an Assignment

Name: View Assignment details

Actor: Tutor, TA

System: LeTutor

**MSS**
1. Tutor or TA chooses to view the details of an Assignment
2. Tutor enters the id of the Assignment
3. System checks if the id is valid
4. System displays the details of the Assignment

   Use case ends.

**Extensions**
* 3a. Invalid id
     * 3a1. System shows an error message

        Use case resumes at step 2.

---

### Use case 10: Add an Assignment

Name: Add Assignment

Actor: Tutor, TA

System: LeTutor

**MSS**
1. Tutor or TA chooses to add an Assignment
2. Tutor or TA enters the label, group and dueDate of the Assignment
3. System checks if the label is valid and not a duplicate
4. System checks if the group and dueDate are valid
5. System adds the Assignment to the list of Assignments
6. UI shows confirmation message "Assignment added."

   Use case ends.

**Extensions**
* 3a. Invalid label or duplicate label
    * 3a1. System shows an error message

      Use case resumes at step 2.
* 4a. Invalid group or dueDate
    * 4a1. System shows an error message

      Use case resumes at step 2.

---

### Use case 11: Edit an Assignment

Name: Edit Assignment

Actor: Tutor, TA

System: LeTutor

Preconditions: Target Assignment already exists in the directory.

**MSS**
1. Tutor <u>searches for Assignment (U10)</u>.
2. Tutor or TA chooses to edit the Assignment
4. Tutor or TA enters the new label, group and dueDate of the Assignment
5. System checks if the new label is valid and not a duplicate
6. System checks if the new group and dueDate are valid
7. System updates the Assignment with the new details
8. UI shows confirmation message "Assignment updated."

    Use case ends.

**Extensions**
* 4a. Invalid label or duplicate label
    * 4a1. System shows an error message

      Use case resumes at step 4.
* 5a. Invalid group or dueDate
    * 5a1. System shows an error message

      Use case resumes at step 4.

___

### Use case 12: Delete an Assignment

Name: Delete Assignment

Actor: Tutor, TA

System: LeTutor

Preconditions: Target Assignment already exists in the directory.

**MSS**
1. Tutor <u>searches for Assignment (U10)</u>.
2. Tutor or TA chooses to delete the Assignment
3. Systems deletes the Assignment from the list of Assignments
4. UI shows confirmation message "Assignment deleted."

    Use case ends.

---

### Use case 13: Add a Group

Name: Add Group

Actor: Tutor, TA

System: LeTutor

Preconditions: Tutor or TA is adding a new student profile or editing an existing student profile.

**MSS**
1. Tutor or TA <u>adds a student profile (U1)</u> or <u>edits a student profile (U3)</u>.
2. Tutor or TA enters a valid group name that does not yet exist.
3. System validates the group name.
4. System checks whether the group already exists.
5. System creates the new group.
6. System associates the student profile with the new group.
7. UI shows the confirmation message for the student add or edit action.

      Use case ends.

**Extensions**
* 3a. Invalid group name
    * 3a1. System shows an error message

      Use case ends.

* 4a. Group already exists
    * 4a1. System does not create a duplicate group
    * 4a2. System associates the student profile with the existing group instead

      Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `17` or above installed.
2.  The system shall support up to 1000 persons while keeping command execution and UI updates for common operations (e.g., add, edit, delete, and search) within 2 seconds on a typical lab laptop.
3.  For frequent tasks such as adding, editing, searching, and deleting records, an experienced user shall be able to complete the task using commands in fewer steps than using the equivalent GUI interactions.
4. The system shall allow an experienced user to add a student profile and record a lesson observation using no more than 8 seconds of command entry time, excluding differences in individual typing speed.
5. For a dataset of up to 120 student contacts (≈ 2–3 class sizes) with notes and tags, the system shall return search/filter results within 1 second and update the UI (including progress bars) within 1 second on a typical lab laptop.
6. The system shall ensure no loss of saved data (contacts, roles, tags, notes, progress, buddy links, submission/score records) after normal application restarts.
7. The system shall enforce role-based access control such that users only view and execute features permitted by their selected role (Tutor/TA/Professor/Student), and role assignments to contacts shall not be modifiable without appropriate permissions (e.g., only the account owner can change their own role and assign roles to contacts).


### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete /students S1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete /students S0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Finding students and assignments by group

1. Filtering by an existing group

    1. Prerequisites: Reset the app with `clear`, then enter the following commands:
        * `add /students {Alice Tan; 91234567; alice@example.com; Science}`
        * `add /students {Bob Lim; 92345678; bob@example.com; Math}`
        * `add /students {Cara Ong; 93456789; cara@example.com; Science}`
        * `add /students {Dana Lee; 94567890; dana@example.com; English}`
        * `add /assignments {Quiz 1; Science; 2026-05-01}`
        * `add /assignments {Worksheet 1; Math; 2026-05-02}`

    2. Test case: `find /groups Science`
       Expected: Only `Alice Tan` and `Cara Ong` remain in the student list. Only `Quiz 1` remains in the assignment list. The status message shows `2 persons listed and 1 assignments listed for Group "Science"`.

    3. Test case: `find /groups Math`
       Expected: Only `Bob Lim` remains in the student list. Only `Worksheet 1` remains in the assignment list. The status message shows `1 persons listed and 1 assignments listed for Group "Math"`.

    4. Test case: `find /groups English`
       Expected: Only `Dana Lee` remains in the student list. The assignment list becomes empty. The status message shows `1 persons listed and 0 assignments listed for Group "English"`.

2. Filtering by a non-existent group

    1. Prerequisites: Use the same data set as above.

    2. Test case: `find /groups History`
       Expected: Both the student list and assignment list become empty. The status message shows `0 persons listed and 0 assignments listed for Group "History"`.

3. Exact-match behavior

    1. Prerequisites: Use the same data set as above.

    2. Test case: `find /groups science`
       Expected: Both lists become empty, because the current implementation matches the group name exactly.

    3. Test case: `find /groups Sci`
       Expected: Both lists become empty, because partial group-name matching is not supported.

4. Invalid command formats

    1. Prerequisites: Any non-empty data set.

    2. Test case: `find`
       Expected: No list changes. An invalid command format message is shown.

    3. Test case: `find /assignments Science`
       Expected: No list changes. An invalid command format message is shown.

5. Restoring the full view after filtering

    1. Prerequisites: Execute any successful `find /groups ...` command.

    2. Test case: `list` followed by `get /assignments`
       Expected: The full student list and full assignment list are shown again.
