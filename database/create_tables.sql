
CREATE TABLE "manager_of_hse" (
	"id"  INTEGER PRIMARY KEY NOT NULL,
	"name" TEXT NOT NULL,
	"surname" TEXT NOT NULL,
	"position" TEXT NOT NULL
);
CREATE TABLE "labor_protection_instructions" (
	"serial_number" INTEGER PRIMARY KEY,
	"name" TEXT NOT NULL,
	"date_of_creation" DATE NOT NULL,
	"expiration_date" DATE NOT NULL
);
CREATE TABLE "workflow_journal" (
    "id" SERIAL PRIMARY KEY,
    "document_type" TEXT NOT NULL, 
    "document_number" INTEGER NOT NULL,
    "document_name" TEXT NOT NULL
);
CREATE TABLE "workers_personal_card" (
	"personnel_number" SERIAL PRIMARY KEY,
	"name" TEXT NOT NULL,
	"surname" TEXT NOT NULL,
	"work_area" TEXT NOT NULL,
	"position" TEXT NOT NULL,
	"date_of_briefing" DATE,
	"briefing_type" TEXT NOT NULL,
	"serial_number_id" INTEGER NOT NULL REFERENCES "labor_protection_instructions" ON DELETE RESTRICT,
	"workflow_journal_id" INTEGER REFERENCES "workflow_journal" ON DELETE RESTRICT,
	"manager_of_hse_id" INTEGER NOT NULL REFERENCES "manager_of_hse" ON DELETE RESTRICT	
);

CREATE TABLE "user" (
	"id" SERIAL PRIMARY KEY,
	"login" TEXT NOT NULL UNIQUE,
	"password" CHAR(128) NOT NULL,
	"role" SMALLINT NOT NULL CHECK ("role" IN (0, 1, 2))
	/*
	 * 0 - ADMIN
	 * 1 - MANAGER_OF_DEPARTMENT_HSE
	 * 2 - WORK_AREA_MANAGER
	 */
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





