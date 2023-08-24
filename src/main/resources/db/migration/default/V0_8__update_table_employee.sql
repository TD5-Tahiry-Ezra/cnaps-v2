ALTER TABLE employee
    ALTER COLUMN cnaps DROP NOT NULL;

-- Add the end_to_end_id column
ALTER TABLE employee
    ADD COLUMN end_to_end_id character varying;
