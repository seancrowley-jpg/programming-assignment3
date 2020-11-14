Name : Sean Crowley



Unit tests succeed completely: Outstanding Tests

Which level unit tests succeed partially: All Tests Pass

------------------------------------------------------------------------------------------------------------------------
Self reflection - Grading Spectrum Level:

I believe the application falls into the Outstanding category as the project meets the requirements up to the Excellent
Level and includes some of the aspects of the Outstanding category.
====================
Pass:

Data Model - The project has implemented the Person, Member and Trainer objects.
API - Line(50,69/147,159) Add Members/Trainers,
      Line(255,268) numberOfMembers/Trainers,
      Line(283,304) isValidMember/TrainerIndex
Menu - Registration, Add New Member and List all members are options in the menu.
Input Validation is present on height, name, gender and statingWeight
===================
Good:

XML Persistence - The project load data from the XML file on startup and saves to the XML file whenever the user makes
                  a change.
Menu - Login and the Member sub-menu are options in the menu.
       A Member can view their profile with the method at Line(633) in the GymAPI class and update their profile using
       the method at Line(647).
=================
Very Good:

API - Member search methods:
                            searchByName - Line(364)
                            searchByEmail - Line(327,342)
      Trainer search methods:
                            searchByName - Line(391)
                            searchByEmail - Line(419
Menu - The menu has incorporated the Trainer sub-menu which has the options to add a Member,List the Members in the gym
       and finally to search for them by email.
=================
Excellent:

Data Model - The project has implemented support for the Assessment object.
API - The GymUtility is implemented with the specified methods.
Menu - The Trainer menu can can add assessments for members using the method at Line(196). The Trainer can update a
       comment on an existing assessment with the method at Line(699).
================
Outstanding:

Data Model - Student and Premium members are supported and can be added by selecting the correct packages when the add
             member method at Line (69) is called.
API - I believe i have managed to implement all of the specified methods(features) required by the assignment spec.
Menu - The progress sub menu has two methods showWeightProgress at Line(730) and showWaistProgress at Line(749).
       Although the weight and waist stats are displayed in order of the Assessments key (date) I was not able to
       display to the user whether their stats had improved from the last assessment or not.
Tests- All Unit Tests Pass.

------------------------------------------------------------------------------------------------------------------------
A statement of how much of the application specification you implemented:

I have completed almost all of the required specification except for one aspect of the Progress Sub-Menu.
The assessments are sorted by date but they do not indicate if the previous assessment is higher or lower than
the current.
------------------------------------------------------------------------------------------------------------------------
Any extra features you wish to bring to the assessors attention, i.e. extra functionality, Java syntax not covered in
the lectures, non-standard Libraries used:
I used the Map.Entry to list the assessment keys in order with their corresponding value. Previously I was only able to
display the dates in order by creating a new HashMap and adding the assessment values into it along with the sortedSet
of keys returned from the method in the Member class sortedAssessmentDates() at Line (132). But this did not display
the correct value associated with the date.

Map.Entry allows the use of the in a for each loop entrySet() which returns a set of the maps entries.
With the TreeMap this meant the keys and the values could be printed to the user in the correct order as the TreeMap is
used to sort keys by their natural order.

Known bugs/problems : Some Scanner bugs. Require user to enter a key again to continue.

Any sources referred to during the development of the assignment (no need to reference lecture/lab materials):
https://stackoverflow.com/questions/8689725/map-entry-how-to-use-it
https://beginnersbook.com/2013/12/hashmap-in-java-with-example/