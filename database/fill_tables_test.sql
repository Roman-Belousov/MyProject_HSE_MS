INSERT INTO "manager_of_hse"
---------------------------------------------
("id", "name" , "surname","position"         ) VALUES
---------------------------------------------
(1,'Иван' , 'Иванов' , 'Начальник отдела ОТ' ),
(2,'Петр' , 'Петров' , 'Инженер отдела ОТ'   ),
(3,'Юрий' , 'Сидоров', 'Начальник участка'   );
SELECT setval('manager_of_hse_seq', 3);

INSERT INTO "workers_personal_card"
---------------------------------------------------------------------
("personnel_number", "name" , "surname" , "work_area" , "position"      ) VALUES
---------------------------------------------------------------------
(100,'Владимир' , 'Мальгин'     , 'ЦПП' , 'слесарь-сборщик СК'          ),
(101,'Сергей'   , 'Ковалёв'     , 'ЦПП' , 'слесарь-сборщик СК'          ),
(102,'Евгений'  , 'Вертелко'    , 'ЦПП' , 'слесарь-сборщик СК'          ),
(103,'Михаил'   , 'Майоров'     , 'ЦПП' , 'газоэлектросварщик'          ),
(104,'Алексей'  , 'Барановский' , 'ЦПП' , 'жестянщик'                   );
SELECT setval('employee_personnel_number_seq', 104);

INSERT INTO "labor_protection_instructions"
---------------------------------------------------------------------
("serial_number", "name" , "date_of_creation" , "expiration_date"   ) VALUES
---------------------------------------------------------------------
(1,'По общим вопросам ОТ'      , '02.01.2020' , '02.01.2023'        ),
(2,'По оказанию первой помощи' , '25.11.2019' , '25.11.2022'        ),
(3,'По пожарной безопасности'  , '11.05.2018' , '11.05.2021'        );


INSERT INTO "category"
--------------------------
("id", "name"     ) VALUES
--------------------------
(1   , 'Для кухни'),
(2   , 'Для ванны');
SELECT setval('category_id_seq', 2);

INSERT INTO "product"
------------------------------------------------------------------------------
("id", "category_id" , "name"        , "price", "amount", "date"      ) VALUES
------------------------------------------------------------------------------
(1   , 1             , 'Ложка'       , 123    , 20      , '2020-03-13'),
(2   , 2             , 'Зубная щётка', 234    , 10      , '2020-03-13'),
(3   , 1             , 'Нож'         , 345    , 30      , '2020-03-13'),
(4   , 2             , 'Бритва'      , 456    , 5       , '2020-03-13');
SELECT setval('product_id_seq', 4);

INSERT INTO "bankaccounts"
------------------------------------------------------------------------------
("id", "accountnumber" , "accountbalance" ) VALUES
------------------------------------------------------------------------------
(1   , 1               , 1200             ),
(2   , 2               , 1300             ),
(3   , 3               , 1400             ),
(4   , 4               , 1500             );
SELECT setval('product_id_seq', 4);

