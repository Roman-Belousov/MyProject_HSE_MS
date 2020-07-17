
CREATE TABLE "manager_of_hse" (
	"id"  SERIAL PRIMARY KEY,
	"name" TEXT NOT NULL,
	"surname" TEXT NOT NULL,
	"position" TEXT NOT NULL
);
CREATE TABLE "instruction_type" (
	"id" SERIAL PRIMARY KEY,
	"name" TEXT NOT NULL
);

CREATE TABLE "instruction" (
    "id"  SERIAL PRIMARY KEY,
	"serial_number" INTEGER NOT NULL UNIQUE,
	"name" TEXT NOT NULL,
	"date_of_creation" DATE NOT NULL,
	"expiration_date" DATE NOT NULL,
	"filename" TEXT NOT NULL,
	"instruction_type_id" INTEGER NOT NULL REFERENCES "instruction_type" 
);

CREATE TABLE "workers_personal_card" (
    "id" SERIAL PRIMARY KEY,
	"personnel_number" INTEGER NOT NULL UNIQUE,
	"name" TEXT NOT NULL,
	"surname" TEXT NOT NULL,
	"work_area" TEXT NOT NULL,
	"position" TEXT NOT NULL,
	"date_of_briefing" DATE,
	"briefing_type" TEXT,
	"serial_number_id" INTEGER  ,

	"manager_of_hse_id" INTEGER  REFERENCES "manager_of_hse" ON DELETE RESTRICT	
);

CREATE TABLE "briefing_type" (
	"id" SERIAL PRIMARY KEY,
	"name" TEXT NOT NULL
);

CREATE TABLE "workflow_journal" (
    "id" SERIAL PRIMARY KEY,
    "instruction_id" INTEGER NOT NULL REFERENCES "instruction", 
    "briefing_type_id" INTEGER NOT NULL REFERENCES "briefing_type",
    "workers_personal_card_id" INTEGER NOT NULL REFERENCES "workers_personal_card",
    "briefing_date" DATE
);


CREATE TABLE "user" (
	"id" SERIAL PRIMARY KEY,
	"login" TEXT NOT NULL UNIQUE,
	"password" TEXT NOT NULL,
	"role" SMALLINT NOT NULL CHECK ("role" IN (0, 1, 2))
	/*
	 * 0 - ADMIN
	 * 1 - MANAGER_OF_DEPARTMENT_HSE
	 * 2 - WORK_AREA_MANAGER
	 */
);

CREATE TABLE "order_type" (
	"id" SERIAL PRIMARY KEY,
	"name" TEXT NOT NULL
);

CREATE TABLE "order" (
    "id"  SERIAL PRIMARY KEY,
	"serial_number" INTEGER NOT NULL UNIQUE,
	"name" TEXT NOT NULL,
	"date_of_creation" DATE NOT NULL,
	"expiration_date" DATE NOT NULL,
	"filename" TEXT NOT NULL,
	"order_type_id" INTEGER NOT NULL REFERENCES "order_type",
	"executer_id" INTEGER NOT NULL REFERENCES "user"
);









CREATE TABLE "category" (
	"id" SERIAL PRIMARY KEY,
	"name" TEXT NOT NULL
);

CREATE TABLE "product" (
	"id" SERIAL PRIMARY KEY,
	"category_id" INTEGER NOT NULL REFERENCES "category",
	"name" TEXT NOT NULL,
	"price" BIGINT NOT NULL,
	"amount" INT NOT NULL,
	"date" DATE NOT NULL
);
CREATE TABLE "bankaccounts" (
	"id" SERIAL PRIMARY KEY,
	"accountnumber" BIGINT NOT NULL,
	"accountbalance" INTEGER NOT NULL
);





