-- V12__Create_employee_table_optional_fields.sql

-- Create required extensions and types
create extension if not exists "uuid-ossp";

do
$$
    begin
        if
            not exists(select from pg_type where typname = 'sex') then
            create type sex as enum ('H', 'F');
        end if;
        if
            not exists(select from pg_type where typname = 'csp') then
            create type csp as enum ('AGRICULTURAL_WORKERS', 'CRAFTSMEN_AND_ARTISANS', 'TRADERS_AND_MERCHANTS', 'CIVIL_SERVANTS_AND_PROFESSIONALS', 'UNSKILLED_LABORERS');
        end if;
    end
$$;

-- Create employee table with optional fields
create table if not exists "employee"
(
    id                  varchar not null
        constraint employee_pk primary key default uuid_generate_v4(),
    first_name          varchar,
    last_name           varchar,
    registration_number varchar,
    personal_email      varchar unique,
    cin                 varchar check ( cin ~ '^[0-9]+$'),
    children_number     integer default 0 check ( children_number > -1 ),
    birth_date          date,
    entrance_date       date,
    departure_date      date,
    sex                 sex,
    csp                 csp,
    image               text,
    professional_email  varchar unique,
    address             varchar,
    endToEndId          varchar
);

-- Create sequence and trigger for reference number
CREATE SEQUENCE if not exists employ_ref_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 999999
    CACHE 1;

CREATE OR REPLACE FUNCTION generate_employ_custom_ref()
    RETURNS TRIGGER AS
$BODY$
BEGIN
    NEW.registration_number := 'REF-' || LPAD(NEXTVAL('employ_ref_sequence')::TEXT, 6, '0');
    RETURN NEW;
END;
$BODY$
    LANGUAGE plpgsql;

CREATE TRIGGER insert_reference_number_trigger
    BEFORE INSERT
    ON "employee"
    FOR EACH ROW
EXECUTE FUNCTION generate_employ_custom_ref();
