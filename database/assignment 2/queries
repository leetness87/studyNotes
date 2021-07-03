-----1.1 two variance as i didnt know if wanted a list of the interests or a flat number as to how many
SELECT *
  FROM interest
 WHERE descrip IS NULL;
 
SELECT count( * ) 
  FROM interest
 WHERE descrip IS NULL;
;

-----1.2 
SELECT DISTINCT instname
  FROM department
 WHERE state = 'NSW' OR 
       state = 'WA'
 ORDER BY instname DESC;

-----1.3
SELECT title,
       count( * ) AS COUNT
  FROM field
 GROUP BY title
HAVING count( * ) > 1;

-----1.4
SELECT a1.ACNUM,
       a1.FAMNAME,
       a1.GIVENAME
  FROM academic a1
       JOIN
       author a2 ON a1.ACNUM = a2.ACNUM
       JOIN
       paper p1 ON a2.panum = p1.panum
 WHERE p1.title LIKE '%spline%';

-----1.5
SELECT d1.instname,
       d1.deptname
  FROM department d1
       LEFT JOIN
       academic a1 ON d1.deptnum = a1.deptnum
 WHERE a1.deptnum IS NULL AND 
       d1.state IS 'Qld' OR 
       'QLD';

-----1.6 this took some time kept thinking to do sub-clause order by because i have ocd with numbers
SELECT DISTINCT a1.ACNUM,
                a1.FAMNAME,
                a1.GIVENAME
  FROM academic a1
       JOIN
       academic a2
       JOIN
       author u1
       JOIN
       author u2 ON a2.acnum = u2.ACNUM AND 
                    u1.panum = u2.panum AND 
                    a1.acnum = u1.acnum
                    WHERE a1.acnum <> a2.acnum AND 
       u1.acnum <> u2.acnum AND 
       a2.famname = 'Baliga' AND 
       a2.givename = 'Lance'
 ORDER BY a1.acnum;

-----2.1
SELECT instname,
       deptname
  FROM department
 WHERE deptnum NOT IN (
           SELECT deptnum
             FROM academic
       )
AND 
       state IS 'Qld' OR 
       'QLD';

-----2.2
SELECT instname,
       deptname
  FROM department
 WHERE NOT EXISTS (
               SELECT deptnum
                 FROM academic
                WHERE department.DEPTNUM = academic.DEPTNUM
           )
AND 
       state IS 'Qld' OR 
       'QLD';

-----2.3 way easier than 1.6 again order by because of number ocd
SELECT DISTINCT a1.acnum,
                a1.givename,
                a1.famname
  FROM academic a1
       JOIN
       author a2 ON a1.ACNUM = a2.ACNUM
 WHERE a2.panum IN (
           SELECT panum
             FROM author
                  JOIN
                  academic ON author.ACNUM = academic.ACNUM
            WHERE academic.givename = 'Lance' AND 
                  academic.famname = 'Baliga'
       )
AND 
       a1.famname <> 'Baliga'
 ORDER BY a1.acnum;
;
 
----2.4 
SELECT deptname
  FROM department
 GROUP BY deptname
HAVING count( * ) = (
                        SELECT max(count) 
                          FROM (
                                   SELECT deptname,
                                          count( * ) AS count
                                     FROM department
                                    GROUP BY deptname
                               )
                    );

-----3.1 
SELECT acnum
  FROM interest
 GROUP BY acnum
HAVING count( * ) >= 5
EXCEPT
SELECT acnum
  FROM author;

-----3.2 hope this is right i keep thinking mathematically it works probably easier way im missing
SELECT acnum
  FROM interest
 WHERE fieldnum IN (
           SELECT fieldnum
             FROM interest
            WHERE acnum = 114
       )
 GROUP BY acnum
HAVING count( * ) = (
                        SELECT max(count) 
                          FROM (
                                   SELECT fieldnum,
                                          count( * ) AS count
                                     FROM interest
                                    WHERE acnum = 114
                               )
                    )
EXCEPT
SELECT acnum
  FROM interest
 WHERE acnum = 114;

-----3.3
SELECT instname,
       deptname
  FROM department
 WHERE state IS 'Qld' OR 
       'QLD'
EXCEPT
SELECT instname,
       deptname
  FROM department
       JOIN
       academic ON department.DEPTNUM = academic.DEPTNUM;


-----4.1a
CREATE TABLE Member (
    memberID  INTEGER (4)  NOT NULL,
    firstName VARCHER (20),
    surname   VARCHAR (20),
    phone     INTEGER (10),
    PRIMARY KEY (
        memberID
    )
);
-----4.1b
CREATE TABLE Workshop (
    workshopID   INTEGER (3)   NOT NULL,
    title        VARCHAR (40),
    sessionDate  DATE,
    price        MONEY,
    qtyRemaining INTEGER (100),
    PRIMARY KEY (
        workshopID
    )
);
-----4.1c
CREATE TABLE Booking (
    memberID   INTEGER     NOT NULL,
    workshopID INTEGER     NOT NULL,
    qtyTickets INTEGER (2),
    FOREIGN KEY (
        memberID
    )
    REFERENCES member,
    FOREIGN KEY (
        workshopID
    )
    REFERENCES Workshop,
    PRIMARY KEY (
        workshopID,
        memberID
    )
);
-----4.2a
INSERT INTO Member VALUES (
                       1000,
                       'Aksha',
                       'Shaikh',
                       456123456
                   ),
                   (
                       1001,
                       'Eric',
                       'Rostron',
                       439774776
                   ),
                   (
                       1002,
                       'Zhe',
                       'Ding',
                       459914321
                   );
-----4.2b
INSERT INTO Workshop VALUES (
                         100,
                         'Firing a steam locomotive',
                         date('2021-12-31'),
                         89.95,
                         2
                     ),
                     (
                         101,
                         'Rail signalling',
                         date('2021-12-17'),
                         55.5,
                         9
                     ),
                     (
                         102,
                         'Shunt rolling stock',
                         date('2021-02-03'),
                         78.0,
                         6
                     );
-------4.2c
INSERT INTO booking VALUES (
                        1001,
                        102,
                        2
                    ),
                    (
                        1000,
                        101,
                        1
                    ),
                    (
                        1002,
                        102,
                        1
                    );

;
-----4.3 this works im unsure on the exact reason for the metadata from the query at bottom if that is a problem or not
CREATE VIEW sales (
    workshopID,
    title,
    date,
    totalsales,
    qtyRemaining
)
AS
    SELECT w.workshopID,
           w.title,
           strftime('%d/%m/%Y', sessiondate),
           sum(b.qtytickets),
           w.qtyRemaining
      FROM workshop w
           JOIN
           booking b ON w.workshopID = b.workshopID
     GROUP BY title;
----4.3 view 
SELECT *
  FROM sales;


    
