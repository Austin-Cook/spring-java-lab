DROP TABLE IF EXISTS `cp`;
DROP TABLE IF EXISTS `cdh`;
DROP TABLE IF EXISTS `csg`;
DROP TABLE IF EXISTS `Classes`;
DROP TABLE IF EXISTS `Students`;


CREATE TABLE `Students`
(
    `studentID` INTEGER NOT NULL PRIMARY KEY,
    `name` TEXT NOT NULL,
    `address` TEXT NOT NULL,
    `phone` TEXT NOT NULL
);

CREATE TABLE `Classes`
(
    `classID` INTEGER NOT NULL PRIMARY KEY,
    `className` TEXT,
    `room` TEXT,
    `professor` TEXT
);

CREATE TABLE `cp`
(
    `className` TEXT NOT NULL,
    `prerequisite` TEXT NOT NULL
);

CREATE TABLE `cdh`
(
    `classID` INTEGER NOT NULL,
    `day` TEXT NOT NULL,
    `hour` TEXT NOT NULL,
    FOREIGN KEY(`classID`) REFERENCES `Classes`(`classID`)
);

CREATE TABLE `csg`
(
    `className` TEXT NOT NULL,
    `studentID` INTEGER NOT NULL,
    `grade` TEXT NOT NULL,
    FOREIGN KEY(`studentID`) REFERENCES `Students`(`studentID`)
);

INSERT INTO Students
    (studentID,name,address,phone)
VALUES
    ('12345', 'C. Brown', '12 Apple St.', '555-1234'),
    ('67890', 'L. Van Pelt', '34 Pear Ave.', '555-5678'),
    ('22222', 'P. Patty', '56 Grape Blvd', '555-9999'),
    ('33333', 'Snoopy', '12 Apple St.', '555-1234');

INSERT INTO Classes
    (classID,className,room,professor)
VALUES
    ('11111', 'CS101', 'Turing Aud.', 'Wilkerson'),
    ('11112', 'EE200', '25 Ohm Hall', 'Rodham'),
    ('11113', 'PH100', 'Newton Lab.', 'Dr. Evil');

INSERT INTO csg
    (className,studentID,grade)
VALUES
    ('CS101', '12345', 'A'),
    ('CS101', '67890', 'B'),
    ('EE200', '12345', 'C'),
    ('EE200', '22222', 'B+'),
    ('EE200', '33333', 'B'),
    ('CS101', '33333', 'A-'),
    ('PH100', '67890', 'C+');

INSERT INTO cp
    (className,prerequisite)
VALUES
    ('CS101', 'CS100'),
    ('EE200', 'EE005'),
    ('EE200', 'CS100'),
    ('CS120', 'CS101'),
    ('CS121', 'CS120'),
    ('CS205', 'CS101'),
    ('CS206', 'CS121'),
    ('CS206', 'CS205'),
    ('EE300', 'EE200'),
    ('PH201', 'PH100');

INSERT INTO cdh
    (classID,day,hour)
VALUES
    ('11111', 'Tu', '10AM'),
    ('11112', 'M', '10AM'),
    ('11112', 'W', '1PM'),
    ('11112', 'F', '10AM'),
    ('11113', 'Th', '11AM');
 