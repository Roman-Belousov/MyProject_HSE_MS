
psql --username=postgres --dbname=postgres --file=create_db.sql
psql --username=postgres --dbname=HSE_MS --file=create_tables.sql
psql --username=postgres --dbname=HSE_MS --command="\encoding UTF8" --file=fill_tables_test.sql
pause

